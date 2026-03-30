package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.ComputerDAO;
import com.mycompany.quanlyquaninternet.dao.CustomerDAO;
import com.mycompany.quanlyquaninternet.dao.OrderDAO;
import com.mycompany.quanlyquaninternet.dao.SessionDAO;
import com.mycompany.quanlyquaninternet.entity.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class SessionPanel extends JPanel {
    private static final Color BG = new Color(24, 21, 55);
    private static final Color CARD_BG = new Color(30, 27, 65);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    private static final Color GREEN = new Color(16, 185, 129);
    private static final Color RED = new Color(239, 68, 68);
    private static final Color ACCENT = new Color(99, 102, 241);

    private JTable tblSessions;
    private DefaultTableModel tableModel;
    private JComboBox<String> cboFilter;
    private Timer refreshTimer;
    private final SessionDAO sessionDAO = new SessionDAO();
    private final ComputerDAO computerDAO = new ComputerDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final OrderDAO orderDAO = new OrderDAO();

    public SessionPanel() {
        setBackground(BG);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        initComponents();
        loadSessions();
        startAutoRefresh();
    }

    private void initComponents() {
        // === TOP BAR ===
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filterPanel.setOpaque(false);

        JLabel lblFilter = new JLabel("Hiển thị: ");
        lblFilter.setForeground(TEXT_SECONDARY);
        lblFilter.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        filterPanel.add(lblFilter);

        cboFilter = new JComboBox<>(new String[]{"Đang chạy", "Tất cả", "Kết thúc hôm nay"});
        cboFilter.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboFilter.addActionListener(e -> loadSessions());
        filterPanel.add(cboFilter);

        topBar.add(filterPanel, BorderLayout.WEST);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actionPanel.setOpaque(false);

        JButton btnEnd = new JButton("⏹ Kết Thúc Phiên");
        btnEnd.setBackground(RED);
        btnEnd.setForeground(Color.WHITE);
        btnEnd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEnd.addActionListener(e -> endSelectedSession());
        actionPanel.add(btnEnd);

        JButton btnRefresh = new JButton("\u21BB Làm mới");
        btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnRefresh.addActionListener(e -> loadSessions());
        actionPanel.add(btnRefresh);

        topBar.add(actionPanel, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // === TABLE ===
        String[] columns = {"ID", "Máy", "Khách hàng", "Bắt đầu", "Kết thúc", "Thời gian", "Chi phí (đ)", "Trạng thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblSessions = new JTable(tableModel);
        styleTable(tblSessions);

        // Custom renderer for status column
        tblSessions.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ("Đang chạy".equals(value)) {
                    setForeground(GREEN);
                } else {
                    setForeground(TEXT_SECONDARY);
                }
                setHorizontalAlignment(CENTER);
                if (isSelected) setBackground(new Color(99, 102, 241, 50));
                else setBackground(CARD_BG);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tblSessions);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        scrollPane.getViewport().setBackground(CARD_BG);
        add(scrollPane, BorderLayout.CENTER);

        // === SUMMARY ===
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        summaryPanel.setOpaque(false);
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JLabel lblInfo = new JLabel("\u2139  Click đúp để xem chi tiết phiên  |  Tự động cập nhật mỗi 30 giây");
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInfo.setForeground(TEXT_SECONDARY);
        summaryPanel.add(lblInfo);

        add(summaryPanel, BorderLayout.SOUTH);
    }

    public void loadSessions() {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM");
        String filter = (String) cboFilter.getSelectedItem();

        List<Session> sessions;
        if ("Đang chạy".equals(filter)) {
            sessions = sessionDAO.getActiveSessions();
        } else if ("Kết thúc hôm nay".equals(filter)) {
            sessions = sessionDAO.getSessionsByDate(new java.util.Date());
        } else {
            sessions = sessionDAO.getAll();
        }

        for (Session s : sessions) {
            double hours = s.getElapsedHours();
            int h = (int) hours;
            int m = (int) ((hours - h) * 60);
            String elapsed = String.format("%dh %02dp", h, m);
            double cost = s.isRunning() ? s.calculateCost(s.getPricePerHour()) : s.getTotalCost();

            tableModel.addRow(new Object[]{
                s.getId(),
                s.getComputerName(),
                s.getCustomerName() != null ? s.getCustomerName() : "Khách vãng lai",
                sdf.format(s.getStartTime()),
                s.getEndTime() != null ? sdf.format(s.getEndTime()) : "---",
                elapsed,
                String.format("%,.0f", cost),
                s.getStatus()
            });
        }
    }

    private void endSelectedSession() {
        int row = tblSessions.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn phiên cần kết thúc!");
            return;
        }

        String status = (String) tableModel.getValueAt(row, 7);
        if (!"Đang chạy".equals(status)) {
            JOptionPane.showMessageDialog(this, "Phiên này đã kết thúc!");
            return;
        }

        int sessionId = (int) tableModel.getValueAt(row, 0);
        Session session = sessionDAO.getById(sessionId);
        if (session == null) return;

        double computerCost = session.calculateCost(session.getPricePerHour());
        double orderTotal = orderDAO.getOrderTotalBySession(sessionId);
        double totalCost = computerCost + orderTotal;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Kết thúc phiên?\n" +
                "Tiền máy: " + String.format("%,.0f đ", computerCost) + "\n" +
                "Tiền DV: " + String.format("%,.0f đ", orderTotal) + "\n" +
                "TỔNG: " + String.format("%,.0f đ", totalCost),
                "Kết thúc phiên", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            sessionDAO.endSession(sessionId, totalCost);
            computerDAO.updateStatus(session.getComputerId(), "Trống");

            if (session.getCustomerId() != null) {
                customerDAO.deductBalance(session.getCustomerId(), totalCost);
                customerDAO.addHours(session.getCustomerId(), session.getElapsedHours());
            }

            loadSessions();
            JOptionPane.showMessageDialog(this,
                    "Phiên kết thúc! Tổng: " + String.format("%,.0f đ", totalCost));
        }
    }

    private void startAutoRefresh() {
        refreshTimer = new Timer(30000, e -> {
            if (isShowing()) loadSessions();
        });
        refreshTimer.start();
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
