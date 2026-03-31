package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.MayTinhDAO;
import com.mycompany.quanlyquaninternet.dao.KhachHangDAO;
import com.mycompany.quanlyquaninternet.dao.DonHangDAO;
import com.mycompany.quanlyquaninternet.dao.PhienSuDungDAO;
import com.mycompany.quanlyquaninternet.entity.PhienSuDung;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

// Giao diện quản lý phiên sử dụng
public class PanelPhienSuDung extends JPanel {
    private static final Color MAU_NEN = new Color(24, 21, 55);
    private static final Color MAU_THE = new Color(30, 27, 65);
    private static final Color MAU_CHU_CHINH = Color.WHITE;
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);
    private static final Color MAU_XANH = new Color(16, 185, 129);
    private static final Color MAU_DO = new Color(239, 68, 68);

    private JTable bangPhien;
    private DefaultTableModel moHinhBang;
    private JComboBox<String> cboBoLoc;
    private Timer boHenLamMoi;
    private final PhienSuDungDAO phienSuDungDAO = new PhienSuDungDAO();
    private final MayTinhDAO mayTinhDAO = new MayTinhDAO();
    private final KhachHangDAO khachHangDAO = new KhachHangDAO();
    private final DonHangDAO donHangDAO = new DonHangDAO();

    public PanelPhienSuDung() {
        setBackground(MAU_NEN);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        khoiTaoGiaoDien();
        taiPhienSuDung();
        batDauTuDongLamMoi();
    }

    private void khoiTaoGiaoDien() {
        JPanel thanhTren = new JPanel(new BorderLayout()); thanhTren.setOpaque(false);
        JPanel panelLoc = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); panelLoc.setOpaque(false);
        JLabel lblLoc = new JLabel("Hiển thị: "); lblLoc.setForeground(MAU_CHU_PHU); lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panelLoc.add(lblLoc);
        cboBoLoc = new JComboBox<>(new String[]{"Đang chạy", "Tất cả", "Kết thúc hôm nay"});
        cboBoLoc.setFont(new Font("Segoe UI", Font.PLAIN, 13)); cboBoLoc.addActionListener(e -> taiPhienSuDung()); panelLoc.add(cboBoLoc);
        thanhTren.add(panelLoc, BorderLayout.WEST);

        JPanel panelHD = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0)); panelHD.setOpaque(false);
        JButton btnKetThuc = new JButton("⏹ Kết Thúc Phiên"); btnKetThuc.setBackground(MAU_DO); btnKetThuc.setForeground(Color.WHITE); btnKetThuc.setFont(new Font("Segoe UI", Font.BOLD, 12)); btnKetThuc.addActionListener(e -> ketThucPhienDaChon()); panelHD.add(btnKetThuc);
        JButton btnLamMoi = new JButton("\u21BB Làm mới"); btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 12)); btnLamMoi.addActionListener(e -> taiPhienSuDung()); panelHD.add(btnLamMoi);
        thanhTren.add(panelHD, BorderLayout.EAST);
        add(thanhTren, BorderLayout.NORTH);

        String[] cot = {"Mã", "Máy", "Khách hàng", "Bắt đầu", "Kết thúc", "Thời gian", "Chi phí (đ)", "Trạng thái"};
        moHinhBang = new DefaultTableModel(cot, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        bangPhien = new JTable(moHinhBang); trangTriBang(bangPhien);

        bangPhien.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                Component comp = super.getTableCellRendererComponent(t, v, s, f, r, c);
                setForeground("Đang chạy".equals(v) ? MAU_XANH : MAU_CHU_PHU); setHorizontalAlignment(CENTER);
                setBackground(s ? new Color(99, 102, 241, 50) : MAU_THE); return comp;
            }
        });

        JScrollPane scrollPane = new JScrollPane(bangPhien); scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90))); scrollPane.getViewport().setBackground(MAU_THE);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelDuoi = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5)); panelDuoi.setOpaque(false); panelDuoi.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        JLabel lblInfo = new JLabel("\u2139  Click đúp để xem chi tiết phiên  |  Tự động cập nhật mỗi 30 giây"); lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12)); lblInfo.setForeground(MAU_CHU_PHU); panelDuoi.add(lblInfo);
        add(panelDuoi, BorderLayout.SOUTH);
    }

    public void taiPhienSuDung() {
        moHinhBang.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM");
        String boLoc = (String) cboBoLoc.getSelectedItem();
        List<PhienSuDung> dsPhien;
        if ("Đang chạy".equals(boLoc)) dsPhien = phienSuDungDAO.layPhienDangChay();
        else if ("Kết thúc hôm nay".equals(boLoc)) dsPhien = phienSuDungDAO.layTheoNgay(new java.util.Date());
        else dsPhien = phienSuDungDAO.layTatCa();

        for (PhienSuDung p : dsPhien) {
            double soGio = p.tinhSoGio(); int h = (int) soGio; int m = (int) ((soGio - h) * 60);
            double chiPhi = p.dangChay() ? p.tinhTien(p.getGiaMoiGio()) : p.getTongTien();
            moHinhBang.addRow(new Object[]{p.getMa(), p.getTenMayTinh(), p.getTenKhach() != null ? p.getTenKhach() : "Khách vãng lai",
                sdf.format(p.getGioBatDau()), p.getGioKetThuc() != null ? sdf.format(p.getGioKetThuc()) : "---",
                String.format("%dh %02dp", h, m), String.format("%,.0f", chiPhi), p.getTrangThai()});
        }
    }

    private void ketThucPhienDaChon() {
        int dong = bangPhien.getSelectedRow();
        if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn phiên cần kết thúc!"); return; }
        String trangThai = (String) moHinhBang.getValueAt(dong, 7);
        if (!"Đang chạy".equals(trangThai)) { JOptionPane.showMessageDialog(this, "Phiên này đã kết thúc!"); return; }
        int maPhien = (int) moHinhBang.getValueAt(dong, 0);
        PhienSuDung phien = phienSuDungDAO.layTheoMa(maPhien); if (phien == null) return;
        double tienMay = phien.tinhTien(phien.getGiaMoiGio()); double tienDV = donHangDAO.layTongTienDonHang(maPhien); double tongTien = tienMay + tienDV;
        int xacNhan = JOptionPane.showConfirmDialog(this, "Kết thúc phiên?\nTiền máy: " + String.format("%,.0f đ", tienMay) + "\nTiền DV: " + String.format("%,.0f đ", tienDV) + "\nTỔNG: " + String.format("%,.0f đ", tongTien), "Kết thúc phiên", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            phienSuDungDAO.ketThucPhien(maPhien, tongTien); mayTinhDAO.capNhatTrangThai(phien.getMaMayTinh(), "Trống");
            if (phien.getMaKhachHang() != null) { khachHangDAO.truTien(phien.getMaKhachHang(), tongTien); khachHangDAO.congGio(phien.getMaKhachHang(), phien.tinhSoGio()); }
            taiPhienSuDung(); JOptionPane.showMessageDialog(this, "Phiên kết thúc! Tổng: " + String.format("%,.0f đ", tongTien));
        }
    }

    private void batDauTuDongLamMoi() { boHenLamMoi = new Timer(30000, e -> { if (isShowing()) taiPhienSuDung(); }); boHenLamMoi.start(); }

    private void trangTriBang(JTable bang) {
        bang.setBackground(MAU_THE); bang.setForeground(MAU_CHU_CHINH); bang.setSelectionBackground(new Color(99, 102, 241, 50)); bang.setSelectionForeground(MAU_CHU_CHINH);
        bang.setGridColor(new Color(50, 47, 90)); bang.setRowHeight(36); bang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bang.getTableHeader().setBackground(new Color(35, 32, 70)); bang.getTableHeader().setForeground(MAU_CHU_PHU); bang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); bang.getTableHeader().setPreferredSize(new Dimension(0, 36));
        bang.setShowHorizontalLines(true); bang.setShowVerticalLines(false);
        DefaultTableCellRenderer canGiua = new DefaultTableCellRenderer(); canGiua.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bang.getColumnCount() - 1; i++) bang.getColumnModel().getColumn(i).setCellRenderer(canGiua);
    }
}
