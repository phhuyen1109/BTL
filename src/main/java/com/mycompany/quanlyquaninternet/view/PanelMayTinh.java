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

// Giao diện quản lý máy tính
public class PanelMayTinh extends JPanel {
    private static final Color MAU_NEN = new Color(24, 21, 55);
    private static final Color MAU_THE = new Color(30, 27, 65);
    private static final Color MAU_CHU_CHINH = Color.WHITE;
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);
    private static final Color MAU_XANH = new Color(16, 185, 129);
    private static final Color MAU_DO = new Color(239, 68, 68);
    private static final Color MAU_VANG = new Color(245, 158, 11);
    private static final Color MAU_NHAN = new Color(99, 102, 241);
    private static final Color MAU_VIP = new Color(251, 191, 36);

    private JPanel panelLuoi;
    private final MayTinhDAO mayTinhDAO = new MayTinhDAO();
    private final PhienSuDungDAO phienSuDungDAO = new PhienSuDungDAO();
    private final KhachHangDAO khachHangDAO = new KhachHangDAO();
    private final DoAnUongDAO doAnUongDAO = new DoAnUongDAO();
    private final DonHangDAO donHangDAO = new DonHangDAO();
    private GiaoDienChinh giaoDienChinh;

    public PanelMayTinh() {
        setBackground(MAU_NEN);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        khoiTaoGiaoDien();
    }

    public void setGiaoDienChinh(GiaoDienChinh giaoDienChinh) {
        this.giaoDienChinh = giaoDienChinh;
    }

    private void khoiTaoGiaoDien() {
        JPanel thanhTren = new JPanel(new BorderLayout());
        thanhTren.setOpaque(false);

        JPanel panelLocTT = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelLocTT.setOpaque(false);
        String[] boLoc = {"Tất cả", "Trống", "Đang dùng", "Bảo trì"};
        for (String loc : boLoc) {
            JButton btn = taoNutLoc(loc);
            panelLocTT.add(btn);
        }
        thanhTren.add(panelLocTT, BorderLayout.WEST);

        JPanel panelHanhDong = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelHanhDong.setOpaque(false);
        JButton btnLamMoi = taoNutHanhDong("\u21BB Làm mới", MAU_NHAN);
        btnLamMoi.addActionListener(e -> lamMoiMayTinh());
        panelHanhDong.add(btnLamMoi);
        JButton btnThem = taoNutHanhDong("+ Thêm Máy", MAU_XANH);
        btnThem.addActionListener(e -> hienThiDialogThemMay());
        panelHanhDong.add(btnThem);
        thanhTren.add(panelHanhDong, BorderLayout.EAST);
        add(thanhTren, BorderLayout.NORTH);

        panelLuoi = new JPanel(new GridLayout(0, 5, 12, 12));
        panelLuoi.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(panelLuoi);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(MAU_NEN);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        lamMoiMayTinh();
    }

    public void lamMoiMayTinh() {
        panelLuoi.removeAll();
        List<MayTinh> dsMay = mayTinhDAO.layTatCa();
        for (MayTinh mt : dsMay) {
            panelLuoi.add(taoTheMayTinh(mt));
        }
        panelLuoi.revalidate();
        panelLuoi.repaint();
    }

    private JPanel taoTheMayTinh(MayTinh may) {
        Color mauTrangThai = may.laTrong() ? MAU_XANH : may.dangDung() ? MAU_DO : MAU_VANG;

        JPanel the = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(MAU_THE);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 14, 14));
                g2d.setColor(mauTrangThai);
                g2d.setStroke(new BasicStroke(2f));
                g2d.draw(new RoundRectangle2D.Double(1, 1, getWidth()-2, getHeight()-2, 14, 14));
            }
        };
        the.setOpaque(false);
        the.setPreferredSize(new Dimension(180, 140));
        the.setLayout(new GridBagLayout());
        the.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(8, 0, 2, 0);
        JLabel lblIcon = new JLabel("\uD83D\uDDA5");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        the.add(lblIcon, gbc);

        gbc.gridy = 1; gbc.insets = new Insets(2, 0, 2, 0);
        JLabel lblTen = new JLabel(may.getTen());
        lblTen.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTen.setForeground(MAU_CHU_CHINH);
        the.add(lblTen, gbc);

        gbc.gridy = 2;
        JLabel lblLoai = new JLabel(may.getLoai());
        lblLoai.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblLoai.setForeground(may.laVIP() ? MAU_VIP : MAU_CHU_PHU);
        the.add(lblLoai, gbc);

        gbc.gridy = 3; gbc.insets = new Insets(2, 0, 8, 0);
        JLabel lblTrangThai = new JLabel("● " + may.getTrangThai());
        lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTrangThai.setForeground(mauTrangThai);
        the.add(lblTrangThai, gbc);

        the.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { xuLyClickMay(may); }
        });
        return the;
    }

    private void xuLyClickMay(MayTinh may) {
        if (may.laTrong()) {
            hienThiDialogBatDauPhien(may);
        } else if (may.dangDung()) {
            hienThiDialogThongTinPhien(may);
        } else {
            int xacNhan = JOptionPane.showConfirmDialog(this,
                    may.getTen() + " đang bảo trì.\nĐặt lại trạng thái Trống?",
                    "Bảo trì", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                mayTinhDAO.capNhatTrangThai(may.getMa(), "Trống");
                lamMoiMayTinh();
            }
        }
    }

    private void hienThiDialogBatDauPhien(MayTinh may) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Bắt Đầu Phiên - " + may.getTen(), true);
        dialog.setSize(420, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(MAU_THE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.gridx = 0; gbc.gridwidth = 2;

        JLabel lblTieuDe = new JLabel("\uD83D\uDDA5  " + may.getTen() + " (" + may.getLoai() + ")");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTieuDe.setForeground(MAU_CHU_CHINH);
        gbc.gridy = 0;
        panel.add(lblTieuDe, gbc);

        JLabel lblGia = new JLabel("Giá: " + String.format("%,.0f", may.getGiaMoiGio()) + " đ/giờ");
        lblGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblGia.setForeground(may.laVIP() ? MAU_VIP : MAU_XANH);
        gbc.gridy = 1;
        panel.add(lblGia, gbc);

        gbc.gridy = 2; gbc.insets = new Insets(15, 0, 4, 0);
        JLabel lblKhach = new JLabel("Khách hàng:");
        lblKhach.setForeground(MAU_CHU_PHU);
        lblKhach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblKhach, gbc);

        List<KhachHang> dsKhach = khachHangDAO.layTatCa();
        JComboBox<String> cboKhach = new JComboBox<>();
        cboKhach.addItem("-- Khách vãng lai --");
        for (KhachHang kh : dsKhach) {
            cboKhach.addItem(kh.getMa() + " - " + kh.getTen() + " (" + String.format("%,.0f", kh.getSoDu()) + "đ)");
        }
        cboKhach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 6, 0);
        panel.add(cboKhach, gbc);

        gbc.gridy = 4; gbc.insets = new Insets(6, 0, 4, 0);
        JLabel lblTen = new JLabel("Hoặc nhập tên khách:");
        lblTen.setForeground(MAU_CHU_PHU);
        lblTen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblTen, gbc);

        JTextField txtTen = new JTextField();
        txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridy = 5; gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(txtTen, gbc);

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelNut.setOpaque(false);
        JButton btnHuy = new JButton("Hủy");
        btnHuy.addActionListener(e -> dialog.dispose());
        JButton btnBatDau = new JButton("▶ Bắt Đầu");
        btnBatDau.setBackground(MAU_XANH);
        btnBatDau.setForeground(Color.WHITE);
        btnBatDau.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBatDau.addActionListener(e -> {
            String tenKhach = txtTen.getText().trim();
            Integer maKhach = null;
            int chonIdx = cboKhach.getSelectedIndex();
            if (chonIdx > 0) {
                KhachHang khDuocChon = dsKhach.get(chonIdx - 1);
                maKhach = khDuocChon.getMa();
                tenKhach = khDuocChon.getTen();
            }
            if (tenKhach.isEmpty() && maKhach == null) tenKhach = "Khách vãng lai";

            PhienSuDung phien = new PhienSuDung(may.getMa(), tenKhach, maKhach);
            int maPhien = phienSuDungDAO.batDauPhien(phien);
            if (maPhien > 0) {
                mayTinhDAO.capNhatTrangThai(may.getMa(), "Đang dùng");
                lamMoiMayTinh();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Đã bắt đầu phiên cho " + may.getTen() + "!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelNut.add(btnHuy);
        panelNut.add(btnBatDau);
        gbc.gridy = 6; gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(panelNut, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void hienThiDialogThongTinPhien(MayTinh may) {
        PhienSuDung phien = phienSuDungDAO.layPhienDangChayTheoMay(may.getMa());
        if (phien == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiên hoạt động!");
            mayTinhDAO.capNhatTrangThai(may.getMa(), "Trống");
            lamMoiMayTinh();
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Phiên - " + may.getTen(), true);
        dialog.setSize(480, 520);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(MAU_THE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridwidth = 2;

        JLabel lblTieuDe = new JLabel("\uD83D\uDDA5  " + may.getTen() + " - ĐANG SỬ DỤNG");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTieuDe.setForeground(MAU_DO);
        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(lblTieuDe, gbc);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        double soGio = phien.tinhSoGio();
        int h = (int) soGio; int m = (int) ((soGio - h) * 60); int s = (int) (((soGio - h) * 60 - m) * 60);
        double tienMay = phien.tinhTien(may.getGiaMoiGio());
        double tienDV = donHangDAO.layTongTienDonHang(phien.getMa());

        themDongThongTin(panel, gbc, 1, "Khách hàng:", phien.getTenKhach());
        themDongThongTin(panel, gbc, 2, "Bắt đầu:", sdf.format(phien.getGioBatDau()));
        themDongThongTin(panel, gbc, 3, "Thời gian:", String.format("%dh %02dp %02ds", h, m, s));
        themDongThongTin(panel, gbc, 4, "Giá/giờ:", String.format("%,.0f đ", may.getGiaMoiGio()));
        themDongThongTin(panel, gbc, 5, "Tiền máy:", String.format("%,.0f đ", tienMay));
        themDongThongTin(panel, gbc, 6, "Tiền DV:", String.format("%,.0f đ", tienDV));

        gbc.gridy = 7; gbc.insets = new Insets(10, 0, 10, 0);
        JLabel lblTong = new JLabel("TỔNG: " + String.format("%,.0f đ", tienMay + tienDV));
        lblTong.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTong.setForeground(MAU_VIP);
        lblTong.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTong, gbc);

        if (phien.getMaKhachHang() != null) {
            KhachHang kh = khachHangDAO.layTheoMa(phien.getMaKhachHang());
            if (kh != null) {
                gbc.gridy = 8; gbc.insets = new Insets(0, 0, 10, 0);
                JLabel lblSoDu = new JLabel("Số dư: " + String.format("%,.0f đ", kh.getSoDu()));
                lblSoDu.setFont(new Font("Segoe UI", Font.BOLD, 14));
                lblSoDu.setForeground(kh.getSoDu() >= tienMay + tienDV ? MAU_XANH : MAU_DO);
                lblSoDu.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(lblSoDu, gbc);
            }
        }

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelNut.setOpaque(false);

        JButton btnGoiMon = new JButton("\uD83C\uDF54 Gọi Món");
        btnGoiMon.setBackground(MAU_NHAN);
        btnGoiMon.setForeground(Color.WHITE);
        btnGoiMon.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGoiMon.addActionListener(e -> { dialog.dispose(); hienThiDialogGoiMon(may, phien); });

        JButton btnBaoTri = new JButton("\uD83D\uDD27 Bảo Trì");
        btnBaoTri.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnBaoTri.addActionListener(e -> { ketThucVaDatTrangThai(phien, may, tienMay, tienDV, "Bảo trì"); dialog.dispose(); });

        JButton btnKetThuc = new JButton("⏹ Kết Thúc");
        btnKetThuc.setBackground(MAU_DO);
        btnKetThuc.setForeground(Color.WHITE);
        btnKetThuc.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnKetThuc.addActionListener(e -> { ketThucVaDatTrangThai(phien, may, tienMay, tienDV, "Trống"); dialog.dispose(); });

        JButton btnDong = new JButton("Đóng");
        btnDong.addActionListener(e -> dialog.dispose());

        panelNut.add(btnGoiMon); panelNut.add(btnBaoTri); panelNut.add(btnKetThuc); panelNut.add(btnDong);
        gbc.gridy = 9; gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(panelNut, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void ketThucVaDatTrangThai(PhienSuDung phien, MayTinh may, double tienMay, double tienDV, String trangThaiMoi) {
        double tongTien = tienMay + tienDV;
        phienSuDungDAO.ketThucPhien(phien.getMa(), tongTien);
        mayTinhDAO.capNhatTrangThai(may.getMa(), trangThaiMoi);

        if (phien.getMaKhachHang() != null) {
            khachHangDAO.truTien(phien.getMaKhachHang(), tongTien);
            double soGio = phien.tinhSoGio();
            khachHangDAO.congGio(phien.getMaKhachHang(), soGio);
            int diem = (int) soGio;
            if (diem > 0) khachHangDAO.congDiem(phien.getMaKhachHang(), diem);
        }

        lamMoiMayTinh();
        JOptionPane.showMessageDialog(this,
                "Phiên kết thúc!\nTổng chi phí: " + String.format("%,.0f đ", tongTien) +
                (phien.getMaKhachHang() != null ? "\nĐiểm tích lũy: +" + (int) phien.tinhSoGio() + " ⭐" : ""),
                "Kết thúc phiên", JOptionPane.INFORMATION_MESSAGE);
    }

    private void hienThiDialogGoiMon(MayTinh may, PhienSuDung phien) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Gọi Món - " + may.getTen(), true);
        dialog.setSize(550, 500);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(MAU_THE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        List<DoAnUong> dsMenu = doAnUongDAO.layConHang();
        String[] cot = {"Chọn", "Tên", "Giá (đ)", "SL", "Loại"};
        Object[][] duLieu = new Object[dsMenu.size()][5];
        for (int i = 0; i < dsMenu.size(); i++) {
            DoAnUong dau = dsMenu.get(i);
            duLieu[i] = new Object[]{false, dau.getTen(), String.format("%,.0f", dau.getGia()), 1, dau.getPhanLoai()};
        }

        javax.swing.table.DefaultTableModel moHinhMenu = new javax.swing.table.DefaultTableModel(cot, 0) {
            @Override public Class<?> getColumnClass(int col) { return col == 0 ? Boolean.class : col == 3 ? Integer.class : String.class; }
            @Override public boolean isCellEditable(int row, int col) { return col == 0 || col == 3; }
        };
        for (Object[] row : duLieu) moHinhMenu.addRow(row);

        JTable bangMenu = new JTable(moHinhMenu);
        bangMenu.setRowHeight(32);
        bangMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bangMenu.getColumnModel().getColumn(0).setPreferredWidth(40);
        bangMenu.getColumnModel().getColumn(3).setPreferredWidth(40);

        JScrollPane sp = new JScrollPane(bangMenu);
        panel.add(sp, BorderLayout.CENTER);

        JPanel panelDuoi = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelDuoi.setOpaque(false);

        JButton btnXacNhan = new JButton("✓ Xác Nhận Đơn");
        btnXacNhan.setBackground(MAU_XANH);
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXacNhan.addActionListener(e -> {
            DonHang donHang = new DonHang(phien.getMa(), may.getMa());
            for (int i = 0; i < moHinhMenu.getRowCount(); i++) {
                boolean daChon = (boolean) moHinhMenu.getValueAt(i, 0);
                if (daChon) {
                    DoAnUong dau = dsMenu.get(i);
                    int sl = (int) moHinhMenu.getValueAt(i, 3);
                    if (sl > 0) donHang.themMon(new ChiTietDonHang(dau.getMa(), dau.getTen(), sl, dau.getGia()));
                }
            }
            if (donHang.getDanhSachMon().isEmpty()) { JOptionPane.showMessageDialog(dialog, "Chưa chọn món nào!"); return; }
            donHang.tinhLaiTong();
            donHangDAO.taoDonHang(donHang);
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Đã đặt " + donHang.getDanhSachMon().size() + " món!\nTổng: " + String.format("%,.0f đ", donHang.getTongGia()), "Đặt món thành công", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnHuy = new JButton("Hủy");
        btnHuy.addActionListener(e -> dialog.dispose());
        panelDuoi.add(btnHuy); panelDuoi.add(btnXacNhan);
        panel.add(panelDuoi, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void hienThiDialogThemMay() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm Máy Tính", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(MAU_THE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridwidth = 2; gbc.insets = new Insets(4, 0, 4, 0);

        JTextField txtTen = new JTextField();
        JComboBox<String> cboLoai = new JComboBox<>(new String[]{"Thường", "VIP"});
        JTextField txtGia = new JTextField("10000");
        JTextField txtCauHinh = new JTextField();

        themTruongNhapLieu(panel, gbc, 0, "Tên máy:", txtTen);
        themTruongNhapLieu(panel, gbc, 1, "Loại:", cboLoai);
        themTruongNhapLieu(panel, gbc, 2, "Giá/giờ:", txtGia);
        themTruongNhapLieu(panel, gbc, 3, "Cấu hình:", txtCauHinh);

        cboLoai.addActionListener(e -> txtGia.setText("VIP".equals(cboLoai.getSelectedItem()) ? "15000" : "10000"));

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelNut.setOpaque(false);
        JButton btnLuu = new JButton("Lưu");
        btnLuu.setBackground(MAU_XANH); btnLuu.setForeground(Color.WHITE);
        btnLuu.addActionListener(e -> {
            String ten = txtTen.getText().trim();
            if (ten.isEmpty()) { JOptionPane.showMessageDialog(dialog, "Nhập tên máy!"); return; }
            try {
                MayTinh mt = new MayTinh();
                mt.setTen(ten); mt.setLoai((String) cboLoai.getSelectedItem());
                mt.setGiaMoiGio(Double.parseDouble(txtGia.getText().trim()));
                mt.setTrangThai("Trống"); mt.setCauHinh(txtCauHinh.getText().trim());
                mayTinhDAO.them(mt); lamMoiMayTinh(); dialog.dispose();
            } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(dialog, "Giá không hợp lệ!"); }
        });
        JButton btnHuy = new JButton("Hủy");
        btnHuy.addActionListener(e -> dialog.dispose());
        panelNut.add(btnHuy); panelNut.add(btnLuu);
        gbc.gridy = 4; gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(panelNut, gbc);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void themDongThongTin(JPanel panel, GridBagConstraints gbc, int dong, String nhan, String giaTri) {
        gbc.gridy = dong; gbc.gridwidth = 1; gbc.insets = new Insets(3, 0, 3, 10);
        gbc.gridx = 0;
        JLabel lbl = new JLabel(nhan); lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13)); lbl.setForeground(MAU_CHU_PHU);
        panel.add(lbl, gbc);
        gbc.gridx = 1;
        JLabel val = new JLabel(giaTri); val.setFont(new Font("Segoe UI", Font.BOLD, 13)); val.setForeground(MAU_CHU_CHINH);
        panel.add(val, gbc);
        gbc.gridx = 0; gbc.gridwidth = 2;
    }

    private void themTruongNhapLieu(JPanel panel, GridBagConstraints gbc, int dong, String nhan, JComponent field) {
        gbc.gridy = dong * 2;
        JLabel lbl = new JLabel(nhan); lbl.setForeground(MAU_CHU_PHU); lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lbl, gbc);
        gbc.gridy = dong * 2 + 1;
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(field, gbc);
    }

    private JButton taoNutLoc(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            panelLuoi.removeAll();
            List<MayTinh> ds = "Tất cả".equals(text) ? mayTinhDAO.layTatCa() : mayTinhDAO.layTheoTrangThai(text);
            for (MayTinh mt : ds) panelLuoi.add(taoTheMayTinh(mt));
            panelLuoi.revalidate(); panelLuoi.repaint();
        });
        return btn;
    }

    private JButton taoNutHanhDong(String text, Color mauNen) {
        JButton btn = new JButton(text);
        btn.setBackground(mauNen); btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
