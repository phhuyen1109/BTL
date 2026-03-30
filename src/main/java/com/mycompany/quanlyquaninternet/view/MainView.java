package com.mycompany.quanlyquaninternet.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainView extends JFrame {
    // Colors
    private static final Color SIDEBAR_BG = new Color(17, 14, 45);
    private static final Color SIDEBAR_HOVER = new Color(35, 32, 70);
    private static final Color SIDEBAR_ACTIVE = new Color(99, 102, 241);
    private static final Color CONTENT_BG = new Color(24, 21, 55);
    private static final Color HEADER_BG = new Color(20, 17, 50);
    private static final Color TEXT_PRIMARY = new Color(255, 255, 255);
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    private static final Color ACCENT = new Color(99, 102, 241);

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JPanel[] menuItems;
    private int activeIndex = 0;
    private JLabel lblClock;

    private DashboardPanel dashboardPanel;
    private ComputerPanel computerPanel;
    private CustomerPanel customerPanel;
    private SessionPanel sessionPanel;
    private FoodDrinkPanel foodDrinkPanel;
    private StatisticsPanel statisticsPanel;

    private static final String[] MENU_NAMES = {
        "Dashboard", "Máy Tính", "Khách Hàng", "Phiên SD", "Dịch Vụ", "Thống Kê"
    };
    private static final String[] MENU_ICONS = {
        "\uD83D\uDCCA", "\uD83D\uDDA5", "\uD83D\uDC65", "\u23F1", "\uD83C\uDF54", "\uD83D\uDCC8"
    };

    public MainView() {
        setTitle("Quản Lý Quán Internet - CyberNet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setMinimumSize(new Dimension(1100, 700));
        setLocationRelativeTo(null);
        initComponents();
        startClock();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // === SIDEBAR ===
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setLayout(new BorderLayout());

        // Logo area
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(SIDEBAR_BG);
        logoPanel.setPreferredSize(new Dimension(220, 100));
        logoPanel.setLayout(new GridBagLayout());
        
        JLabel lblLogo = new JLabel("\uD83C\uDFAE", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));

        JLabel lblAppName = new JLabel("CYBER NET");
        lblAppName.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblAppName.setForeground(TEXT_PRIMARY);

        JLabel lblAppSub = new JLabel("Management System");
        lblAppSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblAppSub.setForeground(TEXT_SECONDARY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 0, 0, 0);
        logoPanel.add(lblLogo, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(2, 0, 0, 0);
        logoPanel.add(lblAppName, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 5, 0);
        logoPanel.add(lblAppSub, gbc);

        sidebar.add(logoPanel, BorderLayout.NORTH);

        // Menu items
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(SIDEBAR_BG);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Separator
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(50, 47, 90));
        sep.setMaximumSize(new Dimension(200, 1));
        menuPanel.add(sep);
        menuPanel.add(Box.createVerticalStrut(10));

        menuItems = new JPanel[MENU_NAMES.length];
        for (int i = 0; i < MENU_NAMES.length; i++) {
            menuItems[i] = createMenuItem(MENU_ICONS[i], MENU_NAMES[i], i);
            menuPanel.add(menuItems[i]);
            menuPanel.add(Box.createVerticalStrut(4));
        }

        menuPanel.add(Box.createVerticalGlue());

        // Logout button
        JPanel logoutItem = createMenuItem("\uD83D\uDEAA", "Đăng Xuất", -1);
        logoutItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MainView.this,
                        "Bạn có muốn đăng xuất?", "Xác nhận",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });
        menuPanel.add(logoutItem);
        menuPanel.add(Box.createVerticalStrut(15));

        sidebar.add(menuPanel, BorderLayout.CENTER);

        // === HEADER ===
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_BG);
        headerPanel.setPreferredSize(new Dimension(0, 55));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));

        JLabel lblPageTitle = new JLabel("Dashboard");
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPageTitle.setForeground(TEXT_PRIMARY);
        headerPanel.add(lblPageTitle, BorderLayout.WEST);

        // Clock + admin info
        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 12));
        rightHeader.setOpaque(false);

        lblClock = new JLabel();
        lblClock.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblClock.setForeground(TEXT_SECONDARY);
        rightHeader.add(lblClock);

        JLabel lblAdmin = new JLabel("\uD83D\uDC64 Admin");
        lblAdmin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblAdmin.setForeground(ACCENT);
        rightHeader.add(lblAdmin);

        headerPanel.add(rightHeader, BorderLayout.EAST);

        // Bottom border for header
        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setBackground(HEADER_BG);
        headerWrapper.add(headerPanel, BorderLayout.CENTER);
        JSeparator headerSep = new JSeparator();
        headerSep.setForeground(new Color(50, 47, 90));
        headerWrapper.add(headerSep, BorderLayout.SOUTH);

        // === CONTENT AREA ===
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(CONTENT_BG);

        // Create panels
        dashboardPanel = new DashboardPanel();
        computerPanel = new ComputerPanel();
        computerPanel.setMainView(this);
        customerPanel = new CustomerPanel();
        sessionPanel = new SessionPanel();
        foodDrinkPanel = new FoodDrinkPanel();
        statisticsPanel = new StatisticsPanel();

        contentPanel.add(dashboardPanel, "Dashboard");
        contentPanel.add(computerPanel, "Máy Tính");
        contentPanel.add(customerPanel, "Khách Hàng");
        contentPanel.add(sessionPanel, "Phiên SD");
        contentPanel.add(foodDrinkPanel, "Dịch Vụ");
        contentPanel.add(statisticsPanel, "Thống Kê");

        // Right section (header + content)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(headerWrapper, BorderLayout.NORTH);
        rightPanel.add(contentPanel, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Set first menu active
        updateMenuActive(0);
    }

    private JPanel createMenuItem(String icon, String text, int index) {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (index >= 0 && index == activeIndex) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    // Active indicator bar
                    g2d.setColor(ACCENT);
                    g2d.fillRoundRect(0, 4, 4, getHeight() - 8, 4, 4);
                }
            }
        };
        panel.setBackground(SIDEBAR_BG);
        panel.setMaximumSize(new Dimension(220, 44));
        panel.setPreferredSize(new Dimension(220, 44));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 12));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lblIcon.setPreferredSize(new Dimension(32, 44));

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblText.setForeground(TEXT_SECONDARY);

        panel.add(lblIcon, BorderLayout.WEST);
        panel.add(lblText, BorderLayout.CENTER);

        if (index >= 0) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (index != activeIndex) {
                        panel.setBackground(SIDEBAR_HOVER);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (index != activeIndex) {
                        panel.setBackground(SIDEBAR_BG);
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    switchToPanel(index);
                }
            });
        }

        return panel;
    }

    public void switchToPanel(int index) {
        updateMenuActive(index);
        cardLayout.show(contentPanel, MENU_NAMES[index]);
        refreshPanel(index);
    }

    private void updateMenuActive(int newIndex) {
        // Reset old
        if (activeIndex >= 0 && activeIndex < menuItems.length) {
            menuItems[activeIndex].setBackground(SIDEBAR_BG);
            Component lblText = ((BorderLayout) menuItems[activeIndex].getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (lblText instanceof JLabel) ((JLabel) lblText).setForeground(TEXT_SECONDARY);
        }
        activeIndex = newIndex;
        // Set new
        if (activeIndex >= 0 && activeIndex < menuItems.length) {
            menuItems[activeIndex].setBackground(new Color(99, 102, 241, 20));
            Component lblText = ((BorderLayout) menuItems[activeIndex].getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (lblText instanceof JLabel) ((JLabel) lblText).setForeground(TEXT_PRIMARY);
            menuItems[activeIndex].repaint();
        }
    }

    private void refreshPanel(int index) {
        switch (index) {
            case 0 -> dashboardPanel.refreshData();
            case 1 -> computerPanel.refreshComputers();
            case 2 -> customerPanel.loadCustomers();
            case 3 -> sessionPanel.loadSessions();
            case 4 -> foodDrinkPanel.loadMenu();
            case 5 -> statisticsPanel.loadStatistics();
        }
    }

    public void refreshAll() {
        refreshPanel(activeIndex);
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  -  dd/MM/yyyy");
            lblClock.setText(sdf.format(new Date()));
        });
        timer.start();
    }
}
