package com.mycompany.quanlyquaninternet.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

// Giao diện chính của ứng dụng
public class GiaoDienChinh extends JFrame {
    private static final Color MAU_SIDEBAR = new Color(17, 14, 45);
    private static final Color MAU_SIDEBAR_HOVER = new Color(35, 32, 70);
    private static final Color MAU_SIDEBAR_ACTIVE = new Color(99, 102, 241);
    private static final Color MAU_NOI_DUNG = new Color(24, 21, 55);
    private static final Color MAU_HEADER = new Color(20, 17, 50);
    private static final Color MAU_CHU_CHINH = new Color(255, 255, 255);
    private static final Color MAU_CHU_PHU = new Color(160, 160, 190);
    private static final Color MAU_NHAN = new Color(99, 102, 241);

    private JPanel panelNoiDung;
    private CardLayout boTriThe;
    private JPanel[] danhSachMenu;
    private int viTriHienTai = 0;
    private JLabel lblDongHo;

    private PanelTongQuan panelTongQuan;
    private PanelMayTinh panelMayTinh;
    private PanelKhachHang panelKhachHang;
    private PanelPhienSuDung panelPhienSuDung;
    private PanelDoAnUong panelDoAnUong;
    private PanelThongKe panelThongKe;

    private static final String[] TEN_MENU = {
        "Dashboard", "Máy Tính", "Khách Hàng", "Phiên SD", "Dịch Vụ", "Thống Kê"
    };
    private static final String[] ICON_MENU = {
        "\uD83D\uDCCA", "\uD83D\uDDA5", "\uD83D\uDC65", "\u23F1", "\uD83C\uDF54", "\uD83D\uDCC8"
    };

    public GiaoDienChinh() {
        setTitle("Quản Lý Quán Internet - CyberNet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setMinimumSize(new Dimension(1100, 700));
        setLocationRelativeTo(null);
        khoiTaoGiaoDien();
        batDauDongHo();
    }

    private void khoiTaoGiaoDien() {
        setLayout(new BorderLayout());

        // === SIDEBAR ===
        JPanel sidebar = new JPanel();
        sidebar.setBackground(MAU_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setLayout(new BorderLayout());

        JPanel panelLogo = new JPanel();
        panelLogo.setBackground(MAU_SIDEBAR);
        panelLogo.setPreferredSize(new Dimension(220, 100));
        panelLogo.setLayout(new GridBagLayout());

        JLabel lblLogo = new JLabel("\uD83C\uDFAE", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        JLabel lblTenApp = new JLabel("CYBER NET");
        lblTenApp.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTenApp.setForeground(MAU_CHU_CHINH);
        JLabel lblPhuDe = new JLabel("Hệ thống quản lý");
        lblPhuDe.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblPhuDe.setForeground(MAU_CHU_PHU);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(15, 0, 0, 0);
        panelLogo.add(lblLogo, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(2, 0, 0, 0);
        panelLogo.add(lblTenApp, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 5, 0);
        panelLogo.add(lblPhuDe, gbc);

        sidebar.add(panelLogo, BorderLayout.NORTH);

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(MAU_SIDEBAR);
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(50, 47, 90));
        sep.setMaximumSize(new Dimension(200, 1));
        panelMenu.add(sep);
        panelMenu.add(Box.createVerticalStrut(10));

        danhSachMenu = new JPanel[TEN_MENU.length];
        for (int i = 0; i < TEN_MENU.length; i++) {
            danhSachMenu[i] = taoMucMenu(ICON_MENU[i], TEN_MENU[i], i);
            panelMenu.add(danhSachMenu[i]);
            panelMenu.add(Box.createVerticalStrut(4));
        }
        panelMenu.add(Box.createVerticalGlue());

        JPanel mucDangXuat = taoMucMenu("\uD83D\uDEAA", "Đăng Xuất", -1);
        mucDangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xacNhan = JOptionPane.showConfirmDialog(GiaoDienChinh.this,
                        "Bạn có muốn đăng xuất?", "Xác nhận",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (xacNhan == JOptionPane.YES_OPTION) { dispose(); System.exit(0); }
            }
        });
        panelMenu.add(mucDangXuat);
        panelMenu.add(Box.createVerticalStrut(15));
        sidebar.add(panelMenu, BorderLayout.CENTER);

        // === HEADER ===
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(MAU_HEADER);
        panelHeader.setPreferredSize(new Dimension(0, 55));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));

        JLabel lblTieuDeTrang = new JLabel("Dashboard");
        lblTieuDeTrang.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTieuDeTrang.setForeground(MAU_CHU_CHINH);
        panelHeader.add(lblTieuDeTrang, BorderLayout.WEST);

