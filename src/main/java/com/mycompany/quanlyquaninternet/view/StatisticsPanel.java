package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.SessionDAO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class StatisticsPanel extends JPanel {
    private static final Color BG = new Color(24, 21, 55);
    private static final Color CARD_BG = new Color(30, 27, 65);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    private static final Color ACCENT = new Color(99, 102, 241);
    private static final Color GREEN = new Color(16, 185, 129);
    private static final Color BAR_COLOR = new Color(99, 102, 241, 180);

    private JDateChooser dateFrom, dateTo;
    private JLabel lblTotalRevenue, lblTotalSessions, lblAvgRevenue;
    private JTable tblRevenue;
    private DefaultTableModel tableModel;
    private JPanel chartPanel;
    private List<Object[]> chartData = new ArrayList<>();
    private final SessionDAO sessionDAO = new SessionDAO();

    public StatisticsPanel() {
        setBackground(BG);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        initComponents();
    }

    private void initComponents() {
        // === TOP: Date range selector ===
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topBar.setOpaque(false);

        JLabel lblFrom = new JLabel("Từ ngày:");
        lblFrom.setForeground(TEXT_SECONDARY);
        lblFrom.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        topBar.add(lblFrom);

        dateFrom = new JDateChooser();
        dateFrom.setPreferredSize(new Dimension(140, 30));
        dateFrom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        // Default: 7 days ago
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        dateFrom.setDate(cal.getTime());
        topBar.add(dateFrom);

        JLabel lblTo = new JLabel("Đến ngày:");
        lblTo.setForeground(TEXT_SECONDARY);
        lblTo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        topBar.add(lblTo);

        dateTo = new JDateChooser();
        dateTo.setPreferredSize(new Dimension(140, 30));
        dateTo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateTo.setDate(new Date());
        topBar.add(dateTo);

        JButton btnLoad = new JButton("\uD83D\uDD0D Xem thống kê");
        btnLoad.setBackground(ACCENT);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLoad.addActionListener(e -> loadStatistics());
        topBar.add(btnLoad);

        // Quick filters
        topBar.add(Box.createHorizontalStrut(15));
        JButton btn7d = new JButton("7 ngày");
        btn7d.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btn7d.addActionListener(e -> setQuickRange(7));
        topBar.add(btn7d);

        JButton btn30d = new JButton("30 ngày");
        btn30d.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btn30d.addActionListener(e -> setQuickRange(30));
        topBar.add(btn30d);

        JButton btnToday = new JButton("Hôm nay");
        btnToday.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnToday.addActionListener(e -> setQuickRange(0));
        topBar.add(btnToday);

        add(topBar, BorderLayout.NORTH);

        // === CENTER: Chart + Summary ===
        JPanel centerPanel = new JPanel(new BorderLayout(0, 15));
        centerPanel.setOpaque(false);

        // Summary cards
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        summaryPanel.setOpaque(false);
        summaryPanel.setPreferredSize(new Dimension(0, 90));

        lblTotalRevenue = new JLabel("0đ");
        lblTotalSessions = new JLabel("0");
        lblAvgRevenue = new JLabel("0đ");

        summaryPanel.add(createSummaryCard("Tổng Doanh Thu", lblTotalRevenue, GREEN));
        summaryPanel.add(createSummaryCard("Tổng Phiên", lblTotalSessions, ACCENT));
        summaryPanel.add(createSummaryCard("TB / Ngày", lblAvgRevenue, new Color(168, 85, 247)));

        centerPanel.add(summaryPanel, BorderLayout.NORTH);

        // Chart
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChart((Graphics2D) g);
            }
        };
        chartPanel.setBackground(CARD_BG);
        chartPanel.setPreferredSize(new Dimension(0, 250));
        chartPanel.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        centerPanel.add(chartPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // === BOTTOM: Revenue table ===
        String[] columns = {"Ngày", "Doanh thu (đ)", "Số phiên"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblRevenue = new JTable(tableModel);
        styleTable(tblRevenue);

        JScrollPane scrollPane = new JScrollPane(tblRevenue);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        scrollPane.getViewport().setBackground(CARD_BG);
        scrollPane.setPreferredSize(new Dimension(0, 180));
        add(scrollPane, BorderLayout.SOUTH);
    }

    public void loadStatistics() {
        Date from = dateFrom.getDate();
        Date to = dateTo.getDate();
        if (from == null || to == null) {
            JOptionPane.showMessageDialog(this, "Chọn ngày bắt đầu và kết thúc!");
            return;
        }

        chartData = sessionDAO.getDailyRevenue(from, to);
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        double totalRevenue = 0;
        int totalSessions = 0;

        for (Object[] row : chartData) {
            Date day = (Date) row[0];
            double revenue = (double) row[1];
            int sessions = (int) row[2];

            totalRevenue += revenue;
            totalSessions += sessions;

            tableModel.addRow(new Object[]{
                sdf.format(day),
                String.format("%,.0f", revenue),
                sessions
            });
        }

        lblTotalRevenue.setText(String.format("%,.0f", totalRevenue) + "đ");
        lblTotalSessions.setText(String.valueOf(totalSessions));

        int days = chartData.size();
        double avg = days > 0 ? totalRevenue / days : 0;
        lblAvgRevenue.setText(String.format("%,.0f", avg) + "đ");

        chartPanel.repaint();
    }

    private void drawChart(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = chartPanel.getWidth();
        int h = chartPanel.getHeight();
        int padding = 60;
        int chartW = w - padding * 2;
        int chartH = h - padding * 2;

        if (chartData.isEmpty()) {
            g2d.setColor(TEXT_SECONDARY);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            String msg = "Chọn khoảng thời gian và nhấn 'Xem thống kê'";
            int msgW = g2d.getFontMetrics().stringWidth(msg);
            g2d.drawString(msg, (w - msgW) / 2, h / 2);
            return;
        }

        // Find max revenue
        double maxRevenue = chartData.stream()
                .mapToDouble(d -> (double) d[1])
                .max().orElse(1);

        // Draw bars
        int barWidth = Math.max(20, Math.min(60, chartW / chartData.size() - 8));
        int totalBarArea = (barWidth + 8) * chartData.size();
        int startX = padding + (chartW - totalBarArea) / 2;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

        for (int i = 0; i < chartData.size(); i++) {
            Object[] data = chartData.get(i);
            double revenue = (double) data[1];
            int barH = (int) (revenue / maxRevenue * chartH);
            int x = startX + i * (barWidth + 8);
            int y = padding + chartH - barH;

            // Bar gradient
            GradientPaint gp = new GradientPaint(x, y, ACCENT, x, y + barH, new Color(168, 85, 247));
            g2d.setPaint(gp);
            g2d.fill(new RoundRectangle2D.Double(x, y, barWidth, barH, 6, 6));

            // Value label
            g2d.setColor(TEXT_PRIMARY);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 10));
            String valStr = String.format("%,.0f", revenue / 1000) + "K";
            int valW = g2d.getFontMetrics().stringWidth(valStr);
            g2d.drawString(valStr, x + (barWidth - valW) / 2, y - 5);

            // Date label
            g2d.setColor(TEXT_SECONDARY);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            String dateStr = sdf.format((Date) data[0]);
            int dateW = g2d.getFontMetrics().stringWidth(dateStr);
            g2d.drawString(dateStr, x + (barWidth - dateW) / 2, h - padding + 15);
        }

        // Axis lines
        g2d.setColor(new Color(50, 47, 90));
        g2d.drawLine(padding, padding, padding, h - padding);
        g2d.drawLine(padding, h - padding, w - padding, h - padding);
    }

    private void setQuickRange(int days) {
        Calendar cal = Calendar.getInstance();
        dateTo.setDate(cal.getTime());
        if (days > 0) {
            cal.add(Calendar.DAY_OF_MONTH, -days);
        }
        dateFrom.setDate(cal.getTime());
        loadStatistics();
    }

    private JPanel createSummaryCard(String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(CARD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 14, 14));
            }
        };
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitle.setForeground(TEXT_SECONDARY);
        card.add(lblTitle, gbc);

        gbc.gridy = 1; gbc.insets = new Insets(4, 0, 0, 0);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueLabel.setForeground(accentColor);
        card.add(valueLabel, gbc);

        return card;
    }

    private void styleTable(JTable table) {
        table.setBackground(CARD_BG);
        table.setForeground(TEXT_PRIMARY);
        table.setSelectionBackground(new Color(99, 102, 241, 50));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setGridColor(new Color(50, 47, 90));
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setBackground(new Color(35, 32, 70));
        table.getTableHeader().setForeground(TEXT_SECONDARY);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setPreferredSize(new Dimension(0, 32));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
