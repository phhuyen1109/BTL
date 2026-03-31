-- =============================================
-- SCHEMA DATABASE - QUẢN LÝ QUÁN INTERNET
-- Phiên bản Việt hóa
-- =============================================

-- Bảng người dùng (tài khoản đăng nhập)
CREATE TABLE IF NOT EXISTS nguoi_dung (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ten_dang_nhap VARCHAR(50) NOT NULL UNIQUE,
    mat_khau VARCHAR(100) NOT NULL,
    vai_tro VARCHAR(20) DEFAULT 'staff'
);

-- Bảng khách hàng
CREATE TABLE IF NOT EXISTS khach_hang (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ten VARCHAR(100) NOT NULL,
    sdt VARCHAR(20),
    so_du DOUBLE DEFAULT 0,
    tong_gio DOUBLE DEFAULT 0,
    diem INT DEFAULT 0,
    ngay_tao DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng máy tính
CREATE TABLE IF NOT EXISTS may_tinh (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ten VARCHAR(50) NOT NULL,
    loai VARCHAR(20) DEFAULT 'Thường',
    gia_moi_gio DOUBLE DEFAULT 10000,
    trang_thai VARCHAR(20) DEFAULT 'Trống',
    cau_hinh VARCHAR(200)
);

-- Bảng phiên sử dụng
CREATE TABLE IF NOT EXISTS phien_su_dung (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ma_may_tinh INT NOT NULL,
    ma_khach_hang INT,
    ten_khach VARCHAR(100),
    gio_bat_dau DATETIME NOT NULL,
    gio_ket_thuc DATETIME,
    tong_tien DOUBLE DEFAULT 0,
    trang_thai VARCHAR(20) DEFAULT 'Đang chạy',
    FOREIGN KEY (ma_may_tinh) REFERENCES may_tinh(ma),
    FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang(ma)
);

-- Bảng đồ ăn uống
CREATE TABLE IF NOT EXISTS do_an_uong (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ten VARCHAR(100) NOT NULL,
    gia DOUBLE NOT NULL,
    phan_loai VARCHAR(50) DEFAULT 'Nước uống',
    diem_doi INT DEFAULT 0,
    con_hang BOOLEAN DEFAULT TRUE
);

-- Bảng lịch sử đổi thưởng
CREATE TABLE IF NOT EXISTS lich_su_doi_thuong (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ma_khach_hang INT NOT NULL,
    ma_do_an INT NOT NULL,
    ten_do_an VARCHAR(100),
    diem_da_dung INT NOT NULL,
    ngay_doi DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang(ma),
    FOREIGN KEY (ma_do_an) REFERENCES do_an_uong(ma)
);

-- Bảng đơn hàng
CREATE TABLE IF NOT EXISTS don_hang (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ma_phien INT,
    ma_may_tinh INT NOT NULL,
    tong_gia DOUBLE DEFAULT 0,
    thoi_gian_dat DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ma_phien) REFERENCES phien_su_dung(ma),
    FOREIGN KEY (ma_may_tinh) REFERENCES may_tinh(ma)
);

-- Bảng chi tiết đơn hàng
CREATE TABLE IF NOT EXISTS chi_tiet_don_hang (
    ma INT AUTO_INCREMENT PRIMARY KEY,
    ma_don_hang INT NOT NULL,
    ma_do_an INT NOT NULL,
    ten_do_an VARCHAR(100),
    so_luong INT DEFAULT 1,
    gia DOUBLE NOT NULL,
    FOREIGN KEY (ma_don_hang) REFERENCES don_hang(ma),
    FOREIGN KEY (ma_do_an) REFERENCES do_an_uong(ma)
);

-- =============================================
-- DỮ LIỆU MẪU
-- =============================================

-- Tài khoản mặc định
INSERT INTO nguoi_dung (ten_dang_nhap, mat_khau, vai_tro)
SELECT 'admin', 'admin', 'admin' WHERE NOT EXISTS (SELECT 1 FROM nguoi_dung WHERE ten_dang_nhap = 'admin');

-- Máy tính mẫu (20 máy)
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 01', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1660' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 01');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 02', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1660' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 02');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 03', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1660' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 03');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 04', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1660' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 04');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 05', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1660' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 05');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 06', 'Thường', 10000, 'Trống', 'Core i5-13400, 16GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 06');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 07', 'Thường', 10000, 'Trống', 'Core i5-13400, 16GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 07');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 08', 'Thường', 10000, 'Trống', 'Core i5-13400, 16GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 08');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 09', 'Thường', 10000, 'Trống', 'Core i5-13400, 16GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 09');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 10', 'Thường', 10000, 'Trống', 'Core i5-13400, 16GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 10');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 11', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 4060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 11');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 12', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 4060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 12');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 13', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 4060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 13');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 14', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 4060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 14');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 15', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 4060' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 15');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 16', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 16');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 17', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 17');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 18', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 18');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 19', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 19');
INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh)
SELECT 'Máy 20', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080' WHERE NOT EXISTS (SELECT 1 FROM may_tinh WHERE ten = 'Máy 20');

-- Menu đồ ăn/uống mẫu
INSERT INTO do_an_uong (ten, gia, phan_loai, diem_doi, con_hang)
SELECT 'Mì tôm', 15000, 'Đồ ăn', 2, true WHERE NOT EXISTS (SELECT 1 FROM do_an_uong WHERE ten = 'Mì tôm');
INSERT INTO do_an_uong (ten, gia, phan_loai, diem_doi, con_hang)
SELECT 'Coca-Cola', 12000, 'Nước uống', 1, true WHERE NOT EXISTS (SELECT 1 FROM do_an_uong WHERE ten = 'Coca-Cola');
INSERT INTO do_an_uong (ten, gia, phan_loai, diem_doi, con_hang)
SELECT 'Cơm rang', 30000, 'Đồ ăn', 3, true WHERE NOT EXISTS (SELECT 1 FROM do_an_uong WHERE ten = 'Cơm rang');

-- Khách hàng mẫu
INSERT INTO khach_hang (ten, sdt, so_du, tong_gio, diem)
SELECT 'Nguyễn Văn An', '0901234567', 200000, 15.5, 15 WHERE NOT EXISTS (SELECT 1 FROM khach_hang WHERE sdt = '0901234567');
INSERT INTO khach_hang (ten, sdt, so_du, tong_gio, diem)
SELECT 'Trần Thị Bình', '0912345678', 150000, 10.0, 10 WHERE NOT EXISTS (SELECT 1 FROM khach_hang WHERE sdt = '0912345678');
