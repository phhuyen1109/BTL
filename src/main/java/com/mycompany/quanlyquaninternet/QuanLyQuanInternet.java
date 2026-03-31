package com.mycompany.quanlyquaninternet;

import com.mycompany.quanlyquaninternet.controller.DieuKhienDangNhap;
import com.mycompany.quanlyquaninternet.view.GiaoDienDangNhap;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

/**
 * Quản Lý Quán Internet - CyberNet
 * Điểm bắt đầu chương trình (Phiên bản Việt hóa)
 */
public class QuanLyQuanInternet {

    public static void main(String[] args) {
        // Thiết lập giao diện FlatLaf Dark
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
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Khởi chạy ứng dụng
        EventQueue.invokeLater(() -> {
            GiaoDienDangNhap giaoDienDangNhap = new GiaoDienDangNhap();
            DieuKhienDangNhap dieuKhien = new DieuKhienDangNhap(giaoDienDangNhap);
            dieuKhien.hienThiGiaoDien();
        });
    }
}
