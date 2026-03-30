-- =============================================
-- Quản Lý Quán Internet - Database Setup
-- =============================================
-- Chạy script này trong MySQL trước khi khởi động app
-- mysql -u root -p < init_database.sql
-- =============================================

CREATE DATABASE IF NOT EXISTS quanly_internet
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE quanly_internet;

-- Bảng tài khoản
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'staff'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Bảng máy tính
CREATE TABLE IF NOT EXISTS computers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL DEFAULT 'Thường',
    price_per_hour DOUBLE NOT NULL DEFAULT 10000,
    status VARCHAR(20) NOT NULL DEFAULT 'Trống',
    specs VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Bảng khách hàng
CREATE TABLE IF NOT EXISTS customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    balance DOUBLE DEFAULT 0,
    total_hours DOUBLE DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Bảng phiên sử dụng
CREATE TABLE IF NOT EXISTS sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    computer_id INT NOT NULL,
    customer_id INT,
    customer_name VARCHAR(100),
    start_time DATETIME NOT NULL,
    end_time DATETIME,
    total_cost DOUBLE DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'Đang chạy',
    FOREIGN KEY (computer_id) REFERENCES computers(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Bảng đồ ăn/uống
CREATE TABLE IF NOT EXISTS food_drinks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(50) DEFAULT 'Nước uống',
    available BOOLEAN DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Bảng đơn hàng
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT,
    computer_id INT NOT NULL,
    total_price DOUBLE DEFAULT 0,
    order_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE SET NULL,
    FOREIGN KEY (computer_id) REFERENCES computers(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Bảng chi tiết đơn hàng
CREATE TABLE IF NOT EXISTS order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    food_drink_id INT NOT NULL,
    food_drink_name VARCHAR(100),
    quantity INT NOT NULL DEFAULT 1,
    price DOUBLE NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (food_drink_id) REFERENCES food_drinks(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- DỮ LIỆU MẪU
-- =============================================

-- Tài khoản admin mặc định
INSERT INTO users (username, password, role) VALUES ('admin', 'admin', 'admin');

-- 20 máy tính (12 Thường + 8 VIP)
INSERT INTO computers (name, type, price_per_hour, status, specs) VALUES
('Máy 01', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650'),
('Máy 02', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650'),
('Máy 03', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650'),
('Máy 04', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650'),
('Máy 05', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050'),
('Máy 06', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050'),
('Máy 07', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050'),
('Máy 08', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050'),
('Máy 09', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050'),
('Máy 10', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050'),
('Máy 11', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050'),
('Máy 12', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050'),
('Máy 13', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060'),
('Máy 14', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060'),
('Máy 15', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060'),
('Máy 16', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060'),
('Máy 17', 'VIP', 15000, 'Trống', 'Core i9-13900, 32GB RAM, RTX 4070'),
('Máy 18', 'VIP', 15000, 'Trống', 'Core i9-13900, 32GB RAM, RTX 4070'),
('Máy 19', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080'),
('Máy 20', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080');

-- Menu đồ ăn/uống mẫu
INSERT INTO food_drinks (name, price, category, available) VALUES
('Mì tôm', 15000, 'Đồ ăn', true),
('Mì trứng', 20000, 'Đồ ăn', true),
('Cơm rang', 30000, 'Đồ ăn', true),
('Xúc xích', 10000, 'Đồ ăn', true),
('Bánh mì', 15000, 'Đồ ăn', true),
('Trà đá', 5000, 'Nước uống', true),
('Coca-Cola', 12000, 'Nước uống', true),
('Pepsi', 12000, 'Nước uống', true),
('Nước suối', 8000, 'Nước uống', true),
('Trà sữa', 25000, 'Nước uống', true),
('Cà phê đen', 15000, 'Nước uống', true),
('Cà phê sữa', 18000, 'Nước uống', true),
('Red Bull', 15000, 'Nước uống', true),
('Sting', 10000, 'Nước uống', true),
('Snack', 10000, 'Đồ ăn', true);

-- Khách hàng mẫu
INSERT INTO customers (name, phone, balance, total_hours) VALUES
('Nguyễn Văn An', '0901234567', 200000, 15.5),
('Trần Thị Bình', '0912345678', 150000, 10.0),
('Lê Hoàng Cường', '0923456789', 500000, 45.0),
('Phạm Đức Dũng', '0934567890', 100000, 8.5),
('Hoàng Minh Đức', '0945678901', 300000, 22.0);
