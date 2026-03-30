package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.ComputerDAO;
import com.mycompany.quanlyquaninternet.dao.OrderDAO;
import com.mycompany.quanlyquaninternet.dao.SessionDAO;
import com.mycompany.quanlyquaninternet.entity.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.List;

public class DashboardPanel extends JPanel {
    private static final Color BG = new Color(24, 21, 55);
    private static final Color CARD_BG = new Color(30, 27, 65);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);

    private JLabel lblAvailable, lblInUse, lblRevenue, lblServiceRevenue;
    private JTable tblRecentSessions;
    private DefaultTableModel tableModel;

    private final ComputerDAO computerDAO = new ComputerDAO();
    private final SessionDAO sessionDAO = new SessionDAO();
    private final OrderDAO orderDAO = new OrderDAO();

    public DashboardPanel() {
        setBackground(BG);
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        initComponents();
        refreshData();
    }

    private void initComponents() {
        // === STAT CARDS ===
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(0, 120));

        lblAvailable = new JLabel("0");
        lblInUse = new JLabel("0");
        lblRevenue = new JLabel("0đ");
        lblServiceRevenue = new JLabel("0đ");

        statsPanel.add(createStatCard("\uD83D\uDFE2", "Máy Trống", lblAvailable, new Color(16, 185, 129)));
        statsPanel.add(createStatCard("\uD83D\uDD34", "Đang Dùng", lblInUse, new Color(239, 68, 68)));
        statsPanel.add(createStatCard("\uD83D\uDCB0", "Doanh Thu Máy", lblRevenue, new Color(59, 130, 246)));
        statsPanel.add(createStatCard("\uD83C\uDF54", "Doanh Thu DV", lblServiceRevenue, new Color(168, 85, 247)));

        add(statsPanel, BorderLayout.NORTH);

        // === RECENT SESSIONS TABLE ===
        JPanel tablePanel = new JPanel(new BorderLayout(0, 10));
        tablePanel.setOpaque(false);

        JLabel lblRecent = new JLabel("  \u23F0  Phiên đang hoạt động");
        lblRecent.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblRecent.setForeground(TEXT_PRIMARY);
        lblRecent.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        tablePanel.add(lblRecent, BorderLayout.NORTH);

        String[] columns = {"ID", "Máy", "Khách hàng", "Bắt đầu", "Thời gian", "Chi phí (đ)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblRecentSessions = new JTable(tableModel);
        tblRecentSessions.setBackground(CARD_BG);
        tblRecentSessions.setForeground(TEXT_PRIMARY);
        tblRecentSessions.setSelectionBackground(new Color(99, 102, 241, 50));
        tblRecentSessions.setSelectionForeground(TEXT_PRIMARY);
        tblRecentSessions.setGridColor(new Color(50, 47, 90));
        tblRecentSessions.setRowHeight(36);
        tblRecentSessions.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblRecentSessions.getTableHeader().setBackground(new Color(35, 32, 70));
        tblRecentSessions.getTableHeader().setForeground(TEXT_SECONDARY);
        tblRecentSessions.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblRecentSessions.getTableHeader().setPreferredSize(new Dimension(0, 36));
        tblRecentSessions.setShowHorizontalLines(true);
        tblRecentSessions.setShowVerticalLines(false);

        // Center align
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblRecentSessions.getColumnCount(); i++) {
            tblRecentSessions.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblRecentSessions);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        scrollPane.getViewport().setBackground(CARD_BG);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createStatCard(String icon, String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(CARD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 16, 16));
                // Accent bar on left
                g2d.setColor(accentColor);
                g2d.fill(new RoundRectangle2D.Double(0, 0, 5, getHeight(), 4, 4));
            }
        };
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 0, 0);

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        card.add(lblIcon, gbc);

        gbc.gridx = 1; gbc.insets = new Insets(0, 12, 0, 15);
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitle.setForeground(TEXT_SECONDARY);
        textPanel.add(lblTitle);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valueLabel.setForeground(accentColor);
        textPanel.add(valueLabel);

        card.add(textPanel, gbc);

        return card;
    }

    public void refreshData() {
        try {
            // Update stats
            int available = computerDAO.countByStatus("Trống");
            int inUse = computerDAO.countByStatus("Đang dùng");
            double todayRevenue = sessionDAO.getTodayRevenue();
            double serviceRevenue = orderDAO.getTodayOrderRevenue();

            lblAvailable.setText(String.valueOf(available));
            lblInUse.setText(String.valueOf(inUse));
            lblRevenue.setText(String.format("%,.0f", todayRevenue) + "đ");
            lblServiceRevenue.setText(String.format("%,.0f", serviceRevenue) + "đ");

            // Update active sessions table
            tableModel.setRowCount(0);
            List<Session> activeSessions = sessionDAO.getActiveSessions();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            for (Session s : activeSessions) {
                double hours = s.getElapsedHours();
                int h = (int) hours;
                int m = (int) ((hours - h) * 60);
                String elapsed = String.format("%dh %02dp", h, m);
                double cost = s.calculateCost(s.getPricePerHour());

                tableModel.addRow(new Object[]{
                    s.getId(),
                    s.getComputerName(),
                    s.getCustomerName() != null ? s.getCustomerName() : "Khách vãng lai",
                    sdf.format(s.getStartTime()),
                    elapsed,
                    String.format("%,.0f", cost)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
