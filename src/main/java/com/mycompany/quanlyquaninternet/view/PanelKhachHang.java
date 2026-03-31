package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.KhachHangDAO;
import com.mycompany.quanlyquaninternet.dao.DoAnUongDAO;
import com.mycompany.quanlyquaninternet.dao.LichSuDoiThuongDAO;
import com.mycompany.quanlyquaninternet.entity.KhachHang;
import com.mycompany.quanlyquaninternet.entity.DoAnUong;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Giao diện quản lý khách hàng
public class PanelKhachHang extends JPanel {
    private static final Color MAU_NEN = new Color(24, 21, 55);
    private static final Color MAU_THE = new Color(30, 27, 65);
    private static final Color MAU_CHU_CHINH = Color.WHITE;
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);
    private static final Color MAU_XANH = new Color(16, 185, 129);
    private static final Color MAU_NHAN = new Color(99, 102, 241);
    private static final Color MAU_DO = new Color(239, 68, 68);
    private static final Color MAU_CAM = new Color(245, 158, 11);
    private static final double GIA_MOI_GIO = 10000.0;

    private JTable bangKhachHang;
    private DefaultTableModel moHinhBang;
    private JTextField txtTimKiem, txtTen, txtSDT, txtSoDu;
    private final KhachHangDAO khachHangDAO = new KhachHangDAO();
    private final DoAnUongDAO doAnUongDAO = new DoAnUongDAO();
    private final LichSuDoiThuongDAO lichSuDoiThuongDAO = new LichSuDoiThuongDAO();

    public PanelKhachHang() {
        setBackground(MAU_NEN);
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        khoiTaoGiaoDien();
        taiDanhSachKhach();
    }

    private void khoiTaoGiaoDien() {
        JPanel thanhTren = new JPanel(new BorderLayout());
        thanhTren.setOpaque(false);

        JPanel panelTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelTimKiem.setOpaque(false);
        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tìm kiếm theo tên hoặc SĐT...");
        panelTimKiem.add(txtTimKiem);

        JButton btnTim = new JButton("\uD83D\uDD0D Tìm");
        btnTim.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnTim.addActionListener(e -> timKiemKhach());
        panelTimKiem.add(btnTim);

        JButton btnLamMoi = new JButton("\u21BB Tất cả");
        btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnLamMoi.addActionListener(e -> { txtTimKiem.setText(""); taiDanhSachKhach(); });
        panelTimKiem.add(btnLamMoi);
        thanhTren.add(panelTimKiem, BorderLayout.WEST);

        JButton btnThem = new JButton("+ Thêm Khách");
        btnThem.setBackground(MAU_XANH); btnThem.setForeground(Color.WHITE);
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnThem.addActionListener(e -> hienThiDialogThem());
        thanhTren.add(btnThem, BorderLayout.EAST);
        add(thanhTren, BorderLayout.NORTH);

        String[] cot = {"Mã", "Tên khách hàng", "Số điện thoại", "Số dư (đ)", "Tổng giờ chơi", "Điểm \uD83C\uDFC6"};
        moHinhBang = new DefaultTableModel(cot, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        bangKhachHang = new JTable(moHinhBang);
        trangTriBang(bangKhachHang);

        JScrollPane scrollPane = new JScrollPane(bangKhachHang);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90)));
        scrollPane.getViewport().setBackground(MAU_THE);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelDuoi = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        panelDuoi.setOpaque(false);

        JButton btnDoiThuong = new JButton("\uD83C\uDF81 Đổi Thưởng");
        btnDoiThuong.setBackground(MAU_CAM); btnDoiThuong.setForeground(Color.WHITE);
        btnDoiThuong.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDoiThuong.addActionListener(e -> hienThiDialogDoiThuong());
        panelDuoi.add(btnDoiThuong);

        JButton btnNapTien = new JButton("\uD83D\uDCB0 Nạp Tiền");
        btnNapTien.setBackground(new Color(168, 85, 247)); btnNapTien.setForeground(Color.WHITE);
        btnNapTien.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNapTien.addActionListener(e -> napTien());
        panelDuoi.add(btnNapTien);

        JButton btnSua = new JButton("\u270F Sửa");
        btnSua.setBackground(MAU_NHAN); btnSua.setForeground(Color.WHITE);
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSua.addActionListener(e -> suaKhach());
        panelDuoi.add(btnSua);

        JButton btnXoa = new JButton("\uD83D\uDDD1 Xóa");
        btnXoa.setBackground(MAU_DO); btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnXoa.addActionListener(e -> xoaKhach());
        panelDuoi.add(btnXoa);

        add(panelDuoi, BorderLayout.SOUTH);
    }

    public void taiDanhSachKhach() {
        moHinhBang.setRowCount(0);
        for (KhachHang kh : khachHangDAO.layTatCa()) {
            moHinhBang.addRow(new Object[]{ kh.getMa(), kh.getTen(), kh.getSdt(),
                String.format("%,.0f", kh.getSoDu()), String.format("%.1f giờ", kh.getTongGio()), kh.getDiem() + " \u2B50" });
        }
    }

    private void timKiemKhach() {
        String tuKhoa = txtTimKiem.getText().trim();
        if (tuKhoa.isEmpty()) { taiDanhSachKhach(); return; }
        moHinhBang.setRowCount(0);
        for (KhachHang kh : khachHangDAO.timKiem(tuKhoa)) {
            moHinhBang.addRow(new Object[]{ kh.getMa(), kh.getTen(), kh.getSdt(),
                String.format("%,.0f", kh.getSoDu()), String.format("%.1f giờ", kh.getTongGio()), kh.getDiem() + " \u2B50" });
        }
    }

    private void hienThiDialogThem() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm Khách Hàng Mới", true);
        dialog.setSize(420, 280); dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(MAU_THE); panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridx = 0; gbc.gridwidth = 2; gbc.insets = new Insets(4, 0, 4, 0);

        txtTen = new JTextField(); txtSDT = new JTextField(); txtSoDu = new JTextField();

        gbc.gridy = 0; JLabel l1 = new JLabel("Tên khách hàng (*):"); l1.setForeground(MAU_CHU_PHU); l1.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panel.add(l1, gbc);
        gbc.gridy = 1; txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panel.add(txtTen, gbc);
        gbc.gridy = 2; JLabel l2 = new JLabel("SĐT:"); l2.setForeground(MAU_CHU_PHU); l2.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panel.add(l2, gbc);
        gbc.gridy = 3; txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panel.add(txtSDT, gbc);
        gbc.gridy = 4; JLabel l3 = new JLabel("Số tiền nạp ban đầu (*) (đ):"); l3.setForeground(MAU_CHU_PHU); l3.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panel.add(l3, gbc);
        gbc.gridy = 5; txtSoDu.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panel.add(txtSoDu, gbc);

        gbc.gridy = 6;
        JLabel lblXemTruoc = new JLabel("\u23F1 \u2248 0.0 giờ chơi  |  \u2B50 0 điểm");
        lblXemTruoc.setFont(new Font("Segoe UI", Font.BOLD, 13)); lblXemTruoc.setForeground(MAU_XANH);
        panel.add(lblXemTruoc, gbc);

        txtSoDu.getDocument().addDocumentListener(new DocumentListener() {
            private void capNhat() {
                try {
                    double soTien = Double.parseDouble(txtSoDu.getText().trim().replace(",", ""));
                    double soGio = soTien / GIA_MOI_GIO; int diem = (int) soGio;
                    lblXemTruoc.setText(String.format("\u23F1 \u2248 %.1f giờ chơi  |  \u2B50 %d điểm", soGio, diem));
                    lblXemTruoc.setForeground(MAU_XANH);
                } catch (Exception e) { lblXemTruoc.setText("\u23F1 \u2248 0.0 giờ chơi  |  \u2B50 0 điểm"); lblXemTruoc.setForeground(MAU_CHU_PHU); }
            }
            public void insertUpdate(DocumentEvent e) { capNhat(); }
            public void removeUpdate(DocumentEvent e) { capNhat(); }
            public void changedUpdate(DocumentEvent e) { capNhat(); }
        });

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.RIGHT)); panelNut.setOpaque(false);
        JButton btnLuu = new JButton("✓ Tạo Khách Hàng"); btnLuu.setBackground(MAU_XANH); btnLuu.setForeground(Color.WHITE); btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLuu.addActionListener(e -> {
            String ten = txtTen.getText().trim(); double soDu = 0;
            try { soDu = Double.parseDouble(txtSoDu.getText().trim().replace(",", "")); } catch (Exception ignored) {}
            if (ten.isEmpty()) { JOptionPane.showMessageDialog(dialog, "Nhập tên khách hàng!"); return; }
            if (soDu <= 0) { JOptionPane.showMessageDialog(dialog, "Khách mới cần nạp tiền! Nhập số tiền > 0."); return; }
            double soGio = soDu / GIA_MOI_GIO; int diem = (int) soGio;
            KhachHang kh = new KhachHang(); kh.setTen(ten); kh.setSdt(txtSDT.getText().trim());
            kh.setSoDu(soDu); kh.setTongGio(soGio); kh.setDiem(diem);
            khachHangDAO.them(kh); taiDanhSachKhach(); dialog.dispose();
            JOptionPane.showMessageDialog(this, "Đã thêm khách \"" + ten + "\"!\nSố dư: " + String.format("%,.0f", soDu) + "đ\nGiờ chơi: " + String.format("%.1f", soGio) + " giờ\nĐiểm: " + diem + " \u2B50", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        });
        JButton btnHuy = new JButton("Hủy"); btnHuy.addActionListener(e -> dialog.dispose());
        panelNut.add(btnHuy); panelNut.add(btnLuu);
        gbc.gridy = 7; gbc.insets = new Insets(15, 0, 0, 0); panel.add(panelNut, gbc);
        dialog.setContentPane(panel); dialog.setVisible(true);
    }

    private void suaKhach() {
        int dong = bangKhachHang.getSelectedRow();
        if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn khách hàng cần sửa!"); return; }
        int ma = (int) moHinhBang.getValueAt(dong, 0);
        KhachHang kh = khachHangDAO.layTheoMa(ma); if (kh == null) return;
        JPanel formPanel = taoFormSua(kh.getTen(), kh.getSdt());
        int ketQua = JOptionPane.showConfirmDialog(this, formPanel, "Sửa Thông Tin Khách Hàng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (ketQua == JOptionPane.OK_OPTION) { kh.setTen(txtTen.getText().trim()); kh.setSdt(txtSDT.getText().trim()); khachHangDAO.capNhat(kh); taiDanhSachKhach(); }
    }

    private void xoaKhach() {
        int dong = bangKhachHang.getSelectedRow();
        if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn khách hàng cần xóa!"); return; }
        int ma = (int) moHinhBang.getValueAt(dong, 0); String ten = (String) moHinhBang.getValueAt(dong, 1);
        int xacNhan = JOptionPane.showConfirmDialog(this, "Xóa khách hàng \"" + ten + "\"?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (xacNhan == JOptionPane.YES_OPTION) { khachHangDAO.xoa(ma); taiDanhSachKhach(); }
    }

    private void napTien() {
        int dong = bangKhachHang.getSelectedRow();
        if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn khách hàng cần nạp tiền!"); return; }
        int ma = (int) moHinhBang.getValueAt(dong, 0); String ten = (String) moHinhBang.getValueAt(dong, 1);
        KhachHang kh = khachHangDAO.layTheoMa(ma); if (kh == null) return;

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nạp Tiền - " + ten, true);
        dialog.setSize(400, 280); dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridBagLayout()); panel.setBackground(MAU_THE); panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints(); gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridx = 0; gbc.gridwidth = 2;

        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 4, 0);
        JLabel lblInfo = new JLabel("Khách: " + ten + "  |  Số dư: " + String.format("%,.0f", kh.getSoDu()) + "đ"); lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13)); lblInfo.setForeground(MAU_CHU_PHU); panel.add(lblInfo, gbc);

        gbc.gridy = 1;
        JLabel lblDiem = new JLabel("Điểm: " + kh.getDiem() + " \u2B50  |  Giờ chơi: " + String.format("%.1f", kh.getTongGio()) + " giờ"); lblDiem.setFont(new Font("Segoe UI", Font.PLAIN, 13)); lblDiem.setForeground(MAU_CAM); panel.add(lblDiem, gbc);

        gbc.gridy = 2; gbc.insets = new Insets(15, 0, 4, 0);
        JLabel lblSoTien = new JLabel("Nhập số tiền nạp (đ):"); lblSoTien.setForeground(MAU_CHU_PHU); lblSoTien.setFont(new Font("Segoe UI", Font.PLAIN, 13)); panel.add(lblSoTien, gbc);

        JTextField txtSoTien = new JTextField(); txtSoTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 6, 0); panel.add(txtSoTien, gbc);

        gbc.gridy = 4;
        JLabel lblXemTruoc = new JLabel("\u23F1 + 0.0 giờ chơi mới  |  \u2B50 + 0 điểm"); lblXemTruoc.setFont(new Font("Segoe UI", Font.BOLD, 14)); lblXemTruoc.setForeground(MAU_XANH); panel.add(lblXemTruoc, gbc);

        txtSoTien.getDocument().addDocumentListener(new DocumentListener() {
            private void capNhat() {
                try { double st = Double.parseDouble(txtSoTien.getText().trim().replace(",", "")); double g = st / GIA_MOI_GIO; int d = (int) g;
                    lblXemTruoc.setText(String.format("\u23F1 + %.1f giờ chơi mới  |  \u2B50 + %d điểm", g, d)); lblXemTruoc.setForeground(MAU_XANH);
                } catch (Exception e) { lblXemTruoc.setText("\u23F1 + 0.0 giờ chơi mới  |  \u2B50 + 0 điểm"); lblXemTruoc.setForeground(MAU_CHU_PHU); }
            }
            public void insertUpdate(DocumentEvent e) { capNhat(); } public void removeUpdate(DocumentEvent e) { capNhat(); } public void changedUpdate(DocumentEvent e) { capNhat(); }
        });

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.RIGHT)); panelNut.setOpaque(false);
        JButton btnXacNhan = new JButton("\uD83D\uDCB0 Nạp Tiền"); btnXacNhan.setBackground(new Color(168, 85, 247)); btnXacNhan.setForeground(Color.WHITE); btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXacNhan.addActionListener(e -> {
            try { double st = Double.parseDouble(txtSoTien.getText().trim().replace(",", ""));
                if (st <= 0) { JOptionPane.showMessageDialog(dialog, "Số tiền phải lớn hơn 0!"); return; }
                double g = st / GIA_MOI_GIO; int d = (int) g;
                khachHangDAO.napTienVaCongGioDiem(ma, st); taiDanhSachKhach(); dialog.dispose();
                JOptionPane.showMessageDialog(this, "Đã nạp " + String.format("%,.0f", st) + "đ cho " + ten + "!\nCộng thêm: " + String.format("%.1f", g) + " giờ chơi\nTích thêm: " + d + " \u2B50 điểm", "Nạp tiền thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(dialog, "Số tiền không hợp lệ!"); }
        });
        JButton btnHuy = new JButton("Hủy"); btnHuy.addActionListener(e -> dialog.dispose());
        panelNut.add(btnHuy); panelNut.add(btnXacNhan);
        gbc.gridy = 5; gbc.insets = new Insets(15, 0, 0, 0); panel.add(panelNut, gbc);
        dialog.setContentPane(panel); dialog.setVisible(true);
    }

    private void hienThiDialogDoiThuong() {
        int dong = bangKhachHang.getSelectedRow();
        if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn khách hàng cần đổi thưởng!"); return; }
        int maKH = (int) moHinhBang.getValueAt(dong, 0); String tenKH = (String) moHinhBang.getValueAt(dong, 1);
        KhachHang kh = khachHangDAO.layTheoMa(maKH); if (kh == null) return;

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "\uD83C\uDF81 Đổi Thưởng - " + tenKH, true);
        dialog.setSize(550, 480); dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new BorderLayout(0, 10)); panel.setBackground(MAU_THE); panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblDiem = new JLabel("\u2B50 Điểm hiện có: " + kh.getDiem() + " điểm"); lblDiem.setFont(new Font("Segoe UI", Font.BOLD, 16)); lblDiem.setForeground(MAU_CAM);
        lblDiem.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5)); panel.add(lblDiem, BorderLayout.NORTH);

        List<DoAnUong> dsMenu = doAnUongDAO.layConHang();
        String[] cot = {"Chọn", "Tên", "Giá (đ)", "Điểm cần", "Loại"};
        DefaultTableModel moHinhThuong = new DefaultTableModel(cot, 0) {
            @Override public Class<?> getColumnClass(int col) { return col == 0 ? Boolean.class : String.class; }
            @Override public boolean isCellEditable(int row2, int col) { return col == 0; }
        };
        for (DoAnUong dau : dsMenu) {
            if (dau.getDiemDoi() > 0) moHinhThuong.addRow(new Object[]{false, dau.getTen(), String.format("%,.0f", dau.getGia()), dau.getDiemDoi() + " \u2B50", dau.getPhanLoai()});
        }
        JTable bangThuong = new JTable(moHinhThuong); bangThuong.setRowHeight(32); bangThuong.setFont(new Font("Segoe UI", Font.PLAIN, 13)); bangThuong.getColumnModel().getColumn(0).setPreferredWidth(40);
        panel.add(new JScrollPane(bangThuong), BorderLayout.CENTER);

        JPanel panelDuoi = new JPanel(new FlowLayout(FlowLayout.RIGHT)); panelDuoi.setOpaque(false);
        JButton btnXacNhan = new JButton("✓ Đổi Thưởng"); btnXacNhan.setBackground(MAU_CAM); btnXacNhan.setForeground(Color.WHITE); btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXacNhan.addActionListener(e -> {
            int tongDiemCan = 0; StringBuilder sb = new StringBuilder("Bạn sẽ đổi:\n"); int soMon = 0;
            for (int i = 0; i < moHinhThuong.getRowCount(); i++) {
                if ((boolean) moHinhThuong.getValueAt(i, 0)) {
                    int idx = 0;
                    for (int j = 0; j < dsMenu.size(); j++) {
                        if (dsMenu.get(j).getDiemDoi() > 0) {
                            if (idx == i) { DoAnUong dau = dsMenu.get(j); tongDiemCan += dau.getDiemDoi(); sb.append("  - ").append(dau.getTen()).append(" (").append(dau.getDiemDoi()).append(" \u2B50)\n"); soMon++; break; }
                            idx++;
                        }
                    }
                }
            }
            if (soMon == 0) { JOptionPane.showMessageDialog(dialog, "Chưa chọn phần thưởng nào!"); return; }
            if (tongDiemCan > kh.getDiem()) { JOptionPane.showMessageDialog(dialog, "Không đủ điểm!\nCần: " + tongDiemCan + " \u2B50\nHiện có: " + kh.getDiem() + " \u2B50", "Không đủ điểm", JOptionPane.WARNING_MESSAGE); return; }
            sb.append("\nTổng điểm cần: ").append(tongDiemCan).append(" \u2B50");
            sb.append("\nĐiểm còn lại: ").append(kh.getDiem() - tongDiemCan).append(" \u2B50");
            int xn = JOptionPane.showConfirmDialog(dialog, sb.toString(), "Xác nhận đổi thưởng", JOptionPane.YES_NO_OPTION);
            if (xn == JOptionPane.YES_OPTION) {
                for (int i = 0; i < moHinhThuong.getRowCount(); i++) {
                    if ((boolean) moHinhThuong.getValueAt(i, 0)) {
                        int idx = 0;
                        for (int j = 0; j < dsMenu.size(); j++) {
                            if (dsMenu.get(j).getDiemDoi() > 0) {
                                if (idx == i) { DoAnUong dau = dsMenu.get(j); lichSuDoiThuongDAO.ghiLichSu(maKH, dau.getMa(), dau.getTen(), dau.getDiemDoi()); break; }
                                idx++;
                            }
                        }
                    }
                }
                khachHangDAO.truDiem(maKH, tongDiemCan); taiDanhSachKhach(); dialog.dispose();
                JOptionPane.showMessageDialog(this, "Đổi thưởng thành công cho " + tenKH + "!\nĐã trừ " + tongDiemCan + " \u2B50 điểm.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton btnDong = new JButton("Đóng"); btnDong.addActionListener(e -> dialog.dispose());
        panelDuoi.add(btnDong); panelDuoi.add(btnXacNhan);
        panel.add(panelDuoi, BorderLayout.SOUTH);
        dialog.setContentPane(panel); dialog.setVisible(true);
    }

    private JPanel taoFormSua(String ten, String sdt) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 8, 8)); panel.setPreferredSize(new Dimension(350, 80));
        txtTen = new JTextField(ten); txtSDT = new JTextField(sdt);
        panel.add(new JLabel("Tên:")); panel.add(txtTen); panel.add(new JLabel("SĐT:")); panel.add(txtSDT);
        return panel;
    }

    private void trangTriBang(JTable bang) {
        bang.setBackground(MAU_THE); bang.setForeground(MAU_CHU_CHINH);
        bang.setSelectionBackground(new Color(99, 102, 241, 50)); bang.setSelectionForeground(MAU_CHU_CHINH);
        bang.setGridColor(new Color(50, 47, 90)); bang.setRowHeight(36);
        bang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bang.getTableHeader().setBackground(new Color(35, 32, 70)); bang.getTableHeader().setForeground(MAU_CHU_PHU);
        bang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); bang.getTableHeader().setPreferredSize(new Dimension(0, 36));
        bang.setShowHorizontalLines(true); bang.setShowVerticalLines(false);
        DefaultTableCellRenderer canGiua = new DefaultTableCellRenderer(); canGiua.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bang.getColumnCount(); i++) bang.getColumnModel().getColumn(i).setCellRenderer(canGiua);
    }
}
