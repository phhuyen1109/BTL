# CHƯƠNG 2: PHÂN TÍCH TRƯỜNG HỢP SỬ DỤNG (USE-CASE ANALYSIS)

> **👤 PHÂN CÔNG THỰC HIỆN:**
> - **Thành viên 3 (BA, Phân tích nghiệp vụ):** Chịu trách nhiệm thiết kế, lập luận kiến trúc và vẽ các Biểu đồ Tuần tự (Sequence Diagram), Sơ đồ Hoạt động luồng dữ liệu.
> - **Thành viên 4 (Backend Developer):** Hỗ trợ lập tài liệu mô tả tương tác giữa các Lớp (Views of participating classes) dựa vào source code Controller/DAO.

---

## 2.1 Phân tích kiến trúc hệ thống

### 2.1.1 Kiến trúc mức cao (High-level Architecture)
Hệ thống quản lý quán Internet CyberNet được thiết kế mạnh mẽ dựa trên sự kết hợp giữa **mô hình MVC (Model-View-Controller)** và thiết kế **DAO (Data Access Object) Pattern**. Lựa chọn này giúp hệ thống tách biệt rành mạch giữa dữ liệu thô, logic xử lý nghiệp vụ và thành phần giao diện, từ đó tạo tiền đề bảo trì dễ dàng sau này.

1. **Lớp View (Giao diện):** Xây dựng bằng Java Swing kết hợp FlatLaf. Đảm nhận việc vẽ các màn hình, lắng nghe thao tác click chuột.
2. **Lớp Controller (Điều khiển):** Bắt sự kiện (Event Listener) từ lớp View. Thực hiện Data Validation. Điều phối luồng xử lý bằng cách gọi các dịch vụ từ lớp DAO.
3. **Lớp DAO (Data Access):** Tương tác trực tiếp với file database nhúng H2.
4. **Lớp Entity (Thực thể):** Ánh xạ cấu trúc bảng trong DB thành đối tượng Java (POJO).

---

## 2.2 Sơ đồ Hoạt động Hệ thống (System Activity Diagrams)
Sơ đồ hoạt động đi sâu vào việc giải quyết tuần tự các điều kiện logic của hệ thống.

### 2.2.1 Sơ đồ Hoạt động: Đăng nhập Hệ thống
Mô tả quy trình kiểm tra bảo mật trước khi truy cập phần mềm.

```mermaid
stateDiagram-v2
    [*] --> NhapThongTin: Nhân viên nhập Username & Password
    NhapThongTin --> KiemTraRong: Bấm "Đăng nhập"
    
    KiemTraRong --> NhapThongTin: Nếu bỏ trống
    KiemTraRong --> TruyenVaoDB: Dữ liệu đầy đủ
    
    TruyenVaoDB --> KiemTraSai: Mật khẩu sai / Tài khoản không tồn tại
    KiemTraSai --> NhapThongTin: Báo lỗi "Thông tin không chính xác"
    
    TruyenVaoDB --> DangNhapThanhCong: Mật khẩu đúng
    DangNhapThanhCong --> MoGiaoDienChinh: Đóng form Login, Load Dashboard
    MoGiaoDienChinh --> [*]
```

---

## 2.3 Thực thi trường hợp sử dụng (Use-case realizations - Sequence Diagrams)

Các biểu đồ tuần tự (Sequence Diagram) thể hiện sự giao tiếp thông điệp (Message Passing) giữa các thành phần MVC.

### 2.3.1 Biểu đồ Tuần tự: Quy trình Bắt đầu Phiên (Mở máy)

```mermaid
sequenceDiagram
    actor Staff as Nhân viên
    participant View as PanelMayTinh (View)
    participant DAO_KH as KhachHangDAO
    participant DAO_Phien as PhienSuDungDAO
    participant DAO_May as MayTinhDAO
    participant DB as H2 Database

    Staff->>View: Click đúp vào Máy Trống
    View->>DAO_KH: layTatCa()
    DAO_KH->>DB: SELECT * FROM KHACH_HANG
    DB-->>DAO_KH: Trả về Danh sách
    DAO_KH-->>View: Render Combobox
    
    Staff->>View: Chọn khách & Bấm "Bắt đầu"
    View->>DAO_Phien: batDauPhien(maMay, maKhach, thoiGian)
    DAO_Phien->>DB: INSERT INTO PHIEN_SU_DUNG
    DB-->>DAO_Phien: Return Success
    
    DAO_Phien-->>View: Thông báo Đã tạo phiên
    
    View->>DAO_May: capNhatTrangThai(maMay, "Đang dùng")
    DAO_May->>DB: UPDATE MAY_TINH SET trang_thai='Đang dùng'
    DB-->>DAO_May: Return Success
    
    DAO_May-->>View: Reload lại Giao diện máy tính (đổi sang màu đỏ)
```

### 2.3.2 Biểu đồ Tuần tự: Nạp Tiền & Tự động Cộng Điểm

```mermaid
sequenceDiagram
    actor Staff as Nhân viên
    participant View as PanelKhachHang
    participant KH_Entity as KhachHang (Entity)
    participant DAO_KH as KhachHangDAO
    participant DB as H2 Database

    Staff->>View: Nhập 50,000 VND và Nhấn "Nạp tiền"
    View->>View: Validate (Số tiền > 0)
    
    View->>View: Tính toán (+5 Giờ chơi, +5 Điểm)
    
    View->>KH_Entity: Cập nhật object (soDu+=50000, diem+=5)
    
    View->>DAO_KH: capNhatKhachHang(khachHangObj)
    DAO_KH->>DB: UPDATE KHACH_HANG
    DB-->>DAO_KH: Rows affected = 1
    
    DAO_KH-->>View: Trả về True
    View-->>Staff: Báo Nạp Tiền Thành Công và Reload Bảng
```

### 2.3.3 Biểu đồ Tuần tự: Kết thúc Phiên & Thanh toán

```mermaid
sequenceDiagram
    actor Staff as Nhân viên
    participant View as PanelMayTinh
    participant DAO_Phien as PhienSuDungDAO
    participant DAO_DonHang as DonHangDAO
    participant DAO_May as MayTinhDAO

    Staff->>View: Nhấn "Thanh toán/Kết thúc"
    View->>DAO_Phien: layThongTinPhienHienTai()
    DAO_Phien-->>View: Trả về Phien_Object (gioBatDau)
    
    View->>DAO_DonHang: tinhTongTienDichVu(maPhien)
    DAO_DonHang-->>View: Trả về Tiền Đồ Ăn
    
    View->>View: Tính (Tiền Giờ = TGian x Đơn Giá)
    View->>View: Tổng Hóa Đơn = Tiền Giờ + Tiền Đồ Ăn
    
    View->>DAO_Phien: dongPhien(maPhien, TongHoaDon)
    View->>DAO_May: resetTrangThaiMay(maMay)
    
    View-->>Staff: Hiển thị Hóa Đơn Chi Tiết
```