        JPanel panelHeaderPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 12));
        panelHeaderPhai.setOpaque(false);
        lblDongHo = new JLabel();
        lblDongHo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDongHo.setForeground(MAU_CHU_PHU);
        panelHeaderPhai.add(lblDongHo);
        JLabel lblAdmin = new JLabel("\uD83D\uDC64 Admin");
        lblAdmin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblAdmin.setForeground(MAU_NHAN);
        panelHeaderPhai.add(lblAdmin);
        panelHeader.add(panelHeaderPhai, BorderLayout.EAST);

        JPanel boiHeader = new JPanel(new BorderLayout());
        boiHeader.setBackground(MAU_HEADER);
        boiHeader.add(panelHeader, BorderLayout.CENTER);
        JSeparator sepHeader = new JSeparator();
        sepHeader.setForeground(new Color(50, 47, 90));
        boiHeader.add(sepHeader, BorderLayout.SOUTH);

        // === NỘI DUNG ===
        boTriThe = new CardLayout();
        panelNoiDung = new JPanel(boTriThe);
        panelNoiDung.setBackground(MAU_NOI_DUNG);

        panelTongQuan = new PanelTongQuan();
        panelMayTinh = new PanelMayTinh();
        panelMayTinh.setGiaoDienChinh(this);
        panelKhachHang = new PanelKhachHang();
        panelPhienSuDung = new PanelPhienSuDung();
        panelDoAnUong = new PanelDoAnUong();
        panelThongKe = new PanelThongKe();

        panelNoiDung.add(panelTongQuan, "Dashboard");
        panelNoiDung.add(panelMayTinh, "Máy Tính");
        panelNoiDung.add(panelKhachHang, "Khách Hàng");
        panelNoiDung.add(panelPhienSuDung, "Phiên SD");
        panelNoiDung.add(panelDoAnUong, "Dịch Vụ");
        panelNoiDung.add(panelThongKe, "Thống Kê");

        JPanel panelPhai = new JPanel(new BorderLayout());
        panelPhai.add(boiHeader, BorderLayout.NORTH);
        panelPhai.add(panelNoiDung, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(panelPhai, BorderLayout.CENTER);
        capNhatMenuHoatDong(0);
    }

    private JPanel taoMucMenu(String icon, String text, int viTri) {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (viTri >= 0 && viTri == viTriHienTai) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(MAU_NHAN);
                    g2d.fillRoundRect(0, 4, 4, getHeight() - 8, 4, 4);
                }
            }
        };
        panel.setBackground(MAU_SIDEBAR);
        panel.setMaximumSize(new Dimension(220, 44));
        panel.setPreferredSize(new Dimension(220, 44));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 12));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lblIcon.setPreferredSize(new Dimension(32, 44));
        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblText.setForeground(MAU_CHU_PHU);

        panel.add(lblIcon, BorderLayout.WEST);
        panel.add(lblText, BorderLayout.CENTER);

        if (viTri >= 0) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) { if (viTri != viTriHienTai) panel.setBackground(MAU_SIDEBAR_HOVER); }
                @Override
                public void mouseExited(MouseEvent e) { if (viTri != viTriHienTai) panel.setBackground(MAU_SIDEBAR); }
                @Override
                public void mouseClicked(MouseEvent e) { chuyenDenPanel(viTri); }
            });
        }
        return panel;
    }

    public void chuyenDenPanel(int viTri) {
        capNhatMenuHoatDong(viTri);
        boTriThe.show(panelNoiDung, TEN_MENU[viTri]);
        lamMoiPanel(viTri);
    }

    private void capNhatMenuHoatDong(int viTriMoi) {
        if (viTriHienTai >= 0 && viTriHienTai < danhSachMenu.length) {
            danhSachMenu[viTriHienTai].setBackground(MAU_SIDEBAR);
            Component lblText = ((BorderLayout) danhSachMenu[viTriHienTai].getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (lblText instanceof JLabel) ((JLabel) lblText).setForeground(MAU_CHU_PHU);
        }
        viTriHienTai = viTriMoi;
        if (viTriHienTai >= 0 && viTriHienTai < danhSachMenu.length) {
            danhSachMenu[viTriHienTai].setBackground(new Color(99, 102, 241, 20));
            Component lblText = ((BorderLayout) danhSachMenu[viTriHienTai].getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (lblText instanceof JLabel) ((JLabel) lblText).setForeground(MAU_CHU_CHINH);
            danhSachMenu[viTriHienTai].repaint();
        }
    }

    private void lamMoiPanel(int viTri) {
        switch (viTri) {
            case 0 -> panelTongQuan.lamMoiDuLieu();
            case 1 -> panelMayTinh.lamMoiMayTinh();
            case 2 -> panelKhachHang.taiDanhSachKhach();
            case 3 -> panelPhienSuDung.taiPhienSuDung();
            case 4 -> panelDoAnUong.taiMenu();
            case 5 -> panelThongKe.taiThongKe();
        }
    }

    public void lamMoiTatCa() { lamMoiPanel(viTriHienTai); }

    private void batDauDongHo() {
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  -  dd/MM/yyyy");
            lblDongHo.setText(sdf.format(new Date()));
        });
        timer.start();
    }
}
