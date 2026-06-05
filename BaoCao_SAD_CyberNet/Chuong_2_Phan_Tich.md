# CHƯƠNG 2: PHÂN TÍCH TRƯỜNG HỢP SỬ DỤNG (USE-CASE ANALYSIS)

> **👤 PHÂN CÔNG THỰC HIỆN:**
> - **Thành viên 3 (BA, Phân tích nghiệp vụ):** Chịu trách nhiệm thiết kế, lập luận kiến trúc và vẽ các Biểu đồ Tuần tự (Sequence Diagram).
> - **Thành viên 4 (Backend Developer):** Hỗ trợ lập tài liệu mô tả tương tác giữa các Lớp (Views of participating classes) dựa vào source code Controller/DAO.

---

## 2.1 Phân tích kiến trúc hệ thống

### 2.1.1 Kiến trúc mức cao (High-level Architecture)
Hệ thống quản lý quán Internet CyberNet được thiết kế mạnh mẽ dựa trên sự kết hợp giữa **mô hình MVC (Model-View-Controller)** và thiết kế **DAO (Data Access Object) Pattern**. Lựa chọn này giúp hệ thống tách biệt rành mạch giữa dữ liệu thô, logic xử lý nghiệp vụ và thành phần giao diện, từ đó tạo tiền đề bảo trì dễ dàng sau này.

1. **Lớp View (Giao diện):** 
   - Nhiệm vụ: Xây dựng bằng Java Swing kết hợp FlatLaf. Đảm nhận việc vẽ các màn hình, lắng nghe thao tác click chuột của thu ngân, hiển thị thông báo lỗi.
   - Các thành phần chính: `GiaoDienDangNhap`, `GiaoDienChinh`, các module Panel như `PanelMayTinh`, `PanelDoAnUong`.

2. **Lớp Controller (Điều khiển):** 
   - Nhiệm vụ: Bắt sự kiện (Event Listener) từ lớp View. Thực hiện Data Validation (Ví dụ: kiểm tra số tiền nạp phải lớn hơn 0). Điều phối luồng xử lý bằng cách gọi các dịch vụ từ lớp DAO.
   - Việc tách logic ra khỏi View giúp các form UI luôn nhẹ và có phản hồi mượt mà.

3. **Lớp DAO (Data Access) & Singleton Database:** 
   - Nhiệm vụ: Tương tác trực tiếp với file database nhúng H2 (`quanlyquaninternet.mv.db`).
   - Mẫu thiết kế Singleton được áp dụng nghiêm ngặt cho class `KetNoiCSDL`. Toàn bộ quá trình chạy ứng dụng chỉ tạo ra **duy nhất một connection** (một ống nước duy nhất) tới database. Các DAO như `KhachHangDAO`, `MayTinhDAO` đều mượn chung kết nối này, giúp tối ưu hóa bộ nhớ và không gặp lỗi "database locked".

4. **Lớp Entity (Thực thể):**
   - Nhiệm vụ: Ánh xạ cấu trúc bảng trong DB thành đối tượng Java (POJO). Bao gồm các fields, Getter, Setter và Constructor tương ứng.

### 2.1.2 Các đối tượng trừu tượng hóa cốt lõi (Key Abstractions)
Trong quá trình phân tích bài toán quán Internet, nhóm đã khoanh vùng được 4 thực thể trừu tượng lớn nhất:
- **Tài khoản (Account/NguoiDung):** Thực thể đại diện cho người vận hành phần mềm. 
- **Máy Trạm (PC Station/MayTinh):** Là tài sản cố định của quán, nơi diễn ra hành vi sử dụng. Mỗi máy được định tuyến giá cả khác nhau (Máy VIP đắt hơn Máy Thường).
- **Khách Hàng (Customer):** Thực thể di động, nắm giữ tài sản tiền tệ và điểm thưởng để trao đổi.
- **Phiên (Session/PhienSuDung):** **Đây là thực thể quan trọng nhất.** Đóng vai trò cầu nối N-N giữa Máy Trạm và Khách Hàng. Bất cứ khi nào Khách hàng ngồi vào Máy Trạm, một Phiên mới được khai sinh.

---

## 2.2 Thực thi trường hợp sử dụng (Use-case realizations)

