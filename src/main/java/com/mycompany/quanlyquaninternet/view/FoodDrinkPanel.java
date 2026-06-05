package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.FoodDrinkDAO;
import com.mycompany.quanlyquaninternet.entity.FoodDrink;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FoodDrinkPanel extends JPanel {
    private static final Color BG = new Color(24, 21, 55);
    private static final Color CARD_BG = new Color(30, 27, 65);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    private static final Color GREEN = new Color(16, 185, 129);
    private static final Color ACCENT = new Color(99, 102, 241);
    private static final Color RED = new Color(239, 68, 68);

    private JTable tblMenu;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPrice;
    private JComboBox<String> cboCategory;
    private JCheckBox chkAvailable;
    private final FoodDrinkDAO foodDrinkDAO = new FoodDrinkDAO();

    public FoodDrinkPanel() {
        setBackground(BG);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        initComponents();
        loadMenu();
    }

    private void initComponents() {
        // === TOP BAR ===
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filterPanel.setOpaque(false);

        String[] filters = {"Tất cả", "Đồ ăn", "Nước uống"};
        for (String filter : filters) {
            JButton btn = new JButton(filter);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btn.addActionListener(e -> filterByCategory(filter));
            filterPanel.add(btn);
        }
        topBar.add(filterPanel, BorderLayout.WEST);

        JButton btnAdd = new JButton("+ Thêm Món");
        btnAdd.setBackground(GREEN);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.addActionListener(e -> showAddDialog());
        topBar.add(btnAdd, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // === TABLE ===
        String[] columns = {"ID", "Tên món", "Giá (đ)", "Loại", "Trạng thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblMenu = new JTable(tableModel);
        styleTable(tblMenu);

        // Custom renderer for status
        tblMenu.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ("Còn hàng".equals(value)) {
                    setForeground(GREEN);
                } else {
                    setForeground(RED);
                }
                setHorizontalAlignment(CENTER);
                if (isSelected) setBackground(new Color(99, 102, 241, 50));
                else setBackground(CARD_BG);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tblMenu);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        scrollPane.getViewport().setBackground(CARD_BG);
        add(scrollPane, BorderLayout.CENTER);

        // === BOTTOM BUTTONS ===
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        bottomPanel.setOpaque(false);

        JButton btnEdit = new JButton("\u270F Sửa");
        btnEdit.setBackground(ACCENT);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEdit.addActionListener(e -> editItem());
        bottomPanel.add(btnEdit);

        JButton btnToggle = new JButton("\u21C4 Đổi TT");
        btnToggle.setBackground(new Color(245, 158, 11));
        btnToggle.setForeground(Color.WHITE);
        btnToggle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnToggle.addActionListener(e -> toggleAvailability());
        bottomPanel.add(btnToggle);

        JButton btnDelete = new JButton("\uD83D\uDDD1 Xóa");
        btnDelete.setBackground(RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDelete.addActionListener(e -> deleteItem());
        bottomPanel.add(btnDelete);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void loadMenu() {
        tableModel.setRowCount(0);
        List<FoodDrink> items = foodDrinkDAO.getAll();
        for (FoodDrink fd : items) {
            tableModel.addRow(new Object[]{
                fd.getId(),
                fd.getName(),
                String.format("%,.0f", fd.getPrice()),
                fd.getCategory(),
                fd.isAvailable() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    private void filterByCategory(String category) {
        tableModel.setRowCount(0);
        List<FoodDrink> items;
        if ("Tất cả".equals(category)) {
            items = foodDrinkDAO.getAll();
        } else {
            items = foodDrinkDAO.getByCategory(category);
        }
        for (FoodDrink fd : items) {
            tableModel.addRow(new Object[]{
                fd.getId(),
                fd.getName(),
                String.format("%,.0f", fd.getPrice()),
                fd.getCategory(),
                fd.isAvailable() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    private void showAddDialog() {
        JPanel formPanel = createFormPanel("", "", "Đồ ăn", true);
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Thêm Món",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nhập tên món!");
                return;
            }
            try {
                FoodDrink fd = new FoodDrink();
                fd.setName(name);
                fd.setPrice(Double.parseDouble(txtPrice.getText().trim().replace(",", "")));
                fd.setCategory((String) cboCategory.getSelectedItem());
                fd.setAvailable(chkAvailable.isSelected());
                foodDrinkDAO.add(fd);
                loadMenu();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá không hợp lệ!");
            }
        }
    }

    private void editItem() {
        int row = tblMenu.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn món cần sửa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        FoodDrink fd = foodDrinkDAO.getById(id);
        if (fd == null) return;

        JPanel formPanel = createFormPanel(fd.getName(),
                String.format("%.0f", fd.getPrice()), fd.getCategory(), fd.isAvailable());
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Sửa Món",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                fd.setName(txtName.getText().trim());
                fd.setPrice(Double.parseDouble(txtPrice.getText().trim().replace(",", "")));
                fd.setCategory((String) cboCategory.getSelectedItem());
                fd.setAvailable(chkAvailable.isSelected());
                foodDrinkDAO.update(fd);
                loadMenu();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá không hợp lệ!");
            }
        }
    }

    private void toggleAvailability() {
        int row = tblMenu.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn món cần đổi trạng thái!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        FoodDrink fd = foodDrinkDAO.getById(id);
        if (fd != null) {
            fd.setAvailable(!fd.isAvailable());
            foodDrinkDAO.update(fd);
            loadMenu();
        }
    }

    private void deleteItem() {
        int row = tblMenu.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn món cần xóa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        String name = (String) tableModel.getValueAt(row, 1);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Xóa món \"" + name + "\"?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            foodDrinkDAO.delete(id);
            loadMenu();
        }
    }

    private JPanel createFormPanel(String name, String price, String category, boolean available) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setPreferredSize(new Dimension(350, 140));

        txtName = new JTextField(name);
        txtPrice = new JTextField(price);
        cboCategory = new JComboBox<>(new String[]{"Đồ ăn", "Nước uống"});
        cboCategory.setSelectedItem(category);
        chkAvailable = new JCheckBox("Còn hàng", available);

        panel.add(new JLabel("Tên món:"));
        panel.add(txtName);
        panel.add(new JLabel("Giá (đ):"));
        panel.add(txtPrice);
        panel.add(new JLabel("Loại:"));
        panel.add(cboCategory);
        panel.add(new JLabel("Trạng thái:"));
        panel.add(chkAvailable);

        return panel;
    }

    private void styleTable(JTable table) {
        table.setBackground(CARD_BG);
        table.setForeground(TEXT_PRIMARY);
        table.setSelectionBackground(new Color(99, 102, 241, 50));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setGridColor(new Color(50, 47, 90));
        table.setRowHeight(36);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setBackground(new Color(35, 32, 70));
        table.getTableHeader().setForeground(TEXT_SECONDARY);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setPreferredSize(new Dimension(0, 36));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
