package com.mycompany.quanlyquaninternet.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessage;

    // Colors
    private static final Color BG_START = new Color(15, 12, 41);
    private static final Color BG_END = new Color(48, 43, 99);
    private static final Color CARD_BG = new Color(30, 27, 65, 230);
    private static final Color ACCENT = new Color(99, 102, 241);
    private static final Color ACCENT_HOVER = new Color(129, 132, 255);
    private static final Color TEXT_PRIMARY = new Color(255, 255, 255);
    private static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    private static final Color FIELD_BG = new Color(45, 42, 85);
    private static final Color FIELD_BORDER = new Color(80, 77, 120);
    private static final Color ERROR_COLOR = new Color(239, 68, 68);

    public LoginView() {
        setTitle("Quản Lý Quán Internet - Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        // Gradient background panel
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, BG_START, getWidth(), getHeight(), BG_END);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Decorative circles
                g2d.setColor(new Color(99, 102, 241, 30));
                g2d.fillOval(-50, -50, 200, 200);
                g2d.fillOval(getWidth() - 100, getHeight() - 150, 250, 250);
                g2d.setColor(new Color(168, 85, 247, 20));
                g2d.fillOval(getWidth() - 200, -80, 300, 300);
            }
        };
        bgPanel.setLayout(new GridBagLayout());

        // Login card
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(CARD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 24, 24));

                // Subtle border
                g2d.setColor(new Color(99, 102, 241, 40));
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth()-1, getHeight()-1, 24, 24));
            }
        };
        cardPanel.setOpaque(false);
        cardPanel.setPreferredSize(new Dimension(380, 450));
        cardPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 30, 8, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Icon
        JLabel lblIcon = new JLabel("\uD83C\uDFAE", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        gbc.gridy = 0;
        gbc.insets = new Insets(30, 30, 5, 30);
        cardPanel.add(lblIcon, gbc);

        // Title
        JLabel lblTitle = new JLabel("CYBER NET", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_PRIMARY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 30, 2, 30);
        cardPanel.add(lblTitle, gbc);

        // Subtitle
        JLabel lblSubtitle = new JLabel("Hệ thống quản lý quán internet", SwingConstants.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(TEXT_SECONDARY);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 30, 25, 30);
        cardPanel.add(lblSubtitle, gbc);

        // Username label
        JLabel lblUser = new JLabel("Tên đăng nhập");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUser.setForeground(TEXT_SECONDARY);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 30, 4, 30);
        cardPanel.add(lblUser, gbc);

        // Username field
        txtUsername = createStyledTextField();
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 30, 12, 30);
        cardPanel.add(txtUsername, gbc);

        // Password label
        JLabel lblPass = new JLabel("Mật khẩu");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPass.setForeground(TEXT_SECONDARY);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 30, 4, 30);
        cardPanel.add(lblPass, gbc);

        // Password field
        txtPassword = createStyledPasswordField();
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 30, 20, 30);
        cardPanel.add(txtPassword, gbc);

        // Login button
        btnLogin = new JButton("ĐĂNG NHẬP") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2d.setColor(ACCENT_HOVER);
                } else {
                    GradientPaint gp = new GradientPaint(0, 0, ACCENT, getWidth(), 0, new Color(168, 85, 247));
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
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(0, 44));
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 30, 10, 30);
        cardPanel.add(btnLogin, gbc);

        // Message label
        lblMessage = new JLabel(" ", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMessage.setForeground(ERROR_COLOR);
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 30, 20, 30);
        cardPanel.add(lblMessage, gbc);

        bgPanel.add(cardPanel);
        setContentPane(bgPanel);

        // Enter key to login
        KeyAdapter enterKey = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        };
        txtUsername.addKeyListener(enterKey);
        txtPassword.addKeyListener(enterKey);

        txtUsername.requestFocusInWindow();
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(FIELD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(isFocusOwner() ? ACCENT : FIELD_BORDER);
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth()-1, getHeight()-1, 10, 10));
            }
        };
        field.setOpaque(false);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT);
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        field.setPreferredSize(new Dimension(0, 42));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(FIELD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(isFocusOwner() ? ACCENT : FIELD_BORDER);
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth()-1, getHeight()-1, 10, 10));
            }
        };
        field.setOpaque(false);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT);
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        field.setPreferredSize(new Dimension(0, 42));
        return field;
    }

    // === Public methods ===
    public String getUsername() { return txtUsername.getText().trim(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }

    public void showMessage(String msg) {
        lblMessage.setText(msg);
    }

    public void clearMessage() {
        lblMessage.setText(" ");
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }
}
