package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.PhienSuDungDAO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

// Giao diện thống kê doanh thu
public class PanelThongKe extends JPanel {
    private static final Color MAU_NEN = new Color(24, 21, 55);
    private static final Color MAU_THE = new Color(30, 27, 65);
    private static final Color MAU_CHU_CHINH = Color.WHITE;
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);
    private static final Color MAU_NHAN = new Color(99, 102, 241);
    private static final Color MAU_XANH = new Color(16, 185, 129);

    private JDateChooser ngayTu, ngayDen;
    private JLabel lblTongDoanhThu, lblTongPhien, lblTBDoanhThu;
    private JTable bangDoanhThu;
    private DefaultTableModel moHinhBang;
    private JPanel panelBieuDo;
    private List<Object[]> duLieuBieuDo = new ArrayList<>();
    private final PhienSuDungDAO phienSuDungDAO = new PhienSuDungDAO();

    public PanelThongKe() {
        setBackground(MAU_NEN); setLayout(new BorderLayout(0, 15)); setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        khoiTaoGiaoDien();
    }

    private void khoiTaoGiaoDien() {
        JPanel thanhTren = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); thanhTren.setOpaque(false);
        JLabel lblTu = new JLabel("Từ ngày:"); lblTu.setForeground(MAU_CHU_PHU); lblTu.setFont(new Font("Segoe UI", Font.PLAIN, 13)); thanhTren.add(lblTu);
        ngayTu = new JDateChooser(); ngayTu.setPreferredSize(new Dimension(140, 30)); ngayTu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        Calendar cal = Calendar.getInstance(); cal.add(Calendar.DAY_OF_MONTH, -7); ngayTu.setDate(cal.getTime()); thanhTren.add(ngayTu);
        JLabel lblDen = new JLabel("Đến ngày:"); lblDen.setForeground(MAU_CHU_PHU); lblDen.setFont(new Font("Segoe UI", Font.PLAIN, 13)); thanhTren.add(lblDen);
        ngayDen = new JDateChooser(); ngayDen.setPreferredSize(new Dimension(140, 30)); ngayDen.setFont(new Font("Segoe UI", Font.PLAIN, 12)); ngayDen.setDate(new Date()); thanhTren.add(ngayDen);
        JButton btnXem = new JButton("\uD83D\uDD0D Xem thống kê"); btnXem.setBackground(MAU_NHAN); btnXem.setForeground(Color.WHITE); btnXem.setFont(new Font("Segoe UI", Font.BOLD, 12)); btnXem.addActionListener(e -> taiThongKe()); thanhTren.add(btnXem);
        thanhTren.add(Box.createHorizontalStrut(15));
        JButton btn7n = new JButton("7 ngày"); btn7n.setFont(new Font("Segoe UI", Font.PLAIN, 11)); btn7n.addActionListener(e -> datKhoangNgayNhanh(7)); thanhTren.add(btn7n);
        JButton btn30n = new JButton("30 ngày"); btn30n.setFont(new Font("Segoe UI", Font.PLAIN, 11)); btn30n.addActionListener(e -> datKhoangNgayNhanh(30)); thanhTren.add(btn30n);
        JButton btnHomNay = new JButton("Hôm nay"); btnHomNay.setFont(new Font("Segoe UI", Font.PLAIN, 11)); btnHomNay.addActionListener(e -> datKhoangNgayNhanh(0)); thanhTren.add(btnHomNay);
        add(thanhTren, BorderLayout.NORTH);

        JPanel panelGiua = new JPanel(new BorderLayout(0, 15)); panelGiua.setOpaque(false);
        JPanel panelTomTat = new JPanel(new GridLayout(1, 3, 15, 0)); panelTomTat.setOpaque(false); panelTomTat.setPreferredSize(new Dimension(0, 90));
        lblTongDoanhThu = new JLabel("0đ"); lblTongPhien = new JLabel("0"); lblTBDoanhThu = new JLabel("0đ");
        panelTomTat.add(taoTheTomTat("Tổng Doanh Thu", lblTongDoanhThu, MAU_XANH));
        panelTomTat.add(taoTheTomTat("Tổng Phiên", lblTongPhien, MAU_NHAN));
        panelTomTat.add(taoTheTomTat("TB / Ngày", lblTBDoanhThu, new Color(168, 85, 247)));
        panelGiua.add(panelTomTat, BorderLayout.NORTH);

        panelBieuDo = new JPanel() { @Override protected void paintComponent(Graphics g) { super.paintComponent(g); veBieuDo((Graphics2D) g); } };
        panelBieuDo.setBackground(MAU_THE); panelBieuDo.setPreferredSize(new Dimension(0, 250)); panelBieuDo.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        panelGiua.add(panelBieuDo, BorderLayout.CENTER);
        add(panelGiua, BorderLayout.CENTER);

        String[] cot = {"Ngày", "Doanh thu (đ)", "Số phiên"};
        moHinhBang = new DefaultTableModel(cot, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        bangDoanhThu = new JTable(moHinhBang); trangTriBang(bangDoanhThu);
        JScrollPane sp = new JScrollPane(bangDoanhThu); sp.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90))); sp.getViewport().setBackground(MAU_THE); sp.setPreferredSize(new Dimension(0, 180));
        add(sp, BorderLayout.SOUTH);
    }

    public void taiThongKe() {
        Date tu = ngayTu.getDate(); Date den = ngayDen.getDate();
        if (tu == null || den == null) { JOptionPane.showMessageDialog(this, "Chọn ngày bắt đầu và kết thúc!"); return; }
        duLieuBieuDo = phienSuDungDAO.layDoanhThuTheoTungNgay(tu, den);
        moHinhBang.setRowCount(0); SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        double tongDoanhThu = 0; int tongPhien = 0;
        for (Object[] dong : duLieuBieuDo) {
            Date ngay = (Date) dong[0]; double doanhThu = (double) dong[1]; int soPhien = (int) dong[2];
            tongDoanhThu += doanhThu; tongPhien += soPhien;
            moHinhBang.addRow(new Object[]{sdf.format(ngay), String.format("%,.0f", doanhThu), soPhien});
        }
        lblTongDoanhThu.setText(String.format("%,.0f", tongDoanhThu) + "đ"); lblTongPhien.setText(String.valueOf(tongPhien));
        int soNgay = duLieuBieuDo.size(); double tb = soNgay > 0 ? tongDoanhThu / soNgay : 0; lblTBDoanhThu.setText(String.format("%,.0f", tb) + "đ");
        panelBieuDo.repaint();
    }

    private void veBieuDo(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = panelBieuDo.getWidth(); int h = panelBieuDo.getHeight(); int padding = 60; int chartW = w - padding * 2; int chartH = h - padding * 2;
        if (duLieuBieuDo.isEmpty()) {
            g2d.setColor(MAU_CHU_PHU); g2d.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            String msg = "Chọn khoảng thời gian và nhấn 'Xem thống kê'"; int msgW = g2d.getFontMetrics().stringWidth(msg); g2d.drawString(msg, (w - msgW) / 2, h / 2); return;
        }
        double maxDT = duLieuBieuDo.stream().mapToDouble(d -> (double) d[1]).max().orElse(1);
        int barW = Math.max(20, Math.min(60, chartW / duLieuBieuDo.size() - 8));
        int totalBarArea = (barW + 8) * duLieuBieuDo.size(); int startX = padding + (chartW - totalBarArea) / 2;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        for (int i = 0; i < duLieuBieuDo.size(); i++) {
            Object[] data = duLieuBieuDo.get(i); double dt = (double) data[1]; int barH = (int) (dt / maxDT * chartH);
            int x = startX + i * (barW + 8); int y = padding + chartH - barH;
            GradientPaint gp = new GradientPaint(x, y, MAU_NHAN, x, y + barH, new Color(168, 85, 247)); g2d.setPaint(gp);
            g2d.fill(new RoundRectangle2D.Double(x, y, barW, barH, 6, 6));
            g2d.setColor(MAU_CHU_CHINH); g2d.setFont(new Font("Segoe UI", Font.BOLD, 10));
            String valStr = String.format("%,.0f", dt / 1000) + "K"; int valW = g2d.getFontMetrics().stringWidth(valStr); g2d.drawString(valStr, x + (barW - valW) / 2, y - 5);
            g2d.setColor(MAU_CHU_PHU); g2d.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            String dateStr = sdf.format((Date) data[0]); int dateW = g2d.getFontMetrics().stringWidth(dateStr); g2d.drawString(dateStr, x + (barW - dateW) / 2, h - padding + 15);
        }
        g2d.setColor(new Color(50, 47, 90)); g2d.drawLine(padding, padding, padding, h - padding); g2d.drawLine(padding, h - padding, w - padding, h - padding);
    }

    private void datKhoangNgayNhanh(int soNgay) {
        Calendar cal = Calendar.getInstance(); ngayDen.setDate(cal.getTime());
        if (soNgay > 0) cal.add(Calendar.DAY_OF_MONTH, -soNgay);
        ngayTu.setDate(cal.getTime()); taiThongKe();
    }

    private JPanel taoTheTomTat(String tieuDe, JLabel lblGiaTri, Color mauNhan) {
        JPanel the = new JPanel() {
            @Override protected void paintComponent(Graphics g) { Graphics2D g2d = (Graphics2D) g; g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g2d.setColor(MAU_THE); g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 14, 14)); }
        };
        the.setOpaque(false); the.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.CENTER;
        JLabel lblTD = new JLabel(tieuDe); lblTD.setFont(new Font("Segoe UI", Font.PLAIN, 12)); lblTD.setForeground(MAU_CHU_PHU); the.add(lblTD, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(4, 0, 0, 0); lblGiaTri.setFont(new Font("Segoe UI", Font.BOLD, 20)); lblGiaTri.setForeground(mauNhan); the.add(lblGiaTri, gbc);
        return the;
    }

    private void trangTriBang(JTable bang) {
        bang.setBackground(MAU_THE); bang.setForeground(MAU_CHU_CHINH); bang.setSelectionBackground(new Color(99, 102, 241, 50)); bang.setSelectionForeground(MAU_CHU_CHINH);
        bang.setGridColor(new Color(50, 47, 90)); bang.setRowHeight(32); bang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bang.getTableHeader().setBackground(new Color(35, 32, 70)); bang.getTableHeader().setForeground(MAU_CHU_PHU); bang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); bang.getTableHeader().setPreferredSize(new Dimension(0, 32));
        bang.setShowHorizontalLines(true); bang.setShowVerticalLines(false);
        DefaultTableCellRenderer canGiua = new DefaultTableCellRenderer(); canGiua.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bang.getColumnCount(); i++) bang.getColumnModel().getColumn(i).setCellRenderer(canGiua);
    }
}
