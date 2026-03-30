-- Bảng tài khoản
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'staff'
);

-- Bảng máy tính
CREATE TABLE IF NOT EXISTS computers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL DEFAULT 'Thường',
    price_per_hour DOUBLE NOT NULL DEFAULT 10000,
    status VARCHAR(20) NOT NULL DEFAULT 'Trống',
    specs VARCHAR(200)
);

-- Bảng khách hàng
CREATE TABLE IF NOT EXISTS customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    balance DOUBLE DEFAULT 0,
    total_hours DOUBLE DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

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
);

-- Bảng đồ ăn/uống
CREATE TABLE IF NOT EXISTS food_drinks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(50) DEFAULT 'Nước uống',
    available BOOLEAN DEFAULT TRUE
);

-- Bảng đơn hàng
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT,
    computer_id INT NOT NULL,
    total_price DOUBLE DEFAULT 0,
    order_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE SET NULL,
    FOREIGN KEY (computer_id) REFERENCES computers(id)
);

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
);

-- =============================================
-- DỮ LIỆU MẪU (Chỉ insert nếu chưa có)
-- =============================================

INSERT INTO users (username, password, role) 
SELECT 'admin', 'admin', 'admin' 
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

-- 20 máy tính (12 Thường + 8 VIP)
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 01', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 01');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 02', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 02');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 03', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 03');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 04', 'Thường', 10000, 'Trống', 'Core i5-12400, 16GB RAM, GTX 1650' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 04');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 05', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 05');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 06', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 06');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 07', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 07');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 08', 'Thường', 10000, 'Trống', 'Core i5-12400, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 08');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 09', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 09');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 10', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 10');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 11', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 11');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 12', 'Thường', 10000, 'Trống', 'Core i3-12100, 8GB RAM, GTX 1050' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 12');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 13', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 13');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 14', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 14');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 15', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 15');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 16', 'VIP', 15000, 'Trống', 'Core i7-13700, 32GB RAM, RTX 3060' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 16');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 17', 'VIP', 15000, 'Trống', 'Core i9-13900, 32GB RAM, RTX 4070' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 17');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 18', 'VIP', 15000, 'Trống', 'Core i9-13900, 32GB RAM, RTX 4070' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 18');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 19', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 19');
INSERT INTO computers (name, type, price_per_hour, status, specs) 
SELECT 'Máy 20', 'VIP', 15000, 'Trống', 'Core i9-13900, 64GB RAM, RTX 4080' WHERE NOT EXISTS (SELECT 1 FROM computers WHERE name = 'Máy 20');

-- Menu đồ ăn/uống mẫu
INSERT INTO food_drinks (name, price, category, available) 
SELECT 'Mì tôm', 15000, 'Đồ ăn', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Mì tôm');
INSERT INTO food_drinks (name, price, category, available) 
SELECT 'Coca-Cola', 12000, 'Nước uống', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Coca-Cola');
INSERT INTO food_drinks (name, price, category, available) 
SELECT 'Cơm rang', 30000, 'Đồ ăn', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Cơm rang');

-- Khách hàng mẫu
INSERT INTO customers (name, phone, balance, total_hours) 
SELECT 'Nguyễn Văn An', '0901234567', 200000, 15.5 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0901234567');
INSERT INTO customers (name, phone, balance, total_hours) 
SELECT 'Trần Thị Bình', '0912345678', 150000, 10.0 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0912345678');
