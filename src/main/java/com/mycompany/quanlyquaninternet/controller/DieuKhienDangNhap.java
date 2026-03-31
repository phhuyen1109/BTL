package com.mycompany.quanlyquaninternet.controller;

import com.mycompany.quanlyquaninternet.dao.KetNoiCSDL;
import com.mycompany.quanlyquaninternet.view.GiaoDienDangNhap;
import com.mycompany.quanlyquaninternet.view.GiaoDienChinh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lớp điều khiển đăng nhập
public class DieuKhienDangNhap {
    private final GiaoDienDangNhap giaoDienDangNhap;

    public DieuKhienDangNhap(GiaoDienDangNhap giaoDienDangNhap) {
        this.giaoDienDangNhap = giaoDienDangNhap;
        giaoDienDangNhap.themSuKienDangNhap(new XuLyDangNhap());
    }

    public void hienThiGiaoDien() {
        giaoDienDangNhap.setVisible(true);
    }

    class XuLyDangNhap implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenDangNhap = giaoDienDangNhap.layTenDangNhap();
            String matKhau = giaoDienDangNhap.layMatKhau();

            if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
                giaoDienDangNhap.hienThongBao("Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            if ("admin".equals(tenDangNhap) && "admin".equals(matKhau)) {
                if (!KetNoiCSDL.kiemTraKetNoi()) {
                    giaoDienDangNhap.hienThongBao("Không thể kết nối database! Kiểm tra MySQL.");
                    JOptionPane.showMessageDialog(giaoDienDangNhap,
                        "Không thể kết nối MySQL!\n\n" +
                        "Hướng dẫn:\n" +
                        "1. Đảm bảo MySQL đang chạy\n" +
                        "2. Chạy file sql/init_database.sql\n" +
                        "3. Kiểm tra file src/main/resources/db.properties\n\n" +
                        "Mặc định: root / (không mật khẩu)",
                        "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                giaoDienDangNhap.xoaThongBao();
                giaoDienDangNhap.setVisible(false);

                SwingUtilities.invokeLater(() -> {
                    GiaoDienChinh giaoDienChinh = new GiaoDienChinh();
                    giaoDienChinh.setVisible(true);
                });
            } else {
                giaoDienDangNhap.hienThongBao("Tên đăng nhập hoặc mật khẩu không đúng!");
            }
        }
    }
}
