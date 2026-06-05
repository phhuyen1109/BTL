# CHƯƠNG 3: THIẾT KẾ (USE-CASE DESIGN)

> **👤 PHÂN CÔNG THỰC HIỆN CHƯƠNG NÀY:**
> - **Thành viên 1 (Trưởng nhóm, Backend/Database):** Chịu trách nhiệm toàn bộ nội dung chương này. Vẽ sơ đồ Lớp (Class Diagram), thiết kế cơ sở dữ liệu, các bảng và định nghĩa ràng buộc.

---

## 3.1 Xác định các thành phần thiết kế (Identify design elements)

### 3.1.1 Xác định các lớp (Identify classes)
Hệ thống sử dụng các Lớp thực thể (Entity Classes) phản ánh trực tiếp logic nghiệp vụ:
- `NguoiDung`: Chứa `id, username, password, role`.
- `KhachHang`: Chứa `id, ten, sdt, soDu, tongGio, diem`.
- `MayTinh`: Chứa `id, ten, loai, giaMoiGio, trangThai`.
- `PhienSuDung`: Chứa `id, maMayTinh, maKhachHang, gioBatDau, gioKetThuc, tongTien`.
- `DoAnUong`: Chứa `id, ten, gia, phanLoai, diemDoi, conHang`.
- Lớp Service/DAO: `KetNoiCSDL` (Singleton Connection), `KhachHangDAO`, `MayTinhDAO`, `PhienSuDungDAO`.

### 3.1.2 Xác định các hệ thống con và giao diện (Identify subsystems and interfaces)
- Hệ thống con **Xác thực (Auth):** Chịu trách nhiệm quản lý đăng nhập, session của nhân viên (Admin/Staff).
- Hệ thống con **Cốt lõi (Core Business):** Quản lý phiên sử dụng, bật tắt máy.
- Hệ thống con **Kế toán (Accounting):** Tính toán giờ chơi, trừ tiền, quản lý đơn hàng dịch vụ, thống kê doanh thu.

### 3.1.3 Xác định các gói (Identify packages)
Cấu trúc mã nguồn được phân bổ vào các package chính để tuân thủ MVC:
- `com.mycompany.quanlyquaninternet.entity`: Chứa các lớp Model/Entity.
- `com.mycompany.quanlyquaninternet.dao`: Chứa các lớp xử lý CSDL.
- `com.mycompany.quanlyquaninternet.controller`: Chứa bộ điều khiển.
- `com.mycompany.quanlyquaninternet.view`: Chứa các cửa sổ, Panel giao diện.

---

## 3.2 Thiết kế trường hợp sử dụng (Use-case design)

### 3.2.1 Thiết kế các biểu đồ tuần tự (Design sequence diagrams)
*(Dựa trên biểu đồ tuần tự đã phân tích ở Chương 2, đi sâu hơn vào việc bổ sung các hàm, tham số cụ thể của từng lớp sẽ được truyền đi - Thành viên 1 sẽ vẽ sơ đồ này)*

### 3.2.2 Thiết kế biểu đồ lớp (Class diagrams)
*(Thành viên 1 chèn Class Diagram thể hiện quan hệ kế thừa, liên kết, dependency giữa các lớp Entity, DAO và View)*

---

## 3.3 Thiết kế cơ sở dữ liệu (Database design)

### 3.3.1 Lược đồ cơ sở dữ liệu (ERD)
*(Thành viên 1 vẽ sơ đồ Entity Relationship Diagram cho 8 bảng: NGUOI_DUNG, KHACH_HANG, MAY_TINH, PHIEN_SU_DUNG, DO_AN_UONG, DON_HANG, CHI_TIET_DON_HANG, LICH_SU_DOI_THUONG)*

### 3.3.2 Chi tiết các bảng

**Bảng 1: KHACH_HANG**
| Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|---|---|---|---|
| ma | INT | PK, AUTO_INCREMENT | Khóa chính |
| ten | VARCHAR(100) | NOT NULL | Tên khách hàng |
| sdt | VARCHAR(20) | NULL | Số điện thoại liên hệ |
| so_du | DOUBLE | DEFAULT 0 | Tiền thực tế nạp vào |
| tong_gio | DOUBLE | DEFAULT 0 | Tổng giờ chơi khách có |
| diem | INT | DEFAULT 0 | Điểm tích lũy để đổi đồ |

**Bảng 2: MAY_TINH**
| Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|---|---|---|---|
| ma | INT | PK, AUTO_INCREMENT | Khóa chính |
| ten | VARCHAR(50) | NOT NULL | Tên máy (VD: Máy 01) |
| loai | VARCHAR(20) | DEFAULT 'Thường' | Phân loại máy (VIP/Thường) |
| gia_moi_gio | DOUBLE | NOT NULL | Giá tiền 1 giờ chơi |
| trang_thai | VARCHAR(20) | DEFAULT 'Trống' | Trống/Đang dùng/Bảo trì |

**Bảng 3: PHIEN_SU_DUNG**
| Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|---|---|---|---|
| ma | INT | PK, AUTO_INCREMENT | Khóa chính |
| ma_may_tinh | INT | FK | ID Máy tính |
| ma_khach_hang | INT | FK, NULL | ID Khách (Null = vãng lai) |
| gio_bat_dau | DATETIME | NOT NULL | Thời gian mở máy |
| gio_ket_thuc | DATETIME | NULL | Thời gian đóng máy |
| tong_tien | DOUBLE | DEFAULT 0 | Tiền thực thu từ phiên |
| trang_thai | VARCHAR(20) | DEFAULT 'Đang chạy'| Trạng thái phiên |

**Bảng 4: DO_AN_UONG (MENU)**
| Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|---|---|---|---|
| ma | INT | PK, AUTO_INCREMENT | Khóa chính |
| ten | VARCHAR(100) | NOT NULL | Tên món ăn/đồ uống |
| gia | DOUBLE | NOT NULL | Giá bán ra |
| phan_loai | VARCHAR(50) | | Đồ ăn / Nước uống |
| diem_doi | INT | DEFAULT 0 | Số điểm yêu cầu để đổi free |
| con_hang | BOOLEAN | DEFAULT TRUE | Tình trạng phục vụ |

*(Các bảng khác như NGUOI_DUNG, DON_HANG, LICH_SU_DOI_THUONG, Thành viên 1 bổ sung thêm dựa trên schema.sql)*
