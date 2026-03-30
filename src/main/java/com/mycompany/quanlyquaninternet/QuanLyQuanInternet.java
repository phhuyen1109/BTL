package com.mycompany.quanlyquaninternet;

import com.mycompany.quanlyquaninternet.controller.LoginController;
import com.mycompany.quanlyquaninternet.view.LoginView;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

/**
 * Quản Lý Quán Internet - CyberNet
 * Main entry point
 */
public class QuanLyQuanInternet {

    public static void main(String[] args) {
        // Set FlatLaf Dark theme
        try {
            FlatDarkLaf.setup();
            UIManager.put("Button.arc", 8);
            UIManager.put("Component.arc", 8);
            UIManager.put("TextComponent.arc", 8);
            UIManager.put("TextField.margin", new Insets(6, 10, 6, 10));
            UIManager.put("Button.margin", new Insets(6, 14, 6, 14));
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", false);
            UIManager.put("ScrollBar.width", 10);
            UIManager.put("TitlePane.unifiedBackground", true);
        } catch (Exception e) {
            // Fallback to system look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Launch application
        EventQueue.invokeLater(() -> {
            LoginView loginView = new LoginView();
            LoginController controller = new LoginController(loginView);
            controller.showLoginView();
        });
    }
}
