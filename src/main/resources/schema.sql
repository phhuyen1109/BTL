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

-- Menu đồ ăn/uống mẫu (4.6)
INSERT INTO food_drinks (name, price, category, available) SELECT 'Mì tôm', 15000, 'Đồ ăn', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Mì tôm');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Coca-Cola', 12000, 'Nước uống', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Coca-Cola');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Cơm rang', 30000, 'Đồ ăn', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Cơm rang');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Pepsi', 12000, 'Nước uống', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Pepsi');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Nước tăng lực Redbull', 15000, 'Nước uống', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Nước tăng lực Redbull');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Trà đá', 5000, 'Nước uống', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Trà đá');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Bánh mì thịt', 20000, 'Đồ ăn', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Bánh mì thịt');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Snack Oishi', 8000, 'Đồ ăn', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Snack Oishi');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Kẹo cao su', 5000, 'Đồ ăn', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Kẹo cao su');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Nước suối', 8000, 'Nước uống', true WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Nước suối');
INSERT INTO food_drinks (name, price, category, available) SELECT 'Cà phê đen', 15000, 'Nước uống', false WHERE NOT EXISTS (SELECT 1 FROM food_drinks WHERE name = 'Cà phê đen');

-- Khách hàng mẫu (4.4, 4.5)
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Nguyễn Văn An', '0901234567', 200000, 15.5 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0901234567');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Trần Thị Bình', '0912345678', 150000, 10.0 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0912345678');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Lê Hoàng Nam', '0923456789', 350000, 28.0 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0923456789');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Phạm Thị Lan', '0934567890', 80000, 5.5 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0934567890');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Đỗ Minh Tuấn', '0945678901', 500000, 42.0 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0945678901');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Vũ Thị Hoa', '0956789012', 120000, 8.0 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0956789012');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Hoàng Văn Đức', '0967890123', 250000, 20.5 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0967890123');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Ngô Thị Mai', '0978901234', 60000, 3.0 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0978901234');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Bùi Quang Huy', '0989012345', 400000, 35.0 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0989012345');
INSERT INTO customers (name, phone, balance, total_hours) SELECT 'Đinh Thị Thu', '0990123456', 180000, 12.5 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE phone = '0990123456');

-- Phiên sử dụng mẫu - dữ liệu 7 ngày để biểu đồ thống kê (4.5, 4.7)
-- Hôm nay
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (1, 1, 'Nguyễn Văn An', DATEADD('HOUR', -3, CURRENT_TIMESTAMP), DATEADD('HOUR', -1, CURRENT_TIMESTAMP), 20000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (13, 5, 'Đỗ Minh Tuấn', DATEADD('HOUR', -5, CURRENT_TIMESTAMP), DATEADD('HOUR', -1, CURRENT_TIMESTAMP), 60000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (14, 9, 'Bùi Quang Huy', DATEADD('HOUR', -4, CURRENT_TIMESTAMP), DATEADD('HOUR', -2, CURRENT_TIMESTAMP), 45000, 'Kết thúc');

-- Hôm qua (ngày -1)
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (3, 3, 'Lê Hoàng Nam', DATEADD('DAY', -1, DATEADD('HOUR', -6, CURRENT_TIMESTAMP)), DATEADD('DAY', -1, DATEADD('HOUR', -2, CURRENT_TIMESTAMP)), 40000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (15, 5, 'Đỗ Minh Tuấn', DATEADD('DAY', -1, DATEADD('HOUR', -8, CURRENT_TIMESTAMP)), DATEADD('DAY', -1, DATEADD('HOUR', -2, CURRENT_TIMESTAMP)), 90000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (2, 2, 'Trần Thị Bình', DATEADD('DAY', -1, DATEADD('HOUR', -4, CURRENT_TIMESTAMP)), DATEADD('DAY', -1, DATEADD('HOUR', -2, CURRENT_TIMESTAMP)), 20000, 'Kết thúc');

-- 2 ngày trước
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (5, 7, 'Hoàng Văn Đức', DATEADD('DAY', -2, DATEADD('HOUR', -7, CURRENT_TIMESTAMP)), DATEADD('DAY', -2, DATEADD('HOUR', -3, CURRENT_TIMESTAMP)), 40000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (16, 4, 'Phạm Thị Lan', DATEADD('DAY', -2, DATEADD('HOUR', -5, CURRENT_TIMESTAMP)), DATEADD('DAY', -2, DATEADD('HOUR', -3, CURRENT_TIMESTAMP)), 30000, 'Kết thúc');

-- 3 ngày trước
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (6, 1, 'Nguyễn Văn An', DATEADD('DAY', -3, DATEADD('HOUR', -8, CURRENT_TIMESTAMP)), DATEADD('DAY', -3, DATEADD('HOUR', -4, CURRENT_TIMESTAMP)), 40000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (17, 9, 'Bùi Quang Huy', DATEADD('DAY', -3, DATEADD('HOUR', -9, CURRENT_TIMESTAMP)), DATEADD('DAY', -3, DATEADD('HOUR', -3, CURRENT_TIMESTAMP)), 90000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (8, 6, 'Vũ Thị Hoa', DATEADD('DAY', -3, DATEADD('HOUR', -5, CURRENT_TIMESTAMP)), DATEADD('DAY', -3, DATEADD('HOUR', -3, CURRENT_TIMESTAMP)), 20000, 'Kết thúc');

-- 4 ngày trước
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (10, 3, 'Lê Hoàng Nam', DATEADD('DAY', -4, DATEADD('HOUR', -6, CURRENT_TIMESTAMP)), DATEADD('DAY', -4, DATEADD('HOUR', -1, CURRENT_TIMESTAMP)), 50000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (18, 5, 'Đỗ Minh Tuấn', DATEADD('DAY', -4, DATEADD('HOUR', -7, CURRENT_TIMESTAMP)), DATEADD('DAY', -4, DATEADD('HOUR', -1, CURRENT_TIMESTAMP)), 90000, 'Kết thúc');

-- 5 ngày trước
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (4, 10, 'Đinh Thị Thu', DATEADD('DAY', -5, DATEADD('HOUR', -5, CURRENT_TIMESTAMP)), DATEADD('DAY', -5, DATEADD('HOUR', -2, CURRENT_TIMESTAMP)), 30000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (19, 7, 'Hoàng Văn Đức', DATEADD('DAY', -5, DATEADD('HOUR', -8, CURRENT_TIMESTAMP)), DATEADD('DAY', -5, DATEADD('HOUR', -2, CURRENT_TIMESTAMP)), 90000, 'Kết thúc');

-- 6 ngày trước
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (7, 2, 'Trần Thị Bình', DATEADD('DAY', -6, DATEADD('HOUR', -4, CURRENT_TIMESTAMP)), DATEADD('DAY', -6, DATEADD('HOUR', -2, CURRENT_TIMESTAMP)), 20000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (20, 9, 'Bùi Quang Huy', DATEADD('DAY', -6, DATEADD('HOUR', -9, CURRENT_TIMESTAMP)), DATEADD('DAY', -6, DATEADD('HOUR', -3, CURRENT_TIMESTAMP)), 90000, 'Kết thúc');
INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, end_time, total_cost, status) VALUES (11, 8, 'Ngô Thị Mai', DATEADD('DAY', -6, DATEADD('HOUR', -5, CURRENT_TIMESTAMP)), DATEADD('DAY', -6, DATEADD('HOUR', -3, CURRENT_TIMESTAMP)), 20000, 'Kết thúc');