Để minh họa luồng đi của dữ liệu từ khi Nhân viên click chuột trên View cho đến khi dữ liệu nằm sâu trong Database, nhóm sử dụng các Biểu đồ Tuần tự (Sequence Diagram).

### 2.2.1 Biểu đồ Tuần tự: Quy trình Bắt đầu Phiên (Mở máy)

```mermaid
sequenceDiagram
    actor Staff as Nhân viên
    participant View as PanelMayTinh (View)
    participant DAO_KH as KhachHangDAO
    participant DAO_Phien as PhienSuDungDAO
    participant DAO_May as MayTinhDAO
    participant DB as H2 Database

    Staff->>View: Click đúp vào Máy Trống
    View->>DAO_KH: layTatCa() - Tải ds khách
    DAO_KH->>DB: SELECT * FROM KHACH_HANG
    DB-->>DAO_KH: Trả về Danh sách Khách hàng
    DAO_KH-->>View: Hiển thị List Khách vào Combobox
    
    Staff->>View: Chọn khách & Bấm "Bắt đầu"
    View->>DAO_Phien: batDauPhien(maMay, maKhach, thoiGian)
    DAO_Phien->>DB: INSERT INTO PHIEN_SU_DUNG
    DB-->>DAO_Phien: Trả về (Success)
    
    DAO_Phien-->>View: Thông báo Đã tạo phiên
    
    View->>DAO_May: capNhatTrangThai(maMay, "Đang dùng")
    DAO_May->>DB: UPDATE MAY_TINH SET trang_thai='Đang dùng'
    DB-->>DAO_May: Trả về (Success)
    
    DAO_May-->>View: Reload lại Giao diện máy tính (đổi sang màu đỏ)
```

**Mô tả luồng tương tác:**
- Khi giao diện `PanelMayTinh` nhận tín hiệu mở máy, nó sẽ chủ động kéo danh sách khách hàng từ `KhachHangDAO` lên bộ nhớ để nhân viên lựa chọn.
- Ngay khi có lệnh xác nhận, hai truy vấn CSDL liên tục được thực thi: Một truy vấn INSERT để sinh ra Phiên, và một truy vấn UPDATE để khóa cứng máy tính thành trạng thái 'Đang dùng'.
- Tính nguyên vẹn dữ liệu được bảo vệ. Nếu ghi Phiên thất bại, máy tính sẽ không bị đổi trạng thái sai lầm.

### 2.2.2 Biểu đồ Tuần tự: Nạp Tiền & Tự động Cộng Điểm

```mermaid
sequenceDiagram
    actor Staff as Nhân viên
    participant View as PanelKhachHang
    participant KH_Entity as KhachHang (POJO)
    participant DAO_KH as KhachHangDAO
    participant DB as H2 Database

    Staff->>View: Nhập 50,000 VND và Nhấn "Nạp tiền"
    View->>View: Kiểm tra Validate (Số tiền > 0)
    
    View->>View: Tính toán: +5 Giờ chơi, +5 Điểm
    
    View->>KH_Entity: Tạo đối tượng (soDu = soDu + 50000)
    View->>KH_Entity: (tongGio = tongGio + 5, diem = diem + 5)
    
    View->>DAO_KH: capNhatKhachHang(khachHangObj)
    DAO_KH->>DB: UPDATE KHACH_HANG SET so_du, tong_gio, diem
    DB-->>DAO_KH: Return (Rows affected)
    
    DAO_KH-->>View: Return True (Thành công)
    View-->>Staff: Báo hiệu Nạp Tiền Thành Công và Tải lại Table
```

**Mô tả luồng tương tác:**
- Điểm khác biệt trong logic này là **sự tính toán được thực hiện tại View/Controller** trước khi đóng gói đẩy xuống DAO.
- Thao tác nạp tiền không chỉ cập nhật ví ảo `so_du`, mà phần mềm được cài đặt thuật toán tự quy đổi: `Số Giờ = Tiền Nạp / Giá Trị Trung Bình`.
- Class `KhachHangDAO` chỉ thực hiện duy nhất 1 câu SQL UPDATE thay vì phải chạy 3 câu SQL rời rạc cho tiền, giờ, và điểm. Điều này tăng cường hiệu suất tối đa.
