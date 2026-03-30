package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.CustomerDAO;
import com.mycompany.quanlyquaninternet.entity.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class CustomerPanel extends JPanel {
    private static final Color BG = new Color(24, 21, 55);
    private static final Color CARD_BG = new Color(30, 27, 65);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    private static final Color GREEN = new Color(16, 185, 129);
    private static final Color ACCENT = new Color(99, 102, 241);
    private static final Color RED = new Color(239, 68, 68);

    private JTable tblCustomers;
    private DefaultTableModel tableModel;
    private JTextField txtSearch, txtName, txtPhone, txtBalance;
    private final CustomerDAO customerDAO = new CustomerDAO();

    public CustomerPanel() {
        setBackground(BG);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        initComponents();
        loadCustomers();
    }

    private void initComponents() {
        // === TOP BAR ===
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        // Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        searchPanel.setOpaque(false);

        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm theo tên hoặc SĐT...");
        searchPanel.add(txtSearch);

        JButton btnSearch = new JButton("\uD83D\uDD0D Tìm");
        btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnSearch.addActionListener(e -> searchCustomers());
        searchPanel.add(btnSearch);

        JButton btnRefresh = new JButton("\u21BB Tất cả");
        btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnRefresh.addActionListener(e -> { txtSearch.setText(""); loadCustomers(); });
        searchPanel.add(btnRefresh);

        topBar.add(searchPanel, BorderLayout.WEST);

        JButton btnAdd = new JButton("+ Thêm Khách");
        btnAdd.setBackground(GREEN);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.addActionListener(e -> showAddDialog());
        topBar.add(btnAdd, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // === TABLE ===
        String[] columns = {"ID", "Tên khách hàng", "Số điện thoại", "Số dư (đ)", "Tổng giờ chơi"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblCustomers = new JTable(tableModel);
        styleTable(tblCustomers);

        JScrollPane scrollPane = new JScrollPane(tblCustomers);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        scrollPane.getViewport().setBackground(CARD_BG);
        add(scrollPane, BorderLayout.CENTER);

        // === BOTTOM BUTTONS ===
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        bottomPanel.setOpaque(false);

        JButton btnTopUp = new JButton("\uD83D\uDCB0 Nạp Tiền");
        btnTopUp.setBackground(new Color(168, 85, 247));
        btnTopUp.setForeground(Color.WHITE);
        btnTopUp.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnTopUp.addActionListener(e -> topUpBalance());
        bottomPanel.add(btnTopUp);

        JButton btnEdit = new JButton("\u270F Sửa");
        btnEdit.setBackground(ACCENT);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEdit.addActionListener(e -> editCustomer());
        bottomPanel.add(btnEdit);

        JButton btnDelete = new JButton("\uD83D\uDDD1 Xóa");
        btnDelete.setBackground(RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDelete.addActionListener(e -> deleteCustomer());
        bottomPanel.add(btnDelete);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void loadCustomers() {
        tableModel.setRowCount(0);
        List<Customer> customers = customerDAO.getAll();
        for (Customer c : customers) {
            tableModel.addRow(new Object[]{
                c.getId(),
                c.getName(),
                c.getPhone(),
                String.format("%,.0f", c.getBalance()),
                String.format("%.1f giờ", c.getTotalHours())
            });
        }
    }

    private void searchCustomers() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadCustomers();
            return;
        }
        tableModel.setRowCount(0);
        List<Customer> results = customerDAO.search(keyword);
        for (Customer c : results) {
            tableModel.addRow(new Object[]{
                c.getId(),
                c.getName(),
                c.getPhone(),
                String.format("%,.0f", c.getBalance()),
                String.format("%.1f giờ", c.getTotalHours())
            });
        }
    }

    private void showAddDialog() {
        JPanel formPanel = createFormPanel("", "", "0");
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Thêm Khách Hàng",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String name = txtName.getText().trim();
            String phone = txtPhone.getText().trim();
            double balance = 0;
            try { balance = Double.parseDouble(txtBalance.getText().trim().replace(",", "")); } catch (Exception ignored) {}

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nhập tên khách hàng!");
                return;
            }

            Customer c = new Customer();
            c.setName(name);
            c.setPhone(phone);
            c.setBalance(balance);
            c.setTotalHours(0);
            customerDAO.add(c);
            loadCustomers();
        }
    }

    private void editCustomer() {
        int row = tblCustomers.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần sửa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        Customer customer = customerDAO.getById(id);
        if (customer == null) return;

        JPanel formPanel = createFormPanel(customer.getName(), customer.getPhone(),
                String.format("%.0f", customer.getBalance()));
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Sửa Khách Hàng",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            customer.setName(txtName.getText().trim());
            customer.setPhone(txtPhone.getText().trim());
            try {
                customer.setBalance(Double.parseDouble(txtBalance.getText().trim().replace(",", "")));
            } catch (Exception ignored) {}
            customerDAO.update(customer);
            loadCustomers();
        }
    }

    private void deleteCustomer() {
        int row = tblCustomers.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần xóa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        String name = (String) tableModel.getValueAt(row, 1);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Xóa khách hàng \"" + name + "\"?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            customerDAO.delete(id);
            loadCustomers();
        }
    }

    private void topUpBalance() {
        int row = tblCustomers.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần nạp tiền!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        String name = (String) tableModel.getValueAt(row, 1);

        String input = JOptionPane.showInputDialog(this,
                "Nạp tiền cho \"" + name + "\"\nNhập số tiền:",
                "Nạp Tiền", JOptionPane.PLAIN_MESSAGE);
        if (input != null && !input.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(input.trim().replace(",", ""));
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0!");
                    return;
                }
                customerDAO.topUp(id, amount);
                loadCustomers();
                JOptionPane.showMessageDialog(this,
                        "Đã nạp " + String.format("%,.0f", amount) + "đ cho " + name + "!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ!");
            }
        }
    }

    private JPanel createFormPanel(String name, String phone, String balance) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setPreferredSize(new Dimension(350, 110));

        txtName = new JTextField(name);
        txtPhone = new JTextField(phone);
        txtBalance = new JTextField(balance);

        panel.add(new JLabel("Tên:"));
        panel.add(txtName);
        panel.add(new JLabel("SĐT:"));
        panel.add(txtPhone);
        panel.add(new JLabel("Số dư (đ):"));
        panel.add(txtBalance);

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
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
