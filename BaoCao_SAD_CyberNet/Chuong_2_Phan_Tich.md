# CHƯƠNG 2: PHÂN TÍCH TRƯỜNG HỢP SỬ DỤNG (USE-CASE ANALYSIS)

> **👤 PHÂN CÔNG THỰC HIỆN CHƯƠNG NÀY:**
> - **Thành viên 3 (BA, Phân tích nghiệp vụ):** Chịu trách nhiệm chính. Phân tích kiến trúc mức cao và vẽ các sơ đồ Sequence (Biểu đồ tuần tự) và Activity (Biểu đồ hoạt động) cho các quy trình nghiệp vụ (Mục 2.1.1 và 2.2.1).
> - **Thành viên 4 (Backend, Tester):** Chịu trách nhiệm cung cấp góc nhìn về các Lớp (Views of participating classes) dựa trên code Backend thực tế (Mục 2.1.2 và 2.2.2).

---

## 2.1 Phân tích kiến trúc hệ thống
**(👤 Người viết: Thành viên 3 + Thành viên 4)**

### 2.1.1 Kiến trúc mức cao của hệ thống
Hệ thống quản lý quán Internet CyberNet được thiết kế theo mô hình **MVC (Model-View-Controller)** kết hợp chặt chẽ với mẫu thiết kế **DAO (Data Access Object)** nhằm tách biệt giao diện, nghiệp vụ và thao tác CSDL:
- **View (Giao diện):** Sử dụng Java Swing và FlatLaf. Chia thành các Module Panel độc lập như `PanelKhachHang`, `PanelMayTinh`, `PanelDoAnUong` và điều hướng qua `GiaoDienChinh` (sử dụng CardLayout).
- **Controller (Điều khiển):** Xử lý sự kiện từ người dùng (ActionListeners), tiếp nhận dữ liệu từ View, thực hiện validation cơ bản và gọi các phương thức tương ứng từ DAO.
- **Model/Entity (Thực thể):** Các lớp POJO ánh xạ với các bảng trong CSDL (như `KhachHang`, `MayTinh`, `PhienSuDung`).
- **DAO Layer:** Lớp đảm nhận giao tiếp với cơ sở dữ liệu H2 Database (`KhachHangDAO`, `MayTinhDAO`). Sử dụng `KetNoiCSDL` với design pattern Singleton để duy trì duy nhất một kết nối xuyên suốt ứng dụng.

### 2.1.2 Các đối tượng trừu tượng hóa chính của hệ thống (Key abstractions)
Các đối tượng kinh doanh (Business objects) cốt lõi đóng vai trò xương sống cho hoạt động của quán:
- **MayTinh (Máy trạm):** Chứa thông tin về giá tiền/giờ, trạng thái (trống, đang dùng, bảo trì).
- **KhachHang (Tài khoản người dùng):** Gắn liền với ví tiền, thời gian chơi còn lại và điểm thưởng đổi quà.
- **PhienSuDung (Session):** Đóng vai trò cầu nối giữa Máy tính và Khách hàng trong một khoảng thời gian nhất định, chịu trách nhiệm lưu vết để tính tổng doanh thu sau này.

---

## 2.2 Thực thi trường hợp sử dụng (Use-case realizations)
**(👤 Người vẽ biểu đồ: Thành viên 3)**

*(Ghi chú cho nhóm: Thành viên 3 sẽ sử dụng draw.io hoặc StarUML để vẽ các biểu đồ dưới đây rồi chèn hình ảnh vào báo cáo Word)*

### 2.2.1 Các biểu đồ tuần tự (Sequence diagrams)

**1. Sơ đồ tuần tự Đăng nhập:**
- `User` nhập thông tin → `GiaoDienDangNhap` → truyền đến `DieuKhienDangNhap` → `KetNoiCSDL` xác thực tài khoản → Trả kết quả (Sai: báo lỗi, Đúng: khởi tạo `GiaoDienChinh`).

**2. Sơ đồ tuần tự Bắt đầu Phiên sử dụng:**
- `User` click máy trống trên `PanelMayTinh` → Hiển thị Dialog → Gọi `KhachHangDAO.layTatCa()` để chọn khách → User nhấn OK → Gọi `PhienSuDungDAO.batDauPhien()` → Gọi `MayTinhDAO.capNhatTrangThai()` sang "Đang dùng" → Refresh `PanelMayTinh`.

**3. Sơ đồ tuần tự Đổi thưởng:**
- `User` mở đổi thưởng trên `PanelKhachHang` → Hiển thị danh sách dịch vụ qua `DoAnUongDAO.layConHang()` → User chọn món và xác nhận → View kiểm tra điểm khách hàng có lớn hơn điểm món không → Gọi `LichSuDoiThuongDAO.ghiLichSu()` → Gọi `KhachHangDAO.truDiem()` → Hiển thị thông báo thành công.

### 2.2.2 Góc nhìn của các lớp trong hệ thống (Views of participating classes)
**(👤 Người viết: Thành viên 4)**

*(Mô tả sự tương tác giữa các lớp với nhau trong một Use Case)*

Ví dụ đối với chức năng **Tính tiền kết thúc phiên**:
- Lớp giao diện `PanelMayTinh` gửi yêu cầu kết thúc phiên.
- Lớp `PhienSuDungDAO` truy vấn thông tin phiên hiện tại, tính toán số giờ chơi dựa vào `gioBatDau` và thời gian hệ thống hiện tại.
- Lớp `DonHangDAO` truy vấn lấy tổng số tiền dịch vụ thức ăn/nước uống gắn liền với mã phiên đó.
- Sau khi có Tổng tiền = Tiền máy + Tiền dịch vụ, hệ thống gọi hàm của `KhachHangDAO` để thực hiện giao dịch trừ `soDu`, đồng thời cộng `tongGio` và `diem` cho thực thể `KhachHang`.
- Cuối cùng `MayTinhDAO` đưa máy tính trở về trạng thái rảnh rỗi.
Tất cả các lớp DAO trên đều chia sẻ chung một phiên kết nối từ lớp Singleton `KetNoiCSDL`.
