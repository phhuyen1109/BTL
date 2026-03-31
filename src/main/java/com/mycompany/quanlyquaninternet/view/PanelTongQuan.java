package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.MayTinhDAO;
import com.mycompany.quanlyquaninternet.dao.DonHangDAO;
import com.mycompany.quanlyquaninternet.dao.PhienSuDungDAO;
import com.mycompany.quanlyquaninternet.entity.PhienSuDung;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.List;

// Giao diện tổng quan (Dashboard)
public class PanelTongQuan extends JPanel {
    private static final Color MAU_NEN = new Color(24, 21, 55);
    private static final Color MAU_THE = new Color(30, 27, 65);
    private static final Color MAU_CHU_CHINH = Color.WHITE;
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);

    private JLabel lblMayTrong, lblDangDung, lblDoanhThu, lblDoanhThuDV;
    private JTable bangPhienHoatDong;
    private DefaultTableModel moHinhBang;

    private final MayTinhDAO mayTinhDAO = new MayTinhDAO();
    private final PhienSuDungDAO phienSuDungDAO = new PhienSuDungDAO();
    private final DonHangDAO donHangDAO = new DonHangDAO();

    public PanelTongQuan() {
        setBackground(MAU_NEN);
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        khoiTaoGiaoDien();
        lamMoiDuLieu();
    }

    private void khoiTaoGiaoDien() {
        JPanel panelThongKe = new JPanel(new GridLayout(1, 4, 15, 0));
        panelThongKe.setOpaque(false);
        panelThongKe.setPreferredSize(new Dimension(0, 120));

        lblMayTrong = new JLabel("0");
        lblDangDung = new JLabel("0");
        lblDoanhThu = new JLabel("0đ");
        lblDoanhThuDV = new JLabel("0đ");

        panelThongKe.add(taoTheThongKe("\uD83D\uDFE2", "Máy Trống", lblMayTrong, new Color(16, 185, 129)));
        panelThongKe.add(taoTheThongKe("\uD83D\uDD34", "Đang Dùng", lblDangDung, new Color(239, 68, 68)));
        panelThongKe.add(taoTheThongKe("\uD83D\uDCB0", "Doanh Thu Máy", lblDoanhThu, new Color(59, 130, 246)));
        panelThongKe.add(taoTheThongKe("\uD83C\uDF54", "Doanh Thu DV", lblDoanhThuDV, new Color(168, 85, 247)));
        add(panelThongKe, BorderLayout.NORTH);

        JPanel panelBang = new JPanel(new BorderLayout(0, 10));
        panelBang.setOpaque(false);

        JLabel lblPhienGanDay = new JLabel("  \u23F0  Phiên đang hoạt động");
        lblPhienGanDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPhienGanDay.setForeground(MAU_CHU_CHINH);
        lblPhienGanDay.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        panelBang.add(lblPhienGanDay, BorderLayout.NORTH);

        String[] cotBang = {"Mã", "Máy", "Khách hàng", "Bắt đầu", "Thời gian", "Chi phí (đ)"};
        moHinhBang = new DefaultTableModel(cotBang, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        bangPhienHoatDong = new JTable(moHinhBang);
        bangPhienHoatDong.setBackground(MAU_THE);
        bangPhienHoatDong.setForeground(MAU_CHU_CHINH);
        bangPhienHoatDong.setSelectionBackground(new Color(99, 102, 241, 50));
        bangPhienHoatDong.setSelectionForeground(MAU_CHU_CHINH);
        bangPhienHoatDong.setGridColor(new Color(50, 47, 90));
        bangPhienHoatDong.setRowHeight(36);
        bangPhienHoatDong.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bangPhienHoatDong.getTableHeader().setBackground(new Color(35, 32, 70));
        bangPhienHoatDong.getTableHeader().setForeground(MAU_CHU_PHU);
        bangPhienHoatDong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        bangPhienHoatDong.getTableHeader().setPreferredSize(new Dimension(0, 36));
        bangPhienHoatDong.setShowHorizontalLines(true);
        bangPhienHoatDong.setShowVerticalLines(false);

        DefaultTableCellRenderer canGiua = new DefaultTableCellRenderer();
        canGiua.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bangPhienHoatDong.getColumnCount(); i++) {
            bangPhienHoatDong.getColumnModel().getColumn(i).setCellRenderer(canGiua);
        }

        JScrollPane scrollPane = new JScrollPane(bangPhienHoatDong);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        scrollPane.getViewport().setBackground(MAU_THE);
        panelBang.add(scrollPane, BorderLayout.CENTER);
        add(panelBang, BorderLayout.CENTER);
    }

    private JPanel taoTheThongKe(String icon, String tieuDe, JLabel lblGiaTri, Color mauNhan) {
        JPanel the = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(MAU_THE);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 16, 16));
                g2d.setColor(mauNhan);
                g2d.fill(new RoundRectangle2D.Double(0, 0, 5, getHeight(), 4, 4));
            }
        };
        the.setOpaque(false);
        the.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 0, 0);
        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        the.add(lblIcon, gbc);

        gbc.gridx = 1; gbc.insets = new Insets(0, 12, 0, 15);
        JPanel panelText = new JPanel();
        panelText.setOpaque(false);
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS));
        JLabel lblTieuDe = new JLabel(tieuDe);
        lblTieuDe.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTieuDe.setForeground(MAU_CHU_PHU);
        panelText.add(lblTieuDe);
        lblGiaTri.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblGiaTri.setForeground(mauNhan);
        panelText.add(lblGiaTri);
        the.add(panelText, gbc);
        return the;
    }

    public void lamMoiDuLieu() {
        try {
            int mayTrong = mayTinhDAO.demTheoTrangThai("Trống");
            int dangDung = mayTinhDAO.demTheoTrangThai("Đang dùng");
            double doanhThuHomNay = phienSuDungDAO.layDoanhThuHomNay();
            double doanhThuDV = donHangDAO.layDoanhThuDVHomNay();

            lblMayTrong.setText(String.valueOf(mayTrong));
            lblDangDung.setText(String.valueOf(dangDung));
            lblDoanhThu.setText(String.format("%,.0f", doanhThuHomNay) + "đ");
            lblDoanhThuDV.setText(String.format("%,.0f", doanhThuDV) + "đ");

            moHinhBang.setRowCount(0);
            List<PhienSuDung> phienDangChay = phienSuDungDAO.layPhienDangChay();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            for (PhienSuDung p : phienDangChay) {
                double soGio = p.tinhSoGio();
                int h = (int) soGio;
                int m = (int) ((soGio - h) * 60);
                String thoiGian = String.format("%dh %02dp", h, m);
                double chiPhi = p.tinhTien(p.getGiaMoiGio());

                moHinhBang.addRow(new Object[]{
                    p.getMa(),
                    p.getTenMayTinh(),
                    p.getTenKhach() != null ? p.getTenKhach() : "Khách vãng lai",
                    sdf.format(p.getGioBatDau()),
                    thoiGian,
                    String.format("%,.0f", chiPhi)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
