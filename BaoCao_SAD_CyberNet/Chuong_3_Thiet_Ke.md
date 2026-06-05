# CHƯƠNG 3: THIẾT KẾ (USE-CASE DESIGN)

> **👤 PHÂN CÔNG THỰC HIỆN:**
> - **Thành viên 1 (Trưởng nhóm, Database/Backend):** Chịu trách nhiệm toàn bộ nội dung chương này. Triển khai các Biểu đồ Lớp (Class Diagram), ERD CSDL, Sơ đồ Trạng thái (State Diagram) và Sơ đồ Thành phần (Component Diagram).

---

## 3.1 Thiết kế Sơ đồ Lớp (Class Diagram)

Sơ đồ mô tả cấu trúc của các lớp thực thể trọng yếu nhất cấu thành nên phần mềm quản lý, cũng như mối quan hệ nhân quả giữa chúng.

```mermaid
classDiagram
    class MayTinh {
        -int ma
        -String ten
        -String loai
        -double giaMoiGio
        -String trangThai
        +getMa() int
        +getGiaMoiGio() double
        +setTrangThai(String) void
    }

    class KhachHang {
        -int ma
        -String ten
        -String sdt
        -double soDu
        -double tongGio
        -int diem
        +truTien(double) boolean
        +congDiem(int) void
    }

    class PhienSuDung {
        -int ma
        -int maMayTinh
        -Integer maKhachHang
        -Date gioBatDau
        -Date gioKetThuc
        -double tongTien
        -String trangThai
        +tinhTien(double giaMay) double
        +ketThucPhien() void
    }

    class DoAnUong {
        -int ma
        -String ten
        -double gia
        -String phanLoai
        -int diemDoi
        -boolean conHang
    }

    KhachHang "1" -- "0..*" PhienSuDung : Thực hiện
    MayTinh "1" -- "0..*" PhienSuDung : Lưu vết qua
    PhienSuDung "1" -- "0..*" DoAnUong : Gọi dịch vụ
```

---

## 3.2 Sơ đồ Trạng thái (State Machine Diagram)

Trong phần mềm quản lý phòng máy, thực thể `Máy Tính` sở hữu chu trình sống (lifecycle) trạng thái cực kỳ nghiêm ngặt nhằm tránh việc trùng lặp phiên (hai người ngồi một máy).

```mermaid
stateDiagram-v2
    [*] --> TRONG : Máy vừa cài đặt / Khởi động phần mềm
    
    TRONG --> DANG_DUNG : Nhân viên Mở máy (Bắt đầu phiên)
    DANG_DUNG --> DANG_DUNG : Thêm dịch vụ đồ ăn vào máy
    
    DANG_DUNG --> TRONG : Nhân viên Thanh toán & Đóng máy
    
    TRONG --> BAO_TRI : Phát hiện hỏng hóc
    BAO_TRI --> TRONG : Sửa xong
```

---

## 3.3 Thiết kế Cơ sở dữ liệu (Database Design)

### 3.3.1 Sơ đồ Thực thể - Mối quan hệ (Entity-Relationship Diagram)

Sơ đồ ERD của toàn bộ hệ thống Database H2. Mối quan hệ giữa bảng gốc (KHACH_HANG, MAY_TINH, DO_AN_UONG) và bảng phát sinh (PHIEN_SU_DUNG, DON_HANG, LICH_SU_DOI_THUONG).

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

    KHACH_HANG ||--o{ PHIEN_SU_DUNG : "Có"
    MAY_TINH ||--o{ PHIEN_SU_DUNG : "Tổ chức"
    PHIEN_SU_DUNG ||--o{ DON_HANG : "Chứa"
```

---

## 3.4 Sơ đồ Thành phần (Component Diagram)

Kiến trúc gói mã nguồn và sự giao tiếp giữa các thành phần nội bộ trong ứng dụng Java.

```mermaid
flowchart TD
    subgraph UI_Layer [Tầng Giao Diện - View Component]
        UI_Login[GiaoDienDangNhap]
        UI_Main[GiaoDienChinh]
        UI_Modules[Các Panel Chức Năng]
    end

    subgraph Controller_Layer [Tầng Xử Lý - Controller Component]
        Ctrl_Auth[Auth Controller]
        Ctrl_Logic[Logic Controller]
    end

    subgraph Data_Layer [Tầng Truy cập dữ liệu - DAO Component]
        DAO_Connection[KetNoiCSDL (Singleton)]
        DAO_Classes[KhachHangDAO, MayTinhDAO...]
    end

    DB[(H2 Embedded Database)]

    UI_Login --> Ctrl_Auth
    UI_Main --> UI_Modules
    UI_Modules --> Ctrl_Logic
    
    Ctrl_Auth --> DAO_Classes
    Ctrl_Logic --> DAO_Classes
    
    DAO_Classes --> DAO_Connection
    DAO_Connection --> DB
```
