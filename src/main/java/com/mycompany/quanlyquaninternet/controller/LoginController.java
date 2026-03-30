package com.mycompany.quanlyquaninternet.controller;

import com.mycompany.quanlyquaninternet.dao.DatabaseConnection;
import com.mycompany.quanlyquaninternet.view.LoginView;
import com.mycompany.quanlyquaninternet.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                loginView.showMessage("Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Simple auth check (admin/admin)
            if ("admin".equals(username) && "admin".equals(password)) {
                // Test DB connection
                if (!DatabaseConnection.testConnection()) {
                    loginView.showMessage("Không thể kết nối database! Kiểm tra MySQL.");
                    JOptionPane.showMessageDialog(loginView,
                        "Không thể kết nối MySQL!\n\n" +
                        "Hướng dẫn:\n" +
                        "1. Đảm bảo MySQL đang chạy\n" +
                        "2. Chạy file sql/init_database.sql\n" +
                        "3. Kiểm tra file src/main/resources/db.properties\n\n" +
                        "Mặc định: root / (không mật khẩu)",
                        "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                loginView.clearMessage();
                loginView.setVisible(false);

                // Show main view
                SwingUtilities.invokeLater(() -> {
                    MainView mainView = new MainView();
                    mainView.setVisible(true);
                });
            } else {
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng!");
            }
        }
    }
}
