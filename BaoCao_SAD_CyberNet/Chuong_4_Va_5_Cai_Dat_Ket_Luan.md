# CHƯƠNG 4: CÀI ĐẶT (IMPLEMENTATION)

> **👤 PHÂN CÔNG THỰC HIỆN:**
> - **Thành viên 4 (Backend, Tester):** Viết mục 4.1 và 4.2. Chịu trách nhiệm quản lý cấu trúc mã nguồn, vẽ sơ đồ Deployment, đóng gói Fat-JAR deploy ứng dụng.
> - **Thành viên 1 & 2:** Hỗ trợ chuẩn bị Chương 5 và Phụ lục chụp ảnh.

---

## 4.1 Lựa chọn công nghệ
Quyết định kiến trúc của CyberNet tập trung vào việc tạo ra một phần mềm cài đặt siêu tốc, không rào cản.
1. **Ngôn ngữ Lập trình:** `Java 17 (LTS)`.
2. **Framework Giao diện:** `Java Swing`. Để khắc phục yếu điểm giao diện cũ của Swing, nhóm đã tích hợp thư viện **FlatLaf**. Nó phủ một lớp áo Dark Theme mượt mà, giúp ứng dụng trở nên sang trọng như các công nghệ web hiện đại.
3. **Cơ sở Dữ liệu:** `H2 Database Embedded Engine`. Giải quyết hoàn hảo bài toán Portable (Mang app đi nơi khác). Không cần cài đặt bất kỳ SQL Server nào, database được lưu tại file `quanlyquaninternet.mv.db`.
4. **Công cụ Build:** `Apache Maven` kết hợp Shade Plugin để nén tất cả class và ảnh vào file `.jar` duy nhất.

## 4.2 Cấu trúc mã nguồn
Sử dụng Maven, thư mục được phân bố theo chuẩn:
```text
src/main/java/com/mycompany/quanlyquaninternet/
├── controller/         # Chứa Logic và Event Listeners
├── dao/                # Chứa class truy cập H2 (KhachHangDAO, MayTinhDAO)
├── entity/             # Các Entity tương ứng CSDL
├── view/               # Toàn bộ Panel, Frame giao diện Swing
└── QuanLyQuanInternet.java # File Main
```

---

# 5. KẾT LUẬN

**Đánh giá phần mềm:**
Phần mềm Quản lý CyberNet đã đạt được độ hoàn thiện xuất sắc. Tốc độ khởi chạy nhanh, thuật toán tính thời gian/tiền chính xác tuyệt đối và giao diện Dark mode trực quan, hỗ trợ tối đa cho nhân viên phòng máy. Hạn chế nhỏ duy nhất là phần mềm hiện tại chưa tích hợp lệnh mở khóa màn hình qua mạng LAN. Nhóm dự định sẽ mở rộng kiến trúc Client-Server sử dụng TCP Socket trong tương lai.

**Kết luận chung:**
Đồ án môn Phân tích và Thiết kế Phần mềm đã mang lại cho nhóm một bức tranh toàn cảnh về quy trình sản xuất phần mềm. Cảm ơn giảng viên TS. Mai Thúy Nga đã tận tình hướng dẫn.

---

# TÀI LIỆU THAM KHẢO
1. TS. Mai Thúy Nga (2026), *Bài giảng Phân tích và Thiết kế Hệ thống Thông tin*, Đại học Phenikaa.
2. Craig Walls (2022), *Spring in Action, 6th Edition*, Manning Publications.
3. Tài liệu trang chủ FlatLaf - Flat Look and Feel for Java Swing: `https://www.formdev.com/flatlaf/`
4. Tài liệu trang chủ H2 Database Engine: `http://www.h2database.com/`

---

# PHỤ LỤC (nếu có)
- **Hình 1:** Giao diện Màn hình Đăng nhập (Mã nguồn đính kèm file).
- **Hình 2:** Giao diện Quản lý Máy Tính - Phiên hoạt động thực tế.
- **Hình 3:** Giao diện Thống kê Doanh thu dạng biểu đồ cột.
*(Ghi chú cho nhóm: Các thành viên tự chụp ảnh màn hình phần mềm Java đang chạy trên máy tính và dán đè vào mục này).*
