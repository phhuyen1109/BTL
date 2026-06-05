# CHƯƠNG 3: THIẾT KẾ (USE-CASE DESIGN)

> **👤 PHÂN CÔNG THỰC HIỆN:**
> - **Thành viên 1 (Trưởng nhóm, Database/Backend):** Chịu trách nhiệm toàn bộ nội dung chương này.

---

## 3.1 Xác định các thành phần thiết kế (Identify design elements)

### 3.1.1 Xác định các lớp (Identify classes)
Quá trình ánh xạ từ yêu cầu người dùng sang code Java được hiện thực hóa qua các thành phần thực thể cốt lõi: `MayTinh`, `KhachHang`, `PhienSuDung`, `DoAnUong`. Chúng mang tính chất đóng gói dữ liệu (Encapsulation) thông qua các field `private` và phương thức `Getter/Setter`.

### 3.1.2 Xác định các hệ thống con và giao diện (Identify subsystems and interfaces)
Dự án bao gồm 3 hệ thống con chính (Subsystems):
- **Core Session Subsystem:** Quản lý vòng đời hoạt động của Phiên và Máy tính.
- **Accounting Subsystem:** Hệ thống kế toán ẩn xử lý tính giờ, tính điểm thưởng và nạp tiền.
- **Service Subsystem:** Hệ thống phụ chịu trách nhiệm về menu đồ ăn thức uống.

### 3.1.3 Xác định các gói (Identify packages)
Hệ thống tuân thủ thiết kế phân tầng tiêu chuẩn trong Java bằng 4 package:
- `entity`: Chứa POJO.
- `dao`: Chứa các bộ truy cập DB.
- `controller`: Chứa logic điều hướng.
- `view`: Chứa màn hình.

---

## 3.2 Thiết kế trường hợp sử dụng (Use-case design)

### 3.2.1 Thiết kế các biểu đồ tuần tự (Design sequence diagrams)
(Các biểu đồ tuần tự chi tiết về sự luân chuyển dữ liệu ở cấp độ Class và Database đã được phân tích và vẽ đầy đủ ở **Mục 2.2.1** - Biểu đồ Bắt đầu Phiên và Nạp tiền).

### 3.2.2 Thiết kế biểu đồ lớp (Class diagrams)
Sơ đồ mô tả cấu trúc của các lớp thực thể trọng yếu nhất cấu thành nên phần mềm quản lý, cũng như mối quan hệ nhân quả giữa chúng.

```mermaid
classDiagram
    class MayTinh {
        -int ma
        -String ten
        -double giaMoiGio
        -String trangThai
        +getGiaMoiGio() double
        +setTrangThai(String) void
    }

    class KhachHang {
        -int ma
        -String ten
        -double soDu
        -int diem
        +truTien(double) boolean
        +congDiem(int) void
    }

    class PhienSuDung {
        -int ma
        -int maMayTinh
        -Integer maKhachHang
        -Date gioBatDau
        -double tongTien
        +tinhTien(double giaMay) double
        +ketThucPhien() void
    }

    class DoAnUong {
        -int ma
        -String ten
        -double gia
        -int diemDoi
    }

    KhachHang "1" -- "0..*" PhienSuDung : Thực hiện
    MayTinh "1" -- "0..*" PhienSuDung : Diễn ra tại
    PhienSuDung "1" -- "0..*" DoAnUong : Gọi dịch vụ
```

---

## 3.3 Thiết kế cơ sở dữ liệu (Database design)

### 3.3.1 Lược đồ cơ sở dữ liệu
Sơ đồ ERD (Entity-Relationship Diagram) của hệ thống Database H2. Mối quan hệ giữa bảng gốc và bảng phát sinh.

```mermaid
erDiagram
    KHACH_HANG {
        int ma PK
        varchar(100) ten
        varchar(20) sdt
        double so_du
        int diem
    }
    MAY_TINH {
        int ma PK
        varchar(50) ten
        double gia_moi_gio
        varchar(20) trang_thai
    }
    PHIEN_SU_DUNG {
        int ma PK
        int ma_may_tinh FK
        int ma_khach_hang FK
        datetime gio_bat_dau
        datetime gio_ket_thuc
    }
    DO_AN_UONG {
        int ma PK
        varchar(100) ten
        double gia
        int diem_doi
    }
    DON_HANG {
        int ma PK
        int ma_phien FK
        double tong_tien
    }

    KHACH_HANG ||--o{ PHIEN_SU_DUNG : "Sở hữu"
    MAY_TINH ||--o{ PHIEN_SU_DUNG : "Lưu vết"
    PHIEN_SU_DUNG ||--o{ DON_HANG : "Chứa"
```

### 3.3.2 Chi tiết các bảng
**Ràng buộc toàn vẹn Dữ liệu được thiết kế cứng trong Database:**
- Bảng **KHACH_HANG**: Cột `sdt` (Số điện thoại) được gán là `UNIQUE` nhằm chống tạo tài khoản ảo lấy điểm thưởng.
- Bảng **MAY_TINH**: Cột `gia_moi_gio` cài đặt `NOT NULL`.
- Bảng **LICH_SU_DOI_THUONG**: Áp dụng Khóa ngoại kép tới `KhachHang` và `DoAnUong`, tạo ra 1 dòng ghi chú tài chính mỗi khi khách đổi điểm để quản lý dễ bề đối soát.
