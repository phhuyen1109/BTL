package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.CustomerDAO;
import com.mycompany.quanlyquaninternet.dao.FoodDrinkDAO;
import com.mycompany.quanlyquaninternet.dao.RewardHistoryDAO;
import com.mycompany.quanlyquaninternet.entity.Customer;
import com.mycompany.quanlyquaninternet.entity.FoodDrink;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private static final Color ORANGE = new Color(245, 158, 11);

    private static final double PRICE_PER_HOUR = 10000.0;

    private JTable tblCustomers;
    private DefaultTableModel tableModel;
    private JTextField txtSearch, txtName, txtPhone, txtBalance;
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final FoodDrinkDAO foodDrinkDAO = new FoodDrinkDAO();
    private final RewardHistoryDAO rewardHistoryDAO = new RewardHistoryDAO();

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
        String[] columns = {"ID", "Tên khách hàng", "Số điện thoại", "Số dư (đ)", "Tổng giờ chơi", "Điểm \uD83C\uDFC6"};
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

        JButton btnReward = new JButton("\uD83C\uDF81 Đổi Thưởng");
        btnReward.setBackground(ORANGE);
        btnReward.setForeground(Color.WHITE);
        btnReward.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnReward.addActionListener(e -> showRewardDialog());
        bottomPanel.add(btnReward);

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
                String.format("%.1f giờ", c.getTotalHours()),
                c.getPoints() + " \u2B50"
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
                String.format("%.1f giờ", c.getTotalHours()),
                c.getPoints() + " \u2B50"
            });
        }
    }

    private void showAddDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm Khách Hàng Mới", true);
        dialog.setSize(420, 280);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 4, 0);

        txtName = new JTextField();
        txtPhone = new JTextField();
        txtBalance = new JTextField();

        // Tên
        gbc.gridy = 0;
        JLabel lblName = new JLabel("Tên khách hàng (*):");
        lblName.setForeground(TEXT_SECONDARY);
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblName, gbc);
        gbc.gridy = 1;
        txtName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtName, gbc);

        // SĐT
        gbc.gridy = 2;
        JLabel lblPhone = new JLabel("SĐT:");
        lblPhone.setForeground(TEXT_SECONDARY);
        lblPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblPhone, gbc);
        gbc.gridy = 3;
        txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtPhone, gbc);

        // Số tiền nạp
        gbc.gridy = 4;
        JLabel lblBalance = new JLabel("Số tiền nạp ban đầu (*) (đ):");
        lblBalance.setForeground(TEXT_SECONDARY);
        lblBalance.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblBalance, gbc);
        gbc.gridy = 5;
        txtBalance.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtBalance, gbc);

        // Label hiển thị giờ chơi realtime
        gbc.gridy = 6;
        JLabel lblHoursPreview = new JLabel("\u23F1 \u2248 0.0 giờ chơi  |  \u2B50 0 điểm");
        lblHoursPreview.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblHoursPreview.setForeground(GREEN);
        panel.add(lblHoursPreview, gbc);

        // Realtime update khi gõ số tiền
        txtBalance.getDocument().addDocumentListener(new DocumentListener() {
            private void update() {
                try {
                    double amount = Double.parseDouble(txtBalance.getText().trim().replace(",", ""));
                    double hours = amount / PRICE_PER_HOUR;
                    int points = (int) hours;
                    lblHoursPreview.setText(String.format("\u23F1 \u2248 %.1f giờ chơi  |  \u2B50 %d điểm", hours, points));
                    lblHoursPreview.setForeground(GREEN);
                } catch (Exception e) {
                    lblHoursPreview.setText("\u23F1 \u2248 0.0 giờ chơi  |  \u2B50 0 điểm");
                    lblHoursPreview.setForeground(TEXT_SECONDARY);
                }
            }
            public void insertUpdate(DocumentEvent e) { update(); }
            public void removeUpdate(DocumentEvent e) { update(); }
            public void changedUpdate(DocumentEvent e) { update(); }
        });

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        JButton btnSave = new JButton("✓ Tạo Khách Hàng");
        btnSave.setBackground(GREEN);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSave.addActionListener(e -> {
            String name = txtName.getText().trim();
            String phone = txtPhone.getText().trim();
            double balance = 0;
            try { balance = Double.parseDouble(txtBalance.getText().trim().replace(",", "")); } catch (Exception ignored) {}

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Nhập tên khách hàng!");
                return;
            }
            if (balance <= 0) {
                JOptionPane.showMessageDialog(dialog, "Khách mới cần nạp tiền! Nhập số tiền > 0.");
                return;
            }

            double hours = balance / PRICE_PER_HOUR;
            int points = (int) hours;

            Customer c = new Customer();
            c.setName(name);
            c.setPhone(phone);
            c.setBalance(balance);
            c.setTotalHours(hours);
            c.setPoints(points);
            customerDAO.add(c);
            loadCustomers();
            dialog.dispose();
            JOptionPane.showMessageDialog(this,
                    "Đã thêm khách \"" + name + "\"!\n" +
                    "Số dư: " + String.format("%,.0f", balance) + "đ\n" +
                    "Giờ chơi: " + String.format("%.1f", hours) + " giờ\n" +
                    "Điểm: " + points + " \u2B50",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
        });
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dialog.dispose());
        btnPanel.add(btnCancel);
        btnPanel.add(btnSave);
        gbc.gridy = 7; gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(btnPanel, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
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

        JPanel formPanel = createEditFormPanel(customer.getName(), customer.getPhone());
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Sửa Thông Tin Khách Hàng",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            customer.setName(txtName.getText().trim());
            customer.setPhone(txtPhone.getText().trim());
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
        Customer customer = customerDAO.getById(id);
        if (customer == null) return;

        // Dialog nạp tiền cải tiến
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nạp Tiền - " + name, true);
        dialog.setSize(400, 280);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridwidth = 2;

        // Info hiện tại
        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 4, 0);
        JLabel lblInfo = new JLabel("Khách: " + name + "  |  Số dư hiện tại: " + String.format("%,.0f", customer.getBalance()) + "đ");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblInfo.setForeground(TEXT_SECONDARY);
        panel.add(lblInfo, gbc);

        gbc.gridy = 1;
        JLabel lblPointsInfo = new JLabel("Điểm hiện có: " + customer.getPoints() + " \u2B50  |  Giờ chơi: " + String.format("%.1f", customer.getTotalHours()) + " giờ");
        lblPointsInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPointsInfo.setForeground(ORANGE);
        panel.add(lblPointsInfo, gbc);

        // Input
        gbc.gridy = 2; gbc.insets = new Insets(15, 0, 4, 0);
        JLabel lblAmount = new JLabel("Nhập số tiền nạp (đ):");
        lblAmount.setForeground(TEXT_SECONDARY);
        lblAmount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblAmount, gbc);

        JTextField txtAmount = new JTextField();
        txtAmount.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 6, 0);
        panel.add(txtAmount, gbc);

        // Preview realtime
        gbc.gridy = 4;
        JLabel lblPreview = new JLabel("\u23F1 + 0.0 giờ chơi mới  |  \u2B50 + 0 điểm");
        lblPreview.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPreview.setForeground(GREEN);
        panel.add(lblPreview, gbc);

        txtAmount.getDocument().addDocumentListener(new DocumentListener() {
            private void update() {
                try {
                    double amount = Double.parseDouble(txtAmount.getText().trim().replace(",", ""));
                    double hours = amount / PRICE_PER_HOUR;
                    int points = (int) hours;
                    lblPreview.setText(String.format("\u23F1 + %.1f giờ chơi mới  |  \u2B50 + %d điểm", hours, points));
                    lblPreview.setForeground(GREEN);
                } catch (Exception e) {
                    lblPreview.setText("\u23F1 + 0.0 giờ chơi mới  |  \u2B50 + 0 điểm");
                    lblPreview.setForeground(TEXT_SECONDARY);
                }
            }
            public void insertUpdate(DocumentEvent e) { update(); }
            public void removeUpdate(DocumentEvent e) { update(); }
            public void changedUpdate(DocumentEvent e) { update(); }
        });

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        JButton btnConfirm = new JButton("\uD83D\uDCB0 Nạp Tiền");
        btnConfirm.setBackground(new Color(168, 85, 247));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConfirm.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText().trim().replace(",", ""));
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Số tiền phải lớn hơn 0!");
                    return;
                }
                double hours = amount / PRICE_PER_HOUR;
                int points = (int) hours;
                customerDAO.topUpWithHoursAndPoints(id, amount);
                loadCustomers();
                dialog.dispose();
                JOptionPane.showMessageDialog(this,
                        "Đã nạp " + String.format("%,.0f", amount) + "đ cho " + name + "!\n" +
                        "Cộng thêm: " + String.format("%.1f", hours) + " giờ chơi\n" +
                        "Tích thêm: " + points + " \u2B50 điểm",
                        "Nạp tiền thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Số tiền không hợp lệ!");
            }
        });
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dialog.dispose());
        btnPanel.add(btnCancel);
        btnPanel.add(btnConfirm);
        gbc.gridy = 5; gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(btnPanel, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void showRewardDialog() {
        int row = tblCustomers.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần đổi thưởng!");
            return;
        }
        int customerId = (int) tableModel.getValueAt(row, 0);
        String customerName = (String) tableModel.getValueAt(row, 1);
        Customer customer = customerDAO.getById(customerId);
        if (customer == null) return;

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "\uD83C\uDF81 Đổi Thưởng - " + customerName, true);
        dialog.setSize(550, 480);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header: số điểm hiện có
        JLabel lblPoints = new JLabel("\u2B50 Điểm hiện có: " + customer.getPoints() + " điểm");
        lblPoints.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPoints.setForeground(ORANGE);
        lblPoints.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        panel.add(lblPoints, BorderLayout.NORTH);

        // Bảng đồ ăn/uống có thể đổi
        List<FoodDrink> menu = foodDrinkDAO.getAvailable();
        String[] cols = {"Chọn", "Tên", "Giá (đ)", "Điểm cần", "Loại"};
        DefaultTableModel rewardModel = new DefaultTableModel(cols, 0) {
            @Override
            public Class<?> getColumnClass(int col) {
                if (col == 0) return Boolean.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row2, int col) {
                return col == 0;
            }
        };

        for (FoodDrink fd : menu) {
            if (fd.getPointsCost() > 0) {
                rewardModel.addRow(new Object[]{
                    false,
                    fd.getName(),
                    String.format("%,.0f", fd.getPrice()),
                    fd.getPointsCost() + " \u2B50",
                    fd.getCategory()
                });
            }
        }

        JTable rewardTable = new JTable(rewardModel);
        rewardTable.setRowHeight(32);
        rewardTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rewardTable.getColumnModel().getColumn(0).setPreferredWidth(40);

        JScrollPane sp = new JScrollPane(rewardTable);
        panel.add(sp, BorderLayout.CENTER);

        // Bottom
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);

        JButton btnConfirm = new JButton("✓ Đổi Thưởng");
        btnConfirm.setBackground(ORANGE);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConfirm.addActionListener(e -> {
            int totalPointsNeeded = 0;
            StringBuilder sb = new StringBuilder("Bạn sẽ đổi:\n");
            int rewardCount = 0;

            for (int i = 0; i < rewardModel.getRowCount(); i++) {
                boolean selected = (boolean) rewardModel.getValueAt(i, 0);
                if (selected) {
                    // Tìm FoodDrink tương ứng (chỉ lấy những item có pointsCost > 0)
                    int menuIdx = 0;
                    for (int j = 0; j < menu.size(); j++) {
                        if (menu.get(j).getPointsCost() > 0) {
                            if (menuIdx == i) {
                                FoodDrink fd = menu.get(j);
                                totalPointsNeeded += fd.getPointsCost();
                                sb.append("  - ").append(fd.getName()).append(" (").append(fd.getPointsCost()).append(" \u2B50)\n");
                                rewardCount++;
                                break;
                            }
                            menuIdx++;
                        }
                    }
                }
            }

            if (rewardCount == 0) {
                JOptionPane.showMessageDialog(dialog, "Chưa chọn phần thưởng nào!");
                return;
            }

            if (totalPointsNeeded > customer.getPoints()) {
                JOptionPane.showMessageDialog(dialog,
                        "Không đủ điểm!\nCần: " + totalPointsNeeded + " \u2B50\nHiện có: " + customer.getPoints() + " \u2B50",
                        "Không đủ điểm", JOptionPane.WARNING_MESSAGE);
                return;
            }

            sb.append("\nTổng điểm cần: ").append(totalPointsNeeded).append(" \u2B50");
            sb.append("\nĐiểm còn lại: ").append(customer.getPoints() - totalPointsNeeded).append(" \u2B50");

            int confirm = JOptionPane.showConfirmDialog(dialog, sb.toString(),
                    "Xác nhận đổi thưởng", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Trừ điểm và ghi lịch sử
                for (int i = 0; i < rewardModel.getRowCount(); i++) {
                    boolean selected = (boolean) rewardModel.getValueAt(i, 0);
                    if (selected) {
                        int idx2 = 0;
                        for (int j = 0; j < menu.size(); j++) {
                            if (menu.get(j).getPointsCost() > 0) {
                                if (idx2 == i) {
                                    FoodDrink fd = menu.get(j);
                                    rewardHistoryDAO.addHistory(customerId, fd.getId(), fd.getName(), fd.getPointsCost());
                                    break;
                                }
                                idx2++;
                            }
                        }
                    }
                }
                customerDAO.deductPoints(customerId, totalPointsNeeded);
                loadCustomers();
                dialog.dispose();
                JOptionPane.showMessageDialog(this,
                        "Đổi thưởng thành công cho " + customerName + "!\n" +
                        "Đã trừ " + totalPointsNeeded + " \u2B50 điểm.",
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btnCancel = new JButton("Đóng");
        btnCancel.addActionListener(e -> dialog.dispose());

        bottom.add(btnCancel);
        bottom.add(btnConfirm);
        panel.add(bottom, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private JPanel createEditFormPanel(String name, String phone) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 8, 8));
        panel.setPreferredSize(new Dimension(350, 80));

        txtName = new JTextField(name);
        txtPhone = new JTextField(phone);

        panel.add(new JLabel("Tên:"));
        panel.add(txtName);
        panel.add(new JLabel("SĐT:"));
        panel.add(txtPhone);

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
