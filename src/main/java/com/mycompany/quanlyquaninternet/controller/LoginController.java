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
                    loginView.showMessage("Không thể kết nối database!");
                    JOptionPane.showMessageDialog(loginView,
                            "Không thể kết nối Cơ sở dữ liệu!\n\n" +
                                    "Hướng dẫn:\n" +
                                    "1. Giải nén toàn bộ file ZIP trước khi chạy\n" +
                                    "2. Đảm bảo thư mục 'data/' nằm cùng thư mục với file JAR\n" +
                                    "3. Đảm bảo có quyền đọc/ghi trong thư mục hiện tại",
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
