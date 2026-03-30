package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.*;
import com.mycompany.quanlyquaninternet.entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ComputerPanel extends JPanel {
    private static final Color BG = new Color(24, 21, 55);
    private static final Color CARD_BG = new Color(30, 27, 65);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    private static final Color GREEN = new Color(16, 185, 129);
    private static final Color RED = new Color(239, 68, 68);
    private static final Color YELLOW = new Color(245, 158, 11);
    private static final Color ACCENT = new Color(99, 102, 241);
    private static final Color VIP_GOLD = new Color(251, 191, 36);

    private JPanel gridPanel;
    private final ComputerDAO computerDAO = new ComputerDAO();
    private final SessionDAO sessionDAO = new SessionDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final FoodDrinkDAO foodDrinkDAO = new FoodDrinkDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private MainView mainView;

    public ComputerPanel() {
        setBackground(BG);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        initComponents();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    private void initComponents() {
        // Top bar with filters and add button
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filterPanel.setOpaque(false);

        String[] filters = {"Tất cả", "Trống", "Đang dùng", "Bảo trì"};
        for (String filter : filters) {
            JButton btn = createFilterButton(filter);
            filterPanel.add(btn);
        }
        topBar.add(filterPanel, BorderLayout.WEST);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actionPanel.setOpaque(false);

        JButton btnRefresh = createActionButton("\u21BB Làm mới", ACCENT);
        btnRefresh.addActionListener(e -> refreshComputers());
        actionPanel.add(btnRefresh);

        JButton btnAdd = createActionButton("+ Thêm Máy", GREEN);
        btnAdd.addActionListener(e -> showAddComputerDialog());
        actionPanel.add(btnAdd);

        topBar.add(actionPanel, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // Computer grid
        gridPanel = new JPanel(new GridLayout(0, 5, 12, 12));
        gridPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(BG);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        refreshComputers();
    }

    public void refreshComputers() {
        gridPanel.removeAll();
        List<Computer> computers = computerDAO.getAll();
        for (Computer c : computers) {
            gridPanel.add(createComputerCard(c));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private JPanel createComputerCard(Computer computer) {
        Color statusColor = computer.isAvailable() ? GREEN : computer.isInUse() ? RED : YELLOW;
        Color cardBg = computer.isAvailable() ? new Color(16, 185, 129, 15) :
                       computer.isInUse() ? new Color(239, 68, 68, 15) :
                       new Color(245, 158, 11, 15);

        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(CARD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 14, 14));
                // Status border
                g2d.setColor(statusColor);
                g2d.setStroke(new BasicStroke(2f));
                g2d.draw(new RoundRectangle2D.Double(1, 1, getWidth()-2, getHeight()-2, 14, 14));
            }
        };
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(180, 140));
        card.setLayout(new GridBagLayout());
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(8, 0, 2, 0);

        // Monitor icon
        JLabel lblIcon = new JLabel("\uD83D\uDDA5");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        card.add(lblIcon, gbc);

        // Computer name
        gbc.gridy = 1; gbc.insets = new Insets(2, 0, 2, 0);
        JLabel lblName = new JLabel(computer.getName());
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblName.setForeground(TEXT_PRIMARY);
        card.add(lblName, gbc);

        // Type badge
        gbc.gridy = 2; gbc.insets = new Insets(2, 0, 2, 0);
        JLabel lblType = new JLabel(computer.getType());
        lblType.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblType.setForeground(computer.isVIP() ? VIP_GOLD : TEXT_SECONDARY);
        card.add(lblType, gbc);

        // Status
        gbc.gridy = 3; gbc.insets = new Insets(2, 0, 8, 0);
        JLabel lblStatus = new JLabel("● " + computer.getStatus());
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStatus.setForeground(statusColor);
        card.add(lblStatus, gbc);

        // Click handler
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleComputerClick(computer);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                card.repaint();
            }
        });

        return card;
    }

    private void handleComputerClick(Computer computer) {
        if (computer.isAvailable()) {
            showStartSessionDialog(computer);
        } else if (computer.isInUse()) {
            showSessionInfoDialog(computer);
        } else {
            // Maintenance
            int option = JOptionPane.showConfirmDialog(this,
                    computer.getName() + " đang bảo trì.\nĐặt lại trạng thái Trống?",
                    "Bảo trì", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                computerDAO.updateStatus(computer.getId(), "Trống");
                refreshComputers();
            }
        }
    }

    private void showStartSessionDialog(Computer computer) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Bắt Đầu Phiên - " + computer.getName(), true);
        dialog.setSize(420, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.gridx = 0; gbc.gridwidth = 2;

        // Title
        JLabel lblTitle = new JLabel("\uD83D\uDDA5  " + computer.getName() + " (" + computer.getType() + ")");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(TEXT_PRIMARY);
        gbc.gridy = 0;
        panel.add(lblTitle, gbc);

        JLabel lblPrice = new JLabel("Giá: " + String.format("%,.0f", computer.getPricePerHour()) + " đ/giờ");
        lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPrice.setForeground(computer.isVIP() ? VIP_GOLD : GREEN);
        gbc.gridy = 1;
        panel.add(lblPrice, gbc);

        // Customer selection
        gbc.gridy = 2; gbc.insets = new Insets(15, 0, 4, 0);
        JLabel lblCust = new JLabel("Khách hàng:");
        lblCust.setForeground(TEXT_SECONDARY);
        lblCust.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblCust, gbc);

        List<Customer> customers = customerDAO.getAll();
        JComboBox<String> cboCustomer = new JComboBox<>();
        cboCustomer.addItem("-- Khách vãng lai --");
        for (Customer c : customers) {
            cboCustomer.addItem(c.getId() + " - " + c.getName() + " (" + String.format("%,.0f", c.getBalance()) + "đ)");
        }
        cboCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 6, 0);
        panel.add(cboCustomer, gbc);

        // Or enter name
        gbc.gridy = 4; gbc.insets = new Insets(6, 0, 4, 0);
        JLabel lblName = new JLabel("Hoặc nhập tên khách:");
        lblName.setForeground(TEXT_SECONDARY);
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblName, gbc);

        JTextField txtName = new JTextField();
        txtName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridy = 5; gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(txtName, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.setOpaque(false);

        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dialog.dispose());

        JButton btnStart = new JButton("▶ Bắt Đầu");
        btnStart.setBackground(GREEN);
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnStart.addActionListener(e -> {
            String customerName = txtName.getText().trim();
            Integer customerId = null;

            int selectedIdx = cboCustomer.getSelectedIndex();
            if (selectedIdx > 0) {
                Customer selected = customers.get(selectedIdx - 1);
                customerId = selected.getId();
                customerName = selected.getName();
            }

            if (customerName.isEmpty() && customerId == null) {
                customerName = "Khách vãng lai";
            }

            Session session = new Session(computer.getId(), customerName, customerId);
            int sessionId = sessionDAO.startSession(session);
            if (sessionId > 0) {
                computerDAO.updateStatus(computer.getId(), "Đang dùng");
                refreshComputers();
                dialog.dispose();
                JOptionPane.showMessageDialog(this,
                        "Đã bắt đầu phiên cho " + computer.getName() + "!",
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnPanel.add(btnCancel);
        btnPanel.add(btnStart);
        gbc.gridy = 6; gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(btnPanel, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void showSessionInfoDialog(Computer computer) {
        Session session = sessionDAO.getActiveByComputerId(computer.getId());
        if (session == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiên hoạt động!");
            computerDAO.updateStatus(computer.getId(), "Trống");
            refreshComputers();
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Phiên - " + computer.getName(), true);
        dialog.setSize(480, 520);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridwidth = 2;

        // Title
        JLabel lblTitle = new JLabel("\uD83D\uDDA5  " + computer.getName() + " - ĐANG SỬ DỤNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(RED);
        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(lblTitle, gbc);

        // Session info
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        double hours = session.getElapsedHours();
        int h = (int) hours;
        int m = (int) ((hours - h) * 60);
        int s = (int) (((hours - h) * 60 - m) * 60);
        double computerCost = session.calculateCost(computer.getPricePerHour());
        double orderTotal = orderDAO.getOrderTotalBySession(session.getId());

        addInfoRow(panel, gbc, 1, "Khách hàng:", session.getCustomerName());
        addInfoRow(panel, gbc, 2, "Bắt đầu:", sdf.format(session.getStartTime()));
        addInfoRow(panel, gbc, 3, "Thời gian:", String.format("%dh %02dp %02ds", h, m, s));
        addInfoRow(panel, gbc, 4, "Giá/giờ:", String.format("%,.0f đ", computer.getPricePerHour()));
        addInfoRow(panel, gbc, 5, "Tiền máy:", String.format("%,.0f đ", computerCost));
        addInfoRow(panel, gbc, 6, "Tiền DV:", String.format("%,.0f đ", orderTotal));

        // Total
        gbc.gridy = 7; gbc.insets = new Insets(10, 0, 10, 0);
        JLabel lblTotal = new JLabel("TỔNG: " + String.format("%,.0f đ", computerCost + orderTotal));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTotal.setForeground(VIP_GOLD);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTotal, gbc);

        // Balance info if customer
        if (session.getCustomerId() != null) {
            Customer customer = customerDAO.getById(session.getCustomerId());
            if (customer != null) {
                gbc.gridy = 8; gbc.insets = new Insets(0, 0, 10, 0);
                JLabel lblBalance = new JLabel("Số dư: " + String.format("%,.0f đ", customer.getBalance()));
                lblBalance.setFont(new Font("Segoe UI", Font.BOLD, 14));
                lblBalance.setForeground(customer.getBalance() >= computerCost + orderTotal ? GREEN : RED);
                lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(lblBalance, gbc);
            }
        }

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.setOpaque(false);

        JButton btnOrder = new JButton("\uD83C\uDF54 Gọi Món");
        btnOrder.setBackground(ACCENT);
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnOrder.addActionListener(e -> {
            dialog.dispose();
            showOrderDialog(computer, session);
        });

        JButton btnMaintenance = new JButton("\uD83D\uDD27 Bảo Trì");
        btnMaintenance.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnMaintenance.addActionListener(e -> {
            endSessionAndSetStatus(session, computer, computerCost, orderTotal, "Bảo trì");
            dialog.dispose();
        });

        JButton btnEnd = new JButton("⏹ Kết Thúc");
        btnEnd.setBackground(RED);
        btnEnd.setForeground(Color.WHITE);
        btnEnd.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEnd.addActionListener(e -> {
            endSessionAndSetStatus(session, computer, computerCost, orderTotal, "Trống");
            dialog.dispose();
        });

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dialog.dispose());

        btnPanel.add(btnOrder);
        btnPanel.add(btnMaintenance);
        btnPanel.add(btnEnd);
        btnPanel.add(btnClose);

        gbc.gridy = 9; gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(btnPanel, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void endSessionAndSetStatus(Session session, Computer computer, double computerCost, double orderTotal, String newStatus) {
        double totalCost = computerCost + orderTotal;
        sessionDAO.endSession(session.getId(), totalCost);
        computerDAO.updateStatus(computer.getId(), newStatus);

        // Deduct from customer balance if applicable
        if (session.getCustomerId() != null) {
            customerDAO.deductBalance(session.getCustomerId(), totalCost);
            customerDAO.addHours(session.getCustomerId(), session.getElapsedHours());
        }

        refreshComputers();
        JOptionPane.showMessageDialog(this,
                "Phiên kết thúc!\nTổng chi phí: " + String.format("%,.0f đ", totalCost),
                "Kết thúc phiên", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showOrderDialog(Computer computer, Session session) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Gọi Món - " + computer.getName(), true);
        dialog.setSize(550, 500);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Menu table
        List<FoodDrink> menu = foodDrinkDAO.getAvailable();
        String[] cols = {"Chọn", "Tên", "Giá (đ)", "SL", "Loại"};
        Object[][] data = new Object[menu.size()][5];
        for (int i = 0; i < menu.size(); i++) {
            FoodDrink fd = menu.get(i);
            data[i] = new Object[]{false, fd.getName(), String.format("%,.0f", fd.getPrice()), 1, fd.getCategory()};
        }

        javax.swing.table.DefaultTableModel menuModel = new javax.swing.table.DefaultTableModel(cols, 0) {
            @Override
            public Class<?> getColumnClass(int col) {
                if (col == 0) return Boolean.class;
                if (col == 3) return Integer.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 0 || col == 3;
            }
        };
        for (Object[] row : data) menuModel.addRow(row);

        JTable menuTable = new JTable(menuModel);
        menuTable.setRowHeight(32);
        menuTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        menuTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        menuTable.getColumnModel().getColumn(3).setPreferredWidth(40);

        JScrollPane sp = new JScrollPane(menuTable);
        panel.add(sp, BorderLayout.CENTER);

        // Bottom
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);

        JButton btnConfirm = new JButton("✓ Xác Nhận Đơn");
        btnConfirm.setBackground(GREEN);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConfirm.addActionListener(e -> {
            Order order = new Order(session.getId(), computer.getId());
            for (int i = 0; i < menuModel.getRowCount(); i++) {
                boolean selected = (boolean) menuModel.getValueAt(i, 0);
                if (selected) {
                    FoodDrink fd = menu.get(i);
                    int qty = (int) menuModel.getValueAt(i, 3);
                    if (qty > 0) {
                        order.addItem(new OrderItem(fd.getId(), fd.getName(), qty, fd.getPrice()));
                    }
                }
            }

            if (order.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Chưa chọn món nào!");
                return;
            }

            order.recalculateTotal();
            orderDAO.createOrder(order);
            dialog.dispose();
            JOptionPane.showMessageDialog(this,
                    "Đã đặt " + order.getItems().size() + " món!\nTổng: " + String.format("%,.0f đ", order.getTotalPrice()),
                    "Đặt món thành công", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dialog.dispose());

        bottom.add(btnCancel);
        bottom.add(btnConfirm);
        panel.add(bottom, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void showAddComputerDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm Máy Tính", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 4, 0);

        JTextField txtName = new JTextField();
        JComboBox<String> cboType = new JComboBox<>(new String[]{"Thường", "VIP"});
        JTextField txtPrice = new JTextField("10000");
        JTextField txtSpecs = new JTextField();

        addFormField(panel, gbc, 0, "Tên máy:", txtName);
        addFormField(panel, gbc, 1, "Loại:", cboType);
        addFormField(panel, gbc, 2, "Giá/giờ:", txtPrice);
        addFormField(panel, gbc, 3, "Cấu hình:", txtSpecs);

        cboType.addActionListener(e -> {
            if ("VIP".equals(cboType.getSelectedItem())) {
                txtPrice.setText("15000");
            } else {
                txtPrice.setText("10000");
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(GREEN);
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Nhập tên máy!");
                return;
            }
            try {
                Computer c = new Computer();
                c.setName(name);
                c.setType((String) cboType.getSelectedItem());
                c.setPricePerHour(Double.parseDouble(txtPrice.getText().trim()));
                c.setStatus("Trống");
                c.setSpecs(txtSpecs.getText().trim());
                computerDAO.add(c);
                refreshComputers();
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Giá không hợp lệ!");
            }
        });
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dialog.dispose());
        btnPanel.add(btnCancel);
        btnPanel.add(btnSave);

        gbc.gridy = 4; gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(btnPanel, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    // Helper methods
    private void addInfoRow(JPanel panel, GridBagConstraints gbc, int row, String label, String value) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.insets = new Insets(3, 0, 3, 10);
        gbc.gridx = 0;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(TEXT_SECONDARY);
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        JLabel val = new JLabel(value);
        val.setFont(new Font("Segoe UI", Font.BOLD, 13));
        val.setForeground(TEXT_PRIMARY);
        panel.add(val, gbc);

        gbc.gridx = 0; gbc.gridwidth = 2;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridy = row * 2;
        JLabel lbl = new JLabel(label);
        lbl.setForeground(TEXT_SECONDARY);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lbl, gbc);

        gbc.gridy = row * 2 + 1;
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(field, gbc);
    }

    private JButton createFilterButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            gridPanel.removeAll();
            List<Computer> list;
            if ("Tất cả".equals(text)) {
                list = computerDAO.getAll();
            } else {
                list = computerDAO.getByStatus(text);
            }
            for (Computer c : list) {
                gridPanel.add(createComputerCard(c));
            }
            gridPanel.revalidate();
            gridPanel.repaint();
        });
        return btn;
    }

    private JButton createActionButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
