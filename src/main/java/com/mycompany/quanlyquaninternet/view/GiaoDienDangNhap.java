package com.mycompany.quanlyquaninternet.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

// Giao diện màn hình đăng nhập
public class GiaoDienDangNhap extends JFrame {
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap;
    private JLabel lblThongBao;

    private static final Color MAU_NEN_BAT_DAU = new Color(15, 12, 41);
    private static final Color MAU_NEN_KET_THUC = new Color(48, 43, 99);
    private static final Color MAU_THE = new Color(30, 27, 65, 230);
    private static final Color MAU_NHAN = new Color(99, 102, 241);
    private static final Color MAU_NHAN_HOVER = new Color(129, 132, 255);
    private static final Color MAU_CHU_CHINH = new Color(255, 255, 255);
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);
    private static final Color MAU_O_NHAP = new Color(45, 42, 85);
    private static final Color MAU_VIEN = new Color(80, 77, 120);
    private static final Color MAU_LOI = new Color(239, 68, 68);

    public GiaoDienDangNhap() {
        setTitle("Quản Lý Quán Internet - Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        khoiTaoGiaoDien();
    }

    private void khoiTaoGiaoDien() {
        JPanel panelNen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, MAU_NEN_BAT_DAU, getWidth(), getHeight(), MAU_NEN_KET_THUC);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(new Color(99, 102, 241, 30));
                g2d.fillOval(-50, -50, 200, 200);
                g2d.fillOval(getWidth() - 100, getHeight() - 150, 250, 250);
                g2d.setColor(new Color(168, 85, 247, 20));
                g2d.fillOval(getWidth() - 200, -80, 300, 300);
            }
        };
        panelNen.setLayout(new GridBagLayout());

        JPanel panelThe = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(MAU_THE);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 24, 24));
                g2d.setColor(new Color(99, 102, 241, 40));
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth()-1, getHeight()-1, 24, 24));
            }
        };
        panelThe.setOpaque(false);
        panelThe.setPreferredSize(new Dimension(380, 450));
        panelThe.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 30, 8, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblBieuTuong = new JLabel("\uD83C\uDFAE", SwingConstants.CENTER);
        lblBieuTuong.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        gbc.gridy = 0; gbc.insets = new Insets(30, 30, 5, 30);
        panelThe.add(lblBieuTuong, gbc);

        JLabel lblTieuDe = new JLabel("CYBER NET", SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTieuDe.setForeground(MAU_CHU_CHINH);
        gbc.gridy = 1; gbc.insets = new Insets(0, 30, 2, 30);
        panelThe.add(lblTieuDe, gbc);

        JLabel lblPhuDe = new JLabel("Hệ thống quản lý quán internet", SwingConstants.CENTER);
        lblPhuDe.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPhuDe.setForeground(MAU_CHU_PHU);
        gbc.gridy = 2; gbc.insets = new Insets(0, 30, 25, 30);
        panelThe.add(lblPhuDe, gbc);

        JLabel lblNhanTDN = new JLabel("Tên đăng nhập");
        lblNhanTDN.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblNhanTDN.setForeground(MAU_CHU_PHU);
        gbc.gridy = 3; gbc.insets = new Insets(0, 30, 4, 30);
        panelThe.add(lblNhanTDN, gbc);

        txtTenDangNhap = taoONhapVanBan();
        gbc.gridy = 4; gbc.insets = new Insets(0, 30, 12, 30);
        panelThe.add(txtTenDangNhap, gbc);

        JLabel lblNhanMK = new JLabel("Mật khẩu");
        lblNhanMK.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblNhanMK.setForeground(MAU_CHU_PHU);
        gbc.gridy = 5; gbc.insets = new Insets(0, 30, 4, 30);
        panelThe.add(lblNhanMK, gbc);

        txtMatKhau = taoONhapMatKhau();
        gbc.gridy = 6; gbc.insets = new Insets(0, 30, 20, 30);
        panelThe.add(txtMatKhau, gbc);

        btnDangNhap = new JButton("ĐĂNG NHẬP") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2d.setColor(MAU_NHAN_HOVER);
                } else {
                    GradientPaint gp = new GradientPaint(0, 0, MAU_NHAN, getWidth(), 0, new Color(168, 85, 247));
                    g2d.setPaint(gp);
                }
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12));
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setPreferredSize(new Dimension(0, 44));
        btnDangNhap.setBorderPainted(false);
        btnDangNhap.setContentAreaFilled(false);
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 7; gbc.insets = new Insets(0, 30, 10, 30);
        panelThe.add(btnDangNhap, gbc);

        lblThongBao = new JLabel(" ", SwingConstants.CENTER);
        lblThongBao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblThongBao.setForeground(MAU_LOI);
        gbc.gridy = 8; gbc.insets = new Insets(0, 30, 20, 30);
        panelThe.add(lblThongBao, gbc);

        panelNen.add(panelThe);
        setContentPane(panelNen);

        KeyAdapter phimEnter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) btnDangNhap.doClick();
            }
        };
        txtTenDangNhap.addKeyListener(phimEnter);
        txtMatKhau.addKeyListener(phimEnter);
        txtTenDangNhap.requestFocusInWindow();
    }

    private JTextField taoONhapVanBan() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(MAU_O_NHAP);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                super.paintComponent(g);
            }
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(isFocusOwner() ? MAU_NHAN : MAU_VIEN);
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth()-1, getHeight()-1, 10, 10));
            }
        };
        field.setOpaque(false);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(MAU_CHU_CHINH);
        field.setCaretColor(MAU_NHAN);
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        field.setPreferredSize(new Dimension(0, 42));
        return field;
    }

    private JPasswordField taoONhapMatKhau() {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(MAU_O_NHAP);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                super.paintComponent(g);
            }
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(isFocusOwner() ? MAU_NHAN : MAU_VIEN);
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth()-1, getHeight()-1, 10, 10));
            }
        };
        field.setOpaque(false);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(MAU_CHU_CHINH);
        field.setCaretColor(MAU_NHAN);
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        field.setPreferredSize(new Dimension(0, 42));
        return field;
    }

    public String layTenDangNhap() { return txtTenDangNhap.getText().trim(); }
    public String layMatKhau() { return new String(txtMatKhau.getPassword()); }
    public void hienThongBao(String msg) { lblThongBao.setText(msg); }
    public void xoaThongBao() { lblThongBao.setText(" "); }
    public void themSuKienDangNhap(ActionListener listener) { btnDangNhap.addActionListener(listener); }
}
