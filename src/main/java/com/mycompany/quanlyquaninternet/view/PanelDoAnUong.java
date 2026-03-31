package com.mycompany.quanlyquaninternet.view;

import com.mycompany.quanlyquaninternet.dao.DoAnUongDAO;
import com.mycompany.quanlyquaninternet.entity.DoAnUong;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Giao diện quản lý đồ ăn uống (dịch vụ)
public class PanelDoAnUong extends JPanel {
    private static final Color MAU_NEN = new Color(24, 21, 55);
    private static final Color MAU_THE = new Color(30, 27, 65);
    private static final Color MAU_CHU_CHINH = Color.WHITE;
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);
    private static final Color MAU_XANH = new Color(16, 185, 129);
    private static final Color MAU_NHAN = new Color(99, 102, 241);
    private static final Color MAU_DO = new Color(239, 68, 68);

    private JTable bangMenu;
    private DefaultTableModel moHinhBang;
    private JTextField txtTen, txtGia;
    private JComboBox<String> cboPhanLoai;
    private JCheckBox chkConHang;
    private final DoAnUongDAO doAnUongDAO = new DoAnUongDAO();

    public PanelDoAnUong() {
        setBackground(MAU_NEN); setLayout(new BorderLayout(0, 15)); setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        khoiTaoGiaoDien(); taiMenu();
    }

    private void khoiTaoGiaoDien() {
        JPanel thanhTren = new JPanel(new BorderLayout()); thanhTren.setOpaque(false);
        JPanel panelLoc = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); panelLoc.setOpaque(false);
        String[] boLoc = {"Tất cả", "Đồ ăn", "Nước uống"};
        for (String loc : boLoc) {
            JButton btn = new JButton(loc); btn.setFont(new Font("Segoe UI", Font.PLAIN, 12)); btn.addActionListener(e -> locTheoPhanLoai(loc)); panelLoc.add(btn);
        }
        thanhTren.add(panelLoc, BorderLayout.WEST);
        JButton btnThem = new JButton("+ Thêm Món"); btnThem.setBackground(MAU_XANH); btnThem.setForeground(Color.WHITE); btnThem.setFont(new Font("Segoe UI", Font.BOLD, 12)); btnThem.addActionListener(e -> hienThiDialogThem()); thanhTren.add(btnThem, BorderLayout.EAST);
        add(thanhTren, BorderLayout.NORTH);

        String[] cot = {"Mã", "Tên món", "Giá (đ)", "Loại", "Trạng thái"};
        moHinhBang = new DefaultTableModel(cot, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        bangMenu = new JTable(moHinhBang); trangTriBang(bangMenu);

        bangMenu.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                Component comp = super.getTableCellRendererComponent(t, v, s, f, r, c);
                setForeground("Còn hàng".equals(v) ? MAU_XANH : MAU_DO); setHorizontalAlignment(CENTER);
                setBackground(s ? new Color(99, 102, 241, 50) : MAU_THE); return comp;
            }
        });

        JScrollPane sp = new JScrollPane(bangMenu); sp.setBorder(BorderFactory.createLineBorder(new Color(50, 47, 90))); sp.getViewport().setBackground(MAU_THE); add(sp, BorderLayout.CENTER);

        JPanel panelDuoi = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10)); panelDuoi.setOpaque(false);
        JButton btnSua = new JButton("\u270F Sửa"); btnSua.setBackground(MAU_NHAN); btnSua.setForeground(Color.WHITE); btnSua.setFont(new Font("Segoe UI", Font.BOLD, 12)); btnSua.addActionListener(e -> suaMon()); panelDuoi.add(btnSua);
        JButton btnDoiTT = new JButton("\u21C4 Đổi TT"); btnDoiTT.setBackground(new Color(245, 158, 11)); btnDoiTT.setForeground(Color.WHITE); btnDoiTT.setFont(new Font("Segoe UI", Font.BOLD, 12)); btnDoiTT.addActionListener(e -> doiTrangThai()); panelDuoi.add(btnDoiTT);
        JButton btnXoa = new JButton("\uD83D\uDDD1 Xóa"); btnXoa.setBackground(MAU_DO); btnXoa.setForeground(Color.WHITE); btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 12)); btnXoa.addActionListener(e -> xoaMon()); panelDuoi.add(btnXoa);
        add(panelDuoi, BorderLayout.SOUTH);
    }

    public void taiMenu() {
        moHinhBang.setRowCount(0);
        for (DoAnUong dau : doAnUongDAO.layTatCa()) {
            moHinhBang.addRow(new Object[]{dau.getMa(), dau.getTen(), String.format("%,.0f", dau.getGia()), dau.getPhanLoai(), dau.isConHang() ? "Còn hàng" : "Hết hàng"});
        }
    }

    private void locTheoPhanLoai(String phanLoai) {
        moHinhBang.setRowCount(0);
        List<DoAnUong> ds = "Tất cả".equals(phanLoai) ? doAnUongDAO.layTatCa() : doAnUongDAO.layTheoPhanLoai(phanLoai);
        for (DoAnUong dau : ds) moHinhBang.addRow(new Object[]{dau.getMa(), dau.getTen(), String.format("%,.0f", dau.getGia()), dau.getPhanLoai(), dau.isConHang() ? "Còn hàng" : "Hết hàng"});
    }

    private void hienThiDialogThem() {
        JPanel formPanel = taoFormPanel("", "", "Đồ ăn", true);
        int ketQua = JOptionPane.showConfirmDialog(this, formPanel, "Thêm Món", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (ketQua == JOptionPane.OK_OPTION) {
            String ten = txtTen.getText().trim(); if (ten.isEmpty()) { JOptionPane.showMessageDialog(this, "Nhập tên món!"); return; }
            try { DoAnUong dau = new DoAnUong(); dau.setTen(ten); dau.setGia(Double.parseDouble(txtGia.getText().trim().replace(",", ""))); dau.setPhanLoai((String) cboPhanLoai.getSelectedItem()); dau.setConHang(chkConHang.isSelected()); doAnUongDAO.them(dau); taiMenu();
            } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Giá không hợp lệ!"); }
        }
    }

    private void suaMon() {
        int dong = bangMenu.getSelectedRow(); if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn món cần sửa!"); return; }
        int ma = (int) moHinhBang.getValueAt(dong, 0); DoAnUong dau = doAnUongDAO.layTheoMa(ma); if (dau == null) return;
        JPanel formPanel = taoFormPanel(dau.getTen(), String.format("%.0f", dau.getGia()), dau.getPhanLoai(), dau.isConHang());
        int ketQua = JOptionPane.showConfirmDialog(this, formPanel, "Sửa Món", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (ketQua == JOptionPane.OK_OPTION) {
            try { dau.setTen(txtTen.getText().trim()); dau.setGia(Double.parseDouble(txtGia.getText().trim().replace(",", ""))); dau.setPhanLoai((String) cboPhanLoai.getSelectedItem()); dau.setConHang(chkConHang.isSelected()); doAnUongDAO.capNhat(dau); taiMenu();
            } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Giá không hợp lệ!"); }
        }
    }

    private void doiTrangThai() {
        int dong = bangMenu.getSelectedRow(); if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn món cần đổi trạng thái!"); return; }
        int ma = (int) moHinhBang.getValueAt(dong, 0); DoAnUong dau = doAnUongDAO.layTheoMa(ma);
        if (dau != null) { dau.setConHang(!dau.isConHang()); doAnUongDAO.capNhat(dau); taiMenu(); }
    }

    private void xoaMon() {
        int dong = bangMenu.getSelectedRow(); if (dong < 0) { JOptionPane.showMessageDialog(this, "Chọn món cần xóa!"); return; }
        int ma = (int) moHinhBang.getValueAt(dong, 0); String ten = (String) moHinhBang.getValueAt(dong, 1);
        int xn = JOptionPane.showConfirmDialog(this, "Xóa món \"" + ten + "\"?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (xn == JOptionPane.YES_OPTION) { doAnUongDAO.xoa(ma); taiMenu(); }
    }

    private JPanel taoFormPanel(String ten, String gia, String phanLoai, boolean conHang) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8)); panel.setPreferredSize(new Dimension(350, 140));
        txtTen = new JTextField(ten); txtGia = new JTextField(gia);
        cboPhanLoai = new JComboBox<>(new String[]{"Đồ ăn", "Nước uống"}); cboPhanLoai.setSelectedItem(phanLoai);
        chkConHang = new JCheckBox("Còn hàng", conHang);
        panel.add(new JLabel("Tên món:")); panel.add(txtTen); panel.add(new JLabel("Giá (đ):")); panel.add(txtGia);
        panel.add(new JLabel("Loại:")); panel.add(cboPhanLoai); panel.add(new JLabel("Trạng thái:")); panel.add(chkConHang);
        return panel;
    }

    private void trangTriBang(JTable bang) {
        bang.setBackground(MAU_THE); bang.setForeground(MAU_CHU_CHINH); bang.setSelectionBackground(new Color(99, 102, 241, 50)); bang.setSelectionForeground(MAU_CHU_CHINH);
        bang.setGridColor(new Color(50, 47, 90)); bang.setRowHeight(36); bang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bang.getTableHeader().setBackground(new Color(35, 32, 70)); bang.getTableHeader().setForeground(MAU_CHU_PHU); bang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); bang.getTableHeader().setPreferredSize(new Dimension(0, 36));
        bang.setShowHorizontalLines(true); bang.setShowVerticalLines(false);
        DefaultTableCellRenderer canGiua = new DefaultTableCellRenderer(); canGiua.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bang.getColumnCount() - 1; i++) bang.getColumnModel().getColumn(i).setCellRenderer(canGiua);
    }
}
