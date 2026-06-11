<div align="center">

# ĐẠI HỌC PHENIKAA
## TRƯỜNG CÔNG NGHỆ THÔNG TIN PHENIKAA

---

<br>

### PHÂN TÍCH VÀ THIẾT KẾ HỆ THỐNG
# HỆ THỐNG QUẢN LÝ QUÁN INTERNET — CYBERNET

<br>

**Giảng viên hướng dẫn:** *(Ghi rõ học hàm, học vị, họ và tên GVHD)*

**Sinh viên thực hiện:**

| Họ và tên | Mã sinh viên |
|-----------|-------------|
| Thành viên 1 (Nhóm trưởng) | *(MSV)* |
| Thành viên 2 | *(MSV)* |
| Thành viên 3 | *(MSV)* |
| Thành viên 4 | *(MSV)* |

**Khóa:** K__ - 20__ - 20__

**Lớp tín chỉ:** *(Điền lớp)*

**Chương trình đào tạo:** Công nghệ Thông tin

<br>

**Hà Nội, tháng __ năm 2025**

</div>

---

<div style="page-break-after: always;"></div>

# PHÂN CÔNG NHIỆM VỤ ĐỒ ÁN

## Danh sách các công việc/nhiệm vụ

| STT | Mô tả tóm tắt công việc |
|-----|--------------------------|
| CV1 | Viết Chương 1 — Tổng quan đề tài, tổng hợp & chỉnh sửa báo cáo, phần kết luận |
| CV2 | Viết Chương 2 — Cơ sở lý thuyết, môi trường triển khai & đánh giá hệ thống |
| CV3 | Viết Chương 3 — Phân tích hệ thống (Use Case, Activity, Sequence, Class Diagram) |
| CV4 | Viết Chương 4 — Thiết kế hệ thống (Kiến trúc, CSDL, giao diện) & Cài đặt chức năng |

## Bảng phân công chi tiết

| TT | Mã sinh viên | Họ và tên | Nhiệm vụ phân công | Nội dung đã thực hiện | Đánh giá |
|----|-------------|-----------|---------------------|----------------------|----------|
| 1 | *(MSV)* | Thành viên 1 | CV1 | Chương 1, Chương 4 (4.4–4.6), Kết luận, Tổng hợp | 28% |
| 2 | *(MSV)* | Thành viên 2 | CV2 | Chương 2, Chương 5 (5.1, 5.3, 5.4) | 25% |
| 3 | *(MSV)* | Thành viên 3 | CV3 | Chương 3 (3.1–3.5) | 25% |
| 4 | *(MSV)* | Thành viên 4 | CV4 | Chương 4 (4.1–4.3), Chương 5 (5.2) | 22% |

---

<div style="page-break-after: always;"></div>

# MỤC LỤC

- [CHƯƠNG 1. TỔNG QUAN ĐỀ TÀI](#chương-1-tổng-quan-đề-tài)
  - [1.1. Lý do chọn đề tài](#11-lý-do-chọn-đề-tài)
  - [1.2. Mục tiêu và phạm vi nghiên cứu](#12-mục-tiêu-và-phạm-vi-nghiên-cứu)
  - [1.3. Đối tượng và phương pháp nghiên cứu](#13-đối-tượng-và-phương-pháp-nghiên-cứu)
  - [1.4. Ý nghĩa khoa học và thực tiễn](#14-ý-nghĩa-khoa-học-và-thực-tiễn)
  - [1.5. Bố cục đồ án](#15-bố-cục-đồ-án)
- [CHƯƠNG 2. CƠ SỞ LÝ THUYẾT](#chương-2-cơ-sở-lý-thuyết)
  - [2.1. Tổng quan lĩnh vực](#21-tổng-quan-lĩnh-vực)
  - [2.2. Các khái niệm và mô hình liên quan](#22-các-khái-niệm-và-mô-hình-liên-quan)
  - [2.3. Các công trình, nghiên cứu liên quan](#23-các-công-trình-nghiên-cứu-liên-quan)
  - [2.4. Công nghệ, ngôn ngữ và công cụ sử dụng](#24-công-nghệ-ngôn-ngữ-và-công-cụ-sử-dụng)
- [CHƯƠNG 3. PHÂN TÍCH HỆ THỐNG](#chương-3-phân-tích-hệ-thống)
  - [3.1. Khảo sát hiện trạng](#31-khảo-sát-hiện-trạng)
  - [3.2. Phân tích yêu cầu](#32-phân-tích-yêu-cầu)
  - [3.3. Mô hình ca sử dụng (Use Case Model)](#33-mô-hình-ca-sử-dụng-use-case-model)
  - [3.4. Phân tích hành vi và cấu trúc hệ thống](#34-phân-tích-hành-vi-và-cấu-trúc-hệ-thống)
  - [3.5. Kết luận chương](#35-kết-luận-chương)
- [CHƯƠNG 4. THIẾT KẾ HỆ THỐNG](#chương-4-thiết-kế-hệ-thống)
  - [4.1. Kiến trúc tổng thể](#41-kiến-trúc-tổng-thể)
  - [4.2. Thiết kế cơ sở dữ liệu](#42-thiết-kế-cơ-sở-dữ-liệu)
  - [4.3. Thiết kế cấu trúc phần mềm](#43-thiết-kế-cấu-trúc-phần-mềm)
  - [4.4. Thiết kế giao diện người dùng](#44-thiết-kế-giao-diện-người-dùng)
  - [4.5. Thiết kế hành vi hệ thống](#45-thiết-kế-hành-vi-hệ-thống)
  - [4.6. Kết luận chương](#46-kết-luận-chương)
- [CHƯƠNG 5. CÀI ĐẶT VÀ THỬ NGHIỆM](#chương-5-cài-đặt-và-thử-nghiệm)
  - [5.1. Môi trường triển khai](#51-môi-trường-triển-khai)
  - [5.2. Cài đặt các chức năng chính](#52-cài-đặt-các-chức-năng-chính)
  - [5.3. Kết quả thực nghiệm](#53-kết-quả-thực-nghiệm)
  - [5.4. Đánh giá hệ thống](#54-đánh-giá-hệ-thống)
- [KẾT LUẬN VÀ HƯỚNG PHÁT TRIỂN](#kết-luận-và-hướng-phát-triển)

---

<div style="page-break-after: always;"></div>

# CHƯƠNG 1. TỔNG QUAN ĐỀ TÀI

> 📌 **Người thực hiện: Thành viên 1 (Nhóm trưởng)**

## 1.1. Lý do chọn đề tài

Trong bối cảnh ngành kinh doanh dịch vụ internet (quán net/cyber game) ngày càng phát triển tại Việt Nam, việc quản lý quán internet trở thành một bài toán thiết thực. Hiện nay, nhiều quán internet nhỏ và vừa vẫn quản lý thủ công bằng sổ sách hoặc sử dụng các phần mềm đắt đỏ, phức tạp, không phù hợp với quy mô kinh doanh.

Các vấn đề thực tế tồn tại:

- **Quản lý máy tính:** Khó theo dõi trạng thái máy (trống, đang dùng, bảo trì) khi số lượng máy lớn (20+ máy).
- **Tính tiền thủ công:** Dễ sai sót khi tính tiền theo giờ, đặc biệt với các mức giá khác nhau giữa máy Thường và VIP.
- **Quản lý khách hàng:** Không có hệ thống lưu trữ thông tin, số dư, tổng giờ chơi và chương trình tích điểm khách hàng.
- **Đặt đồ ăn/uống:** Ghi chép thủ công dễ nhầm lẫn, khó thống kê doanh thu dịch vụ.
- **Thống kê doanh thu:** Không có công cụ tổng hợp doanh thu theo ngày, tuần, tháng.

Xuất phát từ những vấn đề trên, nhóm quyết định xây dựng **"Hệ thống Quản Lý Quán Internet — CyberNet"** nhằm giải quyết trọn vẹn các nghiệp vụ quản lý quán internet bằng một ứng dụng desktop Java có giao diện hiện đại, dễ sử dụng.

## 1.2. Mục tiêu và phạm vi nghiên cứu

### Mục tiêu

- **Mục tiêu 1:** Xây dựng ứng dụng desktop quản lý quán internet hoàn chỉnh bằng Java Swing với giao diện Dark Mode hiện đại (FlatLaf).
- **Mục tiêu 2:** Quản lý 20 máy tính (12 máy Thường + 8 máy VIP) với các trạng thái: Trống, Đang dùng, Bảo trì.
- **Mục tiêu 3:** Quản lý khách hàng với hệ thống nạp tiền, tích điểm và đổi thưởng.
- **Mục tiêu 4:** Quản lý phiên sử dụng máy: bắt đầu, tính tiền tự động theo giờ, kết thúc phiên.
- **Mục tiêu 5:** Quản lý đặt đồ ăn/uống theo phiên sử dụng.
- **Mục tiêu 6:** Thống kê doanh thu theo khoảng thời gian với biểu đồ cột trực quan.

### Phạm vi

- **Đối tượng sử dụng:** Nhân viên quản lý quán internet (admin/staff).
- **Phạm vi chức năng:** Đăng nhập, quản lý máy tính, quản lý khách hàng, quản lý phiên sử dụng, đặt đồ ăn/uống, thống kê doanh thu.
- **Phạm vi triển khai:** Ứng dụng desktop chạy trên hệ điều hành Windows/macOS/Linux, kết nối cơ sở dữ liệu H2 (embedded).

## 1.3. Đối tượng và phương pháp nghiên cứu

### Đối tượng nghiên cứu

- Quy trình quản lý quán internet: tiếp nhận khách, bật/tắt máy, tính tiền, gọi đồ ăn/uống.
- Người dùng hệ thống: nhân viên quản lý quán (Admin).
- Các hệ thống quản lý quán internet hiện có trên thị trường.

### Phương pháp nghiên cứu

- **Khảo sát thực tế:** Tìm hiểu quy trình vận hành tại các quán internet thực tế.
- **Phân tích UML:** Sử dụng biểu đồ Use Case, Activity, Sequence, Class Diagram để mô hình hóa hệ thống.
- **Lập trình hướng đối tượng (OOP):** Áp dụng mô hình MVC (Model-View-Controller) với Java.
- **Thử nghiệm:** Kiểm thử chức năng trên dữ liệu mẫu (20 máy tính, 5 khách hàng, 15 món ăn/uống).

## 1.4. Ý nghĩa khoa học và thực tiễn

### Ý nghĩa khoa học

- Áp dụng kiến thức về phân tích thiết kế hệ thống (UML), lập trình hướng đối tượng (Java), và cơ sở dữ liệu quan hệ (SQL).
- Thực hành triển khai mô hình kiến trúc MVC trong ứng dụng desktop thực tế.
- Nghiên cứu cách sử dụng Design Pattern: Singleton (quản lý kết nối CSDL), DAO Pattern (truy xuất dữ liệu).

### Ý nghĩa thực tiễn

- Cung cấp giải pháp phần mềm miễn phí, dễ triển khai cho các quán internet nhỏ và vừa.
- Giảm thiểu sai sót trong tính tiền, quản lý khách hàng.
- Tăng hiệu quả quản lý với giao diện trực quan, hiện đại (Dark Mode).
- Hỗ trợ quyết định kinh doanh qua module thống kê doanh thu.

## 1.5. Bố cục đồ án

Đồ án được tổ chức thành 5 chương:

- **Chương 1 – Tổng quan đề tài:** Trình bày lý do chọn đề tài, mục tiêu, phạm vi, đối tượng và phương pháp nghiên cứu.
- **Chương 2 – Cơ sở lý thuyết:** Trình bày các kiến thức nền tảng về UML, cơ sở dữ liệu, kiến trúc phần mềm và công nghệ sử dụng (Java, H2 Database, FlatLaf).
- **Chương 3 – Phân tích hệ thống:** Khảo sát hiện trạng, phân tích yêu cầu chức năng/phi chức năng, mô hình hóa Use Case và các biểu đồ UML.
- **Chương 4 – Thiết kế hệ thống:** Thiết kế kiến trúc MVC, cơ sở dữ liệu (7 bảng), giao diện người dùng (6 panel chính) và hành vi hệ thống.
- **Chương 5 – Cài đặt và thử nghiệm:** Triển khai, cài đặt chức năng và đánh giá kết quả.

---

<div style="page-break-after: always;"></div>

# CHƯƠNG 2. CƠ SỞ LÝ THUYẾT

> 📌 **Người thực hiện: Thành viên 2**

## 2.1. Tổng quan lĩnh vực

Hệ thống quản lý quán internet thuộc lĩnh vực **Hệ thống thông tin quản lý (Management Information System - MIS)**, cụ thể là ứng dụng desktop phục vụ quản lý nghiệp vụ kinh doanh dịch vụ internet.

Ngành kinh doanh quán internet/cyber game tại Việt Nam hiện nay có đặc điểm:

- Quy mô trung bình 20–100 máy tính/quán.
- Phân loại máy theo cấu hình: máy Thường (giá thấp) và máy VIP (cấu hình cao, giá cao hơn).
- Dịch vụ kèm theo: đồ ăn, nước uống.
- Tính tiền theo giờ sử dụng.
- Khách hàng có thể là khách vãng lai hoặc khách thành viên (có tài khoản, nạp tiền trước).

## 2.2. Các khái niệm và mô hình liên quan

### UML (Unified Modeling Language)

UML là ngôn ngữ mô hình hóa chuẩn trong công nghệ phần mềm, dùng để đặc tả, trực quan hóa và lập tài liệu cho hệ thống. Trong đồ án này, nhóm sử dụng các biểu đồ UML sau:

- **Use Case Diagram:** Mô hình hóa các chức năng và tác nhân tham gia.
- **Activity Diagram:** Mô tả luồng hoạt động của nghiệp vụ.
- **Sequence Diagram:** Mô tả trình tự tương tác giữa các đối tượng.
- **Class Diagram:** Mô tả cấu trúc lớp và quan hệ giữa các lớp.

### Cơ sở dữ liệu quan hệ

Cơ sở dữ liệu quan hệ (Relational Database) tổ chức dữ liệu thành các bảng (table) có quan hệ với nhau thông qua khóa chính (Primary Key) và khóa ngoại (Foreign Key). Hệ thống CyberNet sử dụng 7 bảng dữ liệu chính với các mối quan hệ 1-nhiều.

### Mô hình kiến trúc MVC (Model-View-Controller)

MVC là mô hình kiến trúc phần mềm tách biệt ứng dụng thành 3 tầng:

```
┌──────────────────────────────────┐
│     VIEW (Giao diện - JFrame)    │  ← Hiển thị dữ liệu, nhận tương tác
├──────────────────────────────────┤
│   CONTROLLER (Điều khiển)        │  ← Xử lý logic nghiệp vụ
├──────────────────────────────────┤
│     MODEL (Dữ liệu - Entity)    │  ← Đại diện đối tượng dữ liệu
├──────────────────────────────────┤
│     DAO (Data Access Object)     │  ← Truy xuất cơ sở dữ liệu
└──────────────────────────────────┘
```

- **Model (Entity):** Các lớp `MayTinh`, `KhachHang`, `PhienSuDung`, `DonHang`, `DoAnUong`, `ChiTietDonHang`, `NguoiDung` — đại diện cho các thực thể trong CSDL.
- **View:** Các class giao diện `GiaoDienDangNhap`, `GiaoDienChinh`, `PanelMayTinh`, `PanelKhachHang`, v.v.
- **Controller:** `DieuKhienDangNhap` — xử lý logic đăng nhập.
- **DAO (Data Access Object):** `MayTinhDAO`, `KhachHangDAO`, `PhienSuDungDAO`, v.v. — thực hiện truy vấn SQL.

### Design Pattern sử dụng

- **Singleton Pattern:** Áp dụng trong `KetNoiCSDL` để đảm bảo chỉ có duy nhất một kết nối CSDL trong toàn bộ ứng dụng.
- **DAO Pattern:** Tách biệt logic truy xuất dữ liệu khỏi logic nghiệp vụ, giúp dễ bảo trì và mở rộng.

## 2.3. Các công trình, nghiên cứu liên quan

| STT | Tên hệ thống | Ưu điểm | Nhược điểm | So sánh với CyberNet |
|-----|-------------|---------|------------|----------------------|
| 1 | CSM (Cyber Station Management) | Đầy đủ tính năng, chuyên nghiệp | Bản quyền đắt, giao diện phức tạp | CyberNet miễn phí, giao diện đơn giản |
| 2 | GCafe | Phổ biến tại Việt Nam, nhiều tính năng | Cần cài đặt server-client, nặng | CyberNet chạy standalone, nhẹ |
| 3 | TrueNet | Giao diện đẹp, quản lý từ xa | Cần kết nối mạng, phí hàng tháng | CyberNet hoạt động offline |

## 2.4. Công nghệ, ngôn ngữ và công cụ sử dụng

| STT | Công nghệ/Công cụ | Phiên bản | Mục đích sử dụng |
|-----|-------------------|-----------|-------------------|
| 1 | Java SE | 17 (LTS) | Ngôn ngữ lập trình chính |
| 2 | Java Swing | JDK 17 | Xây dựng giao diện đồ họa (GUI) |
| 3 | FlatLaf | 3.4 | Look and Feel hiện đại (Dark Mode) |
| 4 | H2 Database | 2.2.224 | CSDL nhúng (embedded), tương thích MySQL |
| 5 | JCalendar | 1.4 | Component chọn ngày cho thống kê |
| 6 | Apache Maven | 3.x | Quản lý dự án và dependency |
| 7 | Maven Shade Plugin | 3.4.1 | Đóng gói Fat-JAR (tất cả vào 1 file) |
| 8 | Git/GitHub | - | Quản lý mã nguồn, phiên bản |
| 9 | IDE (VS Code / IntelliJ) | - | Môi trường phát triển |

---

<div style="page-break-after: always;"></div>

# CHƯƠNG 3. PHÂN TÍCH HỆ THỐNG

> 📌 **Người thực hiện: Thành viên 3**

## 3.1. Khảo sát hiện trạng

### Quy trình nghiệp vụ hiện tại

Tại các quán internet nhỏ và vừa, quy trình vận hành thường diễn ra như sau:

1. Khách hàng đến quán → Nhân viên ghi tên vào sổ → Bật máy thủ công.
2. Khách chơi xong → Nhân viên ghi thời gian → Tính tiền bằng máy tính/sổ sách.
3. Khách gọi đồ ăn/uống → Nhân viên ghi giấy → Mang đến máy → Tính tiền khi kết thúc.
4. Cuối ngày, nhân viên tổng hợp doanh thu thủ công bằng sổ sách.

### Các vấn đề, hạn chế

- **Sai sót tính tiền:** Tính nhầm giờ, nhầm giá khi tính thủ công, đặc biệt khi quán đông.
- **Khó theo dõi trạng thái máy:** Không biết máy nào đang trống, máy nào đang dùng khi có 20+ máy.
- **Không quản lý khách hàng:** Không lưu thông tin, không có chương trình ưu đãi/tích điểm.
- **Ghi chép đồ ăn/uống rời rạc:** Dễ thất thoát, không gắn với phiên sử dụng.
- **Thống kê doanh thu chậm:** Phải cộng dồn thủ công, dễ sai.
- **Không phân biệt máy VIP/Thường:** Giá cả không linh hoạt.

## 3.2. Phân tích yêu cầu

### Yêu cầu nghiệp vụ

- Hệ thống phải hỗ trợ quản lý tối thiểu 20 máy tính với 2 loại: Thường (10.000đ/giờ) và VIP (15.000đ/giờ).
- Khách hàng có thể là khách vãng lai (không cần đăng ký) hoặc khách thành viên (có tài khoản, nạp tiền).
- Tiền được tính tự động theo thời gian sử dụng thực tế (làm tròn lên 0.1 giờ).
- Khách thành viên được tích điểm theo giờ chơi (1 giờ = 1 điểm) và có thể đổi điểm lấy đồ ăn/uống.
- Đồ ăn/uống được gắn với phiên sử dụng của máy tính.

### Yêu cầu chức năng

| STT | Yêu cầu chức năng | Mô tả | Độ ưu tiên |
|-----|-------------------|-------|-------------|
| YC01 | Đăng nhập | Nhân viên đăng nhập bằng tài khoản admin | Cao |
| YC02 | Quản lý máy tính | Xem trạng thái, thêm/sửa/xóa máy, phân loại Thường/VIP | Cao |
| YC03 | Bắt đầu phiên | Chọn máy trống → chọn khách hàng hoặc nhập tên → bắt đầu | Cao |
| YC04 | Kết thúc phiên | Tính tiền tự động, cập nhật trạng thái máy về Trống | Cao |
| YC05 | Gọi đồ ăn/uống | Chọn món từ menu, chọn số lượng, tạo đơn hàng gắn với phiên | Cao |
| YC06 | Quản lý khách hàng | Thêm/sửa/xóa/tìm kiếm khách hàng | Cao |
| YC07 | Nạp tiền | Nạp tiền cho khách thành viên, tự động cộng giờ chơi và điểm | Trung bình |
| YC08 | Đổi thưởng | Khách dùng điểm tích lũy đổi đồ ăn/uống miễn phí | Trung bình |
| YC09 | Dashboard tổng quan | Hiển thị: số máy trống, đang dùng, doanh thu máy, doanh thu dịch vụ | Cao |
| YC10 | Thống kê doanh thu | Thống kê doanh thu theo khoảng ngày, biểu đồ cột, bảng chi tiết | Trung bình |
| YC11 | Lọc máy theo trạng thái | Lọc hiển thị: Tất cả, Trống, Đang dùng, Bảo trì | Thấp |
| YC12 | Quản lý menu đồ ăn/uống | Xem danh sách, thêm/sửa/xóa món, phân loại | Trung bình |

### Yêu cầu phi chức năng

| STT | Yêu cầu phi chức năng | Mô tả |
|-----|----------------------|-------|
| 1 | Hiệu năng | Ứng dụng khởi động nhanh (< 3 giây), tương tác mượt mà |
| 2 | Giao diện | Dark Mode hiện đại với FlatLaf, hỗ trợ emoji icon |
| 3 | Dễ sử dụng | Giao diện trực quan, sidebar điều hướng rõ ràng |
| 4 | Đa nền tảng | Chạy trên Windows/macOS/Linux (Java cross-platform) |
| 5 | Triển khai đơn giản | Đóng gói thành 1 file JAR duy nhất (Fat-JAR), không cần cài đặt server |
| 6 | CSDL nhúng | Sử dụng H2 Database embedded, không cần cài MySQL riêng |

## 3.3. Mô hình ca sử dụng (Use Case Model)

### 3.3.1. Danh sách Use Case
Hệ thống CyberNet bao gồm 13 ca sử dụng (Use Case) chính dành cho tác nhân Quản lý (Admin):

| STT | Mã Use Case | Tên Use Case | Tác nhân chính | Tác nhân phụ | Mô tả |
|-----|-------------|--------------|----------------|--------------|-------|
| 1 | UC01 | Đăng nhập | Admin | CSDL H2 | Xác thực tài khoản Admin để truy cập hệ thống. |
| 2 | UC02 | Xem Dashboard | Admin | Không | Giám sát các chỉ số vận hành và doanh thu realtime. |
| 3 | UC03 | Quản lý máy tính | Admin | Không | Xem danh sách máy dạng lưới, thêm máy mới vào hệ thống. |
| 4 | UC04 | Bắt đầu phiên | Admin | Không | Kích hoạt phiên chơi mới tại máy trống cho khách hàng. |
| 5 | UC05 | Kết thúc phiên | Admin | Không | Tính hóa đơn, trừ tiền tài khoản thành viên, giải phóng máy. |
| 6 | UC06 | Gọi đồ ăn/uống | Admin | Không | Gọi thêm món ăn, đồ uống gắn liền với phiên chơi. |
| 7 | UC07 | Quản lý khách hàng | Admin | Không | Quản lý thông tin tài khoản thành viên (CRUD). |
| 8 | UC08 | Nạp tiền | Admin | Không | Nạp tiền vào tài khoản hội viên và cộng điểm, giờ chơi. |
| 9 | UC09 | Đổi thưởng | Admin | Không | Khách dùng điểm tích lũy đổi đồ ăn/nước uống miễn phí. |
| 10 | UC10 | Quản lý menu dịch vụ| Admin | Không | CRUD thực đơn dịch vụ ăn uống của quán. |
| 11 | UC11 | Thống kê doanh thu | Admin | Không | Xem báo cáo tài chính và biểu đồ doanh thu theo ngày. |
| 12 | UC12 | Đặt máy bảo trì | Admin | Không | Chuyển máy sang trạng thái bảo trì khi hỏng hóc. |
| 13 | UC13 | Đăng xuất | Admin | Không | Thoát khỏi hệ thống và đóng kết nối CSDL an toàn. |

### 3.3.2. Biểu đồ Use Case tổng quát

![Biểu đồ Use Case tổng quát](http://www.plantuml.com/plantuml/png/VLHFYzGm7BttK_oO-wxJpdJ-b6LONK51M10Ll6RQE0dJfgLDTGVn81uyYE0YddMGHLXSz2OqY8SCVezy4zFEPjQ4LUZbzRsNlFnUsiEAevBNMUgb-0a7daD95uH3GaiSSvephrfILg0IPL2WU8aM-05VfHXQ6h55YlyIe9ZdvPP6pt2LPzZhNi7D9AFCytOUwDrLuZLmeiGfMy3HQexBOynxyDm3g2iSeqfh7Xtr_GWsxpQlj8OHrLmKFK0LjEyTNH31Ovp1RLIHUOxAnEW2HpU8u7wjciyCKlaJChbU0PSNZ1ZzmD4F8xYbccySDgUg-L93GQWyPqOzTDHX1FTKyqkFaSWcTiIX8nv5S4U9jxHTMhm7VSWRTJSEOnWvXh6LVKdaEYP0vAShnzXnJ28uLisw04wLUBbBCd5aqtR8IdoWElVlxqfyl5fnQalzlZq_p6f8b7YZWpnLukpIvVSTbsxnuMNLItaEIOuOqNlLHk-qwGTTeEO7tpOqrnlcm4lvrHYSMlt1xfgSw7HdtEZQElNJLWNx-xEkOOZWu82oEAqJF9jjsT2mm-lOaM73ZiKd7BF4a1D3Zg-pJl_7-i4_5dxXUTqN0tjxi-rjjs1Wmu4DXpOCRJYouTY64njEBQXhjQ2TohTJwT4VwiFe7yiV)

### 3.3.3. Biểu đồ Use Case chi tiết

#### Phân hệ Máy tính & Phiên sử dụng
Biểu đồ mô tả chi tiết các ca sử dụng liên quan đến quản lý trạng thái máy và vòng đời một phiên chơi của khách hàng tại phòng máy.

![Phân hệ Máy tính & Phiên sử dụng](http://www.plantuml.com/plantuml/png/VPB1IiCm6CVlVOe_EtZRTBFjeOmnzU11X8du0B6DRLYRZZP5XtZou4bm-0Gg8eZ3ez0Y7heNoPkOjjkqOmgv_FFBBr--9FrOa4WaWQ_vz4n0X8YOwmauBAAsO27NuY7Z8nAH02DY3ubBZyNOfoWmuQvFVoqWjWYZ2fDp6eS1rSefx3W1uvgsTD0OUFaZXoUp6npc3sE8VCezR63WiVoL8vRP58xCdhdRmAK6936rIQp4axsjRGj7YKoVEFpy4y5IRu34AFZAUjt2hapV1MOJcRua6AsfKPdwYcbOE93fbxeNBq_jVqHZHJGjxClibXKbitlChlbc8hF9Zs3MXNR7mknEfX-YwkTKjHT2HFdRN6XtbA16SKYqMhsoJrZeTXct_SIXlLv5ZGNLrr5pGOsIqWj1kJE7xSuVy4hJoWT4irdLhKUz7erwD6jHjT5Ncwh_zWq)

#### Phân hệ Khách hàng & Thành viên
Biểu đồ mô tả chi tiết các tương tác quản lý thông tin khách hàng, luồng tiền nạp và đổi điểm lấy dịch vụ miễn phí.

![Phân hệ Khách hàng & Thành viên](http://www.plantuml.com/plantuml/png/TP4nIyGm5CVtV8e_7TpkSBCjHpbn54HHFq1iOnEQfgL95H47SN3LmLbFDoTThzpKm--HRsBQejmT2e7avVT-ZyTxKsrePUfS4idE3Ko1IgJS814LYuqe5D6PK2MjQ8wInXbDsR6vaWmTfYgLR2M1ngQe1amlcIvoHlelx2QvK8JyEl0EUVkgm6rpZttUpc8Ethwe55iuwHuS5w9zLnwk2L1h5bFjfDEzxPqGHxMTlod8Te5iHVL0DRgC3SCFSM3din96sEPM_Udu6quGOlbWcoS1mxy-RFFiEZkpyqztBHzTcRlynmoSwKvN0-Dnr3U049E9KB6i4nP50mt-epU4zDF1Q3JmzT1V3mCoPIfnw_e6)

#### Phân hệ Quản trị & Hệ thống
Biểu đồ thể hiện các tác vụ của Admin liên quan tới bảo mật, cấu hình thực đơn dịch vụ và báo cáo tài chính của quán game.

![Phân hệ Quản trị & Hệ thống](http://www.plantuml.com/plantuml/png/NP4zJiGm44Pxd-BJ5dGh9Tak1Le41IK8a6Y7nCHHOYUo7Nw4g2WeQE0Ae6sGkC16LDcBv2RhRCYAf9oPztcUxQMnf6qbSvRpMmjRGAU9i8XJpIER5egPB5KbQP8eASeeuHVsCUVengIIdFy3ABA5xiTqpqqXETkrS1JBL36sps1s9ffF1T7MhpYlslMNWjLj_OO3d7OzwoOVAfdXYG6LuH4P5xewzlmGc_VDYqgWHBl-AMSWWwu_uO8GrrpYX8ouAKZ70nUCETyBX_rvyml9LONOUKG2Tssz-alvtYJbB2vxGsJDD-A2b73ErS1FRFp5tlh1BLlPWLkmPyPsZuFv_B2_nwWCHgNp69LZs9ssv2fsVxa5)

---

### 3.3.4. Đặc tả các Use Case chi tiết

#### UC01: Đăng nhập

![Sơ đồ ca sử dụng UC01](http://www.plantuml.com/plantuml/png/PL2xJiCm5Dtz5KUPQGKTgoegj0C3wb8XiI7ZcCHAx5JsZL25c1XOuGz0J0mChEd0qFw8_wIEgNWKwGxtdfUkpjWHjzJeYbNoca0rhCeBGgQi5AHgmrofp89Rhi45rNPEoqg2tqXNQyaYXEDCAxFRarE_UW0LVlLaSapcqxC4t65wmhf0RlBWtjCihwITIKfmou36IS5Tq9nF3e-6s3nkxeF65BvzNyIa3l-jcqKAj7vMA8lQjw_c03fW54xVlZNVhii_lelrYuW_V0YKllqacF2F-davMWR14gRRqU-dknmCCHef8wecasdwZn_iyNSiDeHUxql0ehoxGYrZQRBG_nO)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Đăng nhập |
| **Mô tả** | Quản trị viên đăng nhập vào hệ thống để bắt đầu phiên làm việc. |
| **Actor chính** | Admin |
| **Actor phụ** | Hệ thống CSDL H2 |
| **Tiền điều kiện** | Ứng dụng đã khởi động thành công và hiển thị giao diện đăng nhập. |
| **Hậu điều kiện** | Admin đăng nhập hệ thống thành công, hệ thống chuyển sang giao diện quản trị chính. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Khởi động ứng dụng | |
| | 2. Hiển thị màn hình đăng nhập (GiaoDienDangNhap) |
| 3. Nhập tên đăng nhập và mật khẩu | |
| 4. Nhấn nút "ĐĂNG NHẬP" | |
| | 5. Kiểm tra thông tin tài khoản có khớp khớp với cấu hình hệ thống không |
| | 6. Gọi kết nối database để kiểm tra tính sẵn sàng của dữ liệu |
| | 7. Ẩn form đăng nhập và khởi chạy giao diện điều khiển chính (GiaoDienChinh) |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| | **Tại bước 5:** Nếu sai tài khoản hoặc mật khẩu → Hệ thống hiển thị hộp thoại cảnh báo: "Tên đăng nhập hoặc mật khẩu không chính xác!" |
| | **Tại bước 5.1:** Quay về bước 3 để nhập lại |
| | **Tại bước 6:** Nếu không kết nối được database nhúng H2 → Hệ thống hiển thị dialog thông báo lỗi kết nối CSDL và hướng dẫn khắc phục |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC01](http://www.plantuml.com/plantuml/png/PP8nIyD05CVt-nGFfnOaOXM4PB1gmK2b23gufasREsnUIdDrocG71nSxYPCjfM2b3eBJPN2uV97x9hwafJIut1r_tl_t_kznzLXwGpaA-omvyqE12Ii4PXzQqEGcVHUGJKpwXW6OT4cdRz851WvBN9C-WQII2GO9isiMkCAaOooqPmZr587h5N82P6Do8ZVgQ-1i6zb75hGA3QJ-n78PFEWfXFIIS4_2SkGm-tXTlq10hIJiPI_PO-iIMgvHg-kzVv543oftUjOfb6-wz5GGB39gZgTLKhi85RBC1z0dOvK1v9QcKRyIa202V4zwRI_kKYYjA9jRsnflEGt8eQD_CCYj0FQ91KPz8FIYOLYUPX_uxcuQNipRaJVqdS8OosX0ZswV6X6N9fJGDcecDjnpFOloYAyKVoVSnKtZgWn0N_JO6jFaUJ3WfV5sIR4dIXivA2zx0rqZwtJH5_a3)

---

#### UC02: Xem Dashboard tổng quan

![Sơ đồ ca sử dụng UC02](http://www.plantuml.com/plantuml/png/9Oun2i9054JxFSMGFY2M5XA9XPMD2BO_sM-ocDsLlpz846jhpvFJv2OcshwPDqoHb4HxtvcEhmgD4DUq2kk4QtKnc7HpuKv27bHhb9CE7OCUdA9dyqFOMU-2MGGApPncXsbyGzjf_8G6vL2n75apF0tG9wufpPrpkLflS66FFQMsYYGs0oKitBpCRnDvlls3WeETNtu1)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Xem Dashboard tổng quan |
| **Mô tả** | Admin theo dõi các chỉ số hoạt động thực tế theo thời gian thực (realtime) của quán. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Admin đăng nhập thành công vào hệ thống. |
| **Hậu điều kiện** | Hiển thị các số liệu thống kê doanh thu và máy tính chính xác tại thời điểm xem. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Chọn menu "Dashboard" trên thanh sidebar điều hướng | |
| | 2. Thực hiện đếm số máy trống và số máy đang hoạt động từ bảng dữ liệu máy tính |
| | 3. Tính toán tổng doanh thu giờ chơi và doanh thu dịch vụ phát sinh trong ngày hôm nay |
| | 4. Lấy danh sách các phiên sử dụng máy tính đang chạy trong CSDL |
| | 5. Hiển thị 4 thẻ thông tin chỉ số: Máy Trống, Đang Sử Dụng, Doanh Thu Máy, Doanh Thu Dịch Vụ |
| | 6. Hiển thị bảng chi tiết các phiên đang chạy (Mã phiên, máy sử dụng, tên khách, thời gian bắt đầu, tiền tạm tính) |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC02](http://www.plantuml.com/plantuml/png/NP2_JiCm4CPtFyMfGyT8G658b_9dwC382wHnL5iYTXKxbI9bOq2eGg830riZb0MfBn0F3AvuZtkJdjq5M6pvk-ztTvzdncQLhSj7rbxpKggMHO6bPmdS2dIl2if2rJ2wounOwApY8x2L_r9W92yMMJLbxHpTCrWoRzMoPUbv0eSt7BvBC2H1wRk6a5Y5jT_zKW_RZ3IXSUWiFT1zgEMKfHS9FFYz4c2rxnHOTEzaupeBagWX5p-TZAqukaqkO8skfn6QN6hfTmtrc2HmZqFV00-KyHtPSZfX9MJS7jq-u3qnfpLoGMi4z1B6S9N0NA9xKZ7N1YRX7bmmkPTJWZ7SuF0PGGbMKc3xPyB_MCRg5PiLYjDd7m4)

---

#### UC03: Quản lý máy tính

![Sơ đồ ca sử dụng UC03](http://www.plantuml.com/plantuml/png/9Oun2i9054JxFSMGFY3OMKWaZPKWuW7Mv9iixlx8xWyInDhQ8uYr5p1OvIIvYKbitviP9WcYlTJEAaj7WLJmfYW5kV6KYQbOXPFXi_RQGMTI-Pqqbg0l52f7Qa9OvSwm6WkQYy56wxwzGyg-VN21j3cGtv14k2gW3fJfC6JswMo-mBRkFo-6xRvmtRE1T6yk8-Y0qQkRchOHnyi_I8ZpuUqF)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Quản lý máy tính |
| **Mô tả** | Xem danh sách máy tính dưới dạng sơ đồ lưới trực quan, lọc theo trạng thái và thêm máy tính mới. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Admin đăng nhập thành công và chọn phân hệ quản lý máy tính. |
| **Hậu điều kiện** | Dữ liệu máy tính được thêm hoặc lọc đúng theo yêu cầu. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Chọn menu "Máy Tính" trên sidebar | |
| | 2. Lấy toàn bộ danh sách máy tính từ CSDL |
| | 3. Hiển thị lưới máy tính (card UI) với màu sắc hiển thị trạng thái (Xanh: Trống, Đỏ: Đang dùng, Vàng: Bảo trì) |
| 4. Nhấn nút "+ Thêm Máy" | |
| | 5. Hiển thị form thêm máy tính (Tên máy, Loại máy Thường/VIP, cấu hình) |
| 6. Nhập thông tin máy mới và nhấn "Lưu" | |
| | 7. Kiểm tra dữ liệu hợp lệ, thêm máy mới vào CSDL với trạng thái mặc định là "Trống" |
| | 8. Làm mới lưới hiển thị máy tính |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| **Tại bước 3a:** Chọn lọc trạng thái (Trống / Đang dùng / Bảo trì) | |
| | **Tại bước 3a.1:** Hệ thống truy vấn tương ứng và cập nhật lại lưới máy |
| | **Tại bước 7:** Nếu tên máy bị trùng hoặc để trống → Hệ thống cảnh báo: "Tên máy không được để trống hoặc trùng lặp!" và yêu cầu nhập lại |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC03](http://www.plantuml.com/plantuml/png/PPBDYXD158NtzHI7NaY2qkA_T1QEZiY0enlp0fdfJggmwtRehWuCP2Epc8K8PZKBDsa7Dv710ON1xcM5UOz-4szL1mrcLN1_pldklRLNc55kIfsA-VDOAveB7n3HlH3xicq-4NH29O8tjZh1q5wI361oko8KAawEHlb0p0_QvWo6gnSqcOleVeXXNfvWrjRV2F6890fR7Kjefs6S1apRVCV-kvU70n4z276WskQKlCP7f3TNRVD5RPVFEF-1KUoon5JQhuGZZfmPHcdhYYRSQIir-3j4z334M-dzoTO6mMqCfLrfk362NUH7_o6yod8DOe7BALBdL3e7xdTCMpQFGwm_TsoS_-L057NWsizmowimHd1uSrNkEginUg-Ti-PHHeZRviV6y5cVgn92Znikc89ZVG54JqBUhnFRjfdPPURNYTvmUoEShg-FiLxmK2GZleltxG-SqbC7RtzoxPZ7lOFKBlL6R_S4hgCmsLGaQP4u5_kR9dStUbooeWqTVvmNjieSyBdoYKxbVRUVzS99NtHCf-v-Na1c7jHuMUwjhwV_72bMOx773__JFm)

---

#### UC04: Bắt đầu phiên sử dụng

![Sơ đồ ca sử dụng UC04](http://www.plantuml.com/plantuml/png/NO-nIiH054Nx-OfB-bs4jGfBMDt6IbWMFs1CdidWpCioymO9Ocszdo0MWY3sMQpYZyoVc4HiBE-xvpxktGJHNgApojATG5fuKpU2odWgnRIimhtXW_RQGPVI-hrqbg0VABIEr7p2HUKCgocWkHxTx2gTdY5DEXsvnhQx9NzDak5H0J5GgSF8t6pFpdDSflvJy7rC_Ll4eJ73EsVG0PFxZrxbsCNKlpBiy0Ktl7IGuOERFturybCCosKnQ-HOhmsNDbPK5Ef9pJsnM1I_xpV4rRZy1m)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Bắt đầu phiên sử dụng |
| **Mô tả** | Kích hoạt phiên chơi máy tính cho một khách hàng (thành viên hoặc vãng lai). |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Có máy tính ở trạng thái "Trống". |
| **Hậu điều kiện** | Phiên chơi được lưu vào CSDL, máy tính chuyển sang trạng thái "Đang dùng". |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Click vào một máy tính có trạng thái "Trống" trên lưới | |
| | 2. Hiển thị dialog "Bắt Đầu Phiên Sử Dụng" hiển thị tên máy, loại máy và đơn giá |
| 3. Chọn tài khoản Khách hàng thành viên từ dropdown | |
| 4. Nhấn nút "▶ Bắt Đầu" | |
| | 5. Khởi tạo một đối tượng phiên chơi mới (gioBatDau = thời gian hiện tại) |
| | 6. Thực hiện INSERT bản ghi phiên chơi vào bảng `phien_su_dung` trong CSDL |
| | 7. Cập nhật trạng thái máy tính trong bảng `may_tinh` sang "Đang dùng" |
| | 8. Đóng dialog, làm mới lưới máy tính và thông báo bắt đầu thành công |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| 3a. Không chọn khách hàng thành viên mà nhập tên Khách vãng lai | |
| | **Tại bước 3a.1:** Đặt tên khách vãng lai và không lưu liên kết mã khách hàng thành viên |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC04](http://www.plantuml.com/plantuml/png/RL9DIyD04BtdLmmvAKZ0RqakhRQWAANOUYunATcboQQaIQ6GcuSUH6ZF8YOK4OIYu45CZbly7_jFdDrMHV4qcPUPD-_DR6KGsr6S1BwMLjs0ibHJW6Pk6d3aKwS7ZXsv4F1y174aYfnv41EUKz3RaIYdpDCjBJqMvHXXbQUQkMN0MMYxuDgCm83d3e4UKO7m30c6F0D2HNd5LDCrrAZjXnuSYk8vXiLK58y9z0dbJypwrcLkeoAili4cdb5a34KnOr_CiQGcC9HD87-zES28AEPzB4VeT5asv3CKuDlKqimT0nfOyS20yI86VJL-8iV_ushNG19HtbE8SG-Xr5bECVbu4QKaRHBQPQsabk1t8CfR2ci-ie_0Gwjtol2OgLwwZjFt33XfjEhdRRXKFZn2vMB2PUf9_rtM6IGT5mcnVj-0YsQjsgx_kSFoD7mkLsqZgYycChZyNTt6F33G4d-LaDho1lWy2rOAVIaUuwzh_31Q-1h2lbRfCXUVo2S)

---

#### UC05: Kết thúc phiên sử dụng

![Sơ đồ ca sử dụng UC05](http://www.plantuml.com/plantuml/png/NOynReD044NxFSKNFPOBqY0BEQQn52bDb0Eibp6i30lQ7HIXABLhdyAIc_IuI65VP6-I1NSkP_xxV_wi7KlBVLEBclOCRc5rMJ4ARKcnRerm1sqwQMK3gRYr7ppK1Fb5hcr8p2ky5eqsOZ98KmOrslhh4LpvwycKo8STsNVY2Dy2w1qfwGBpcIzVKhpvyI-ShMwZGbVfsyL4a0wJ-0GdAJP-_6NSJtuyzqzq4kZ9XCKYcsUaMAsqKNLVK9Q97p6NH1ndZ_0rcIAy_Gy)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Kết thúc phiên sử dụng |
| **Mô tả** | Tính tiền giờ chơi, tiền dịch vụ, thanh toán hóa đơn và giải phóng máy về trạng thái trống hoặc bảo trì. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Máy tính được chọn đang ở trạng thái "Đang dùng" (có phiên chơi đang chạy). |
| **Hậu điều kiện** | Phiên chơi được cập nhật thời gian kết thúc và tổng tiền; số dư khách thành viên bị trừ; máy tính được giải phóng. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Click vào máy tính đang ở trạng thái "Đang dùng" | |
| | 2. Truy vấn thông tin phiên chơi hiện tại của máy đó |
| | 3. Tính toán thời gian chơi thực tế (làm tròn đến 0.1 giờ) và tiền máy tương ứng |
| | 4. Tính toán tổng tiền các đơn đặt đồ ăn/nước uống đã gọi trong phiên |
| | 5. Hiển thị dialog thanh toán gồm: Tên khách, thời gian bắt đầu, tiền máy, tiền dịch vụ và tổng tiền thanh toán |
| 6. Nhấn nút "⏹ Kết Thúc" để hoàn tất | |
| | 7. Cập nhật thời gian kết thúc và tổng tiền vào bản ghi `phien_su_dung` |
| | 8. Nếu là Khách thành viên: Trừ tiền trực tiếp vào tài khoản, cộng tổng giờ chơi tích lũy và cộng điểm thưởng tương ứng (1 giờ chơi = 1 điểm) |
| | 9. Cập nhật trạng thái máy tính về "Trống" |
| | 10. Đóng dialog, làm mới lưới máy tính và hiển thị thông báo thanh toán thành công |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| 6a. Chọn nút "Bảo Trì" thay vì "Kết Thúc" | |
| | **Tại bước 6a.1:** Thực hiện thanh toán bình thường nhưng máy tính được đưa về trạng thái "Bảo trì" |
| | **Tại bước 8:** Nếu tài khoản Khách thành viên không đủ số dư để thanh toán → Hệ thống thông báo yêu cầu nạp thêm tiền hoặc chuyển sang thanh toán bằng tiền mặt trực tiếp |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC05](http://www.plantuml.com/plantuml/png/ZLJBQjj05DthAovP59iQ4JSjRQL5azW5Gx8eD8Lk5ScM1aiZOyi6WpUbYowoSIXTb5AmC27Wnk3MXL29aiKO_SVyIUyT-HcowDAQUnxtp1aVTYAh7NK3NniSEG7Z0qrzq8oo3XMVsKsmhRO3WOZx4BLb4dCN8a_43FOMGmj_EE8FT_TCRL2JwISyakabTmUQyKI7itQt3psPN75eUKnCEDXUXiZ59G5j3zdwfcOSm2DuYjDYoZs8c4m_y5olu8jHWAhY5qUCJEuuxEjbhmYF8TeUTcHwONkeb5wXa8WZw33k8VCp7Meuyf4hNnTGPPOVad-BmA68ENXYRY5v5fCvHep9NMIkms0DADJ42CVHGFgLkw-A2AzpA9pG84MHs3lR5JM0Ura06CynJPbCMy1nwscqcmUIA8MX64DpGsiYaFHFS2LSRpdL8-3QHKNCIJ0VN0nbUWqTL0Kdcs568mPDBvJ9-040cdgX7z0boVJ7AibjI0bijJ6uUFOTxmWpjoa7IZGmLoIdsQoBarkxDNE_PDU5dXY5K7bRFSq1TRzJLnlzvkwM3opBiOX3WkA1odgpI0dwOg8qhhkgW-DrIsHo6m7PlRM1OvrQbD9aQQxE7TRGjZJ9LK-CKS2tMAvkxFGZakaNwdVEJe9qqbtge7JwqyuhqYA9bO3nKeTtRwf7Pw-NxqILiqCbl_TaAEnBzLnoKtXZn7oEEuLKz1j5y725tijq2hxukrFVdHwTYniwyB8PNXXtoQBnVxwErmR8cbhBAE_hLCavJZN2Tb02zHe3cNvZqCbYTKcVmISn_B8HCF4V96nfXqY3Vol_00)

---

#### UC06: Gọi đồ ăn/uống

![Sơ đồ ca sử dụng UC06](http://www.plantuml.com/plantuml/png/NOynRi9044NxFSMNUvCqfB2G1Q98f4Xf40TOx8czYhssTiSA255HK54WRf4Zu2A5VP6z2Mj7QIZdptkZFtFBqd1J5gAWJmPNC2hB6QaobB2gjB1VIjVIo18ouSgiU5SGv3VPgYGnHbYafT9Y4AJE_3PuS-q9dBlseZCiTnio7yG1zW9eB2NIUcQzV7w9yEhQiq9_SUqL_L4_DQCJG5eCm8CmZV3kRh--PzxT4jIvwdxqFppryE1WCed76H5cCzeowJIEnK6CDH66yT_fkS_zutS)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Gọi đồ ăn/uống |
| **Mô tả** | Đặt các món ăn hoặc thức uống cho máy tính đang hoạt động, hóa đơn dịch vụ được tính chung khi kết thúc phiên chơi. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Máy tính được gọi món phải đang ở trạng thái "Đang dùng". |
| **Hậu điều kiện** | Đơn hàng dịch vụ được tạo và liên kết trực tiếp với mã phiên đang chạy. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Click vào máy tính "Đang dùng", trong dialog thông tin nhấn nút "🍔 Gọi Món" | |
| | 2. Lấy danh sách các món ăn, nước uống còn hàng trong thực đơn |
| | 3. Hiển thị bảng menu gọi món (cho phép nhập số lượng) |
| 4. Chọn các món ăn/thức uống và điều chỉnh số lượng tương ứng | |
| 5. Nhấn nút "✓ Xác Nhận Đơn" | |
| | 6. Khởi tạo đơn hàng `don_hang` và các bản ghi chi tiết đơn hàng `chi_tiet_don_hang` |
| | 7. Lưu đơn hàng vào CSDL và gán liên kết với mã phiên chơi hiện tại |
| | 8. Cập nhật lại tổng tiền dịch vụ tạm tính của phiên chơi |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| | **Tại bước 5:** Nếu Admin chưa chọn bất kỳ món nào hoặc nhập số lượng không hợp lệ (nhỏ hơn hoặc bằng 0) → Hệ thống hiển thị cảnh báo yêu cầu kiểm tra lại dữ liệu nhập |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC06](http://www.plantuml.com/plantuml/png/PLAxQXj15EtdAmugZ0rXJHwEpQgm4maYyAEnYxJhrNftoEgkq8u41ZN1HGhZmeLnQGjZWXC4ygFQALoCsV-OFydTMH4hf9gP-pZtd7DdArV1G0rxgHX_wlOaZOKB2F-jXrPYzHc1JA7G-C9tYLtpI0seWVb5wCeWpMBq4rczNiu3YX4cjfWSDyMuO_KFA0OuftWi_7SU3WR3OuniSKikVXzoIpaXz1WIBoUqEdJ52Cq38J7NV5LM_qOtKC5Xa4TDuR_tq95MdzG8fsZN30vjSLDLBwA6DVSAlFbNcl-1QPXfc2ociGHYrjC-SYQ0j9nPxU3A6GF5iFgEOXw-vc4lSVHhGxwQILXtKZspyPziUOJbbdbSdBQvmkc8N7n50Fv73zkSlfAijfXaQ6VKgPnSOllbWOpKFD2iYjSzx9IpuLnZRTB8N6TexRTttY1rc_XcYsV5msxcgt6T6xnE9z8nLfMr5vL8jlCxhvBTP5T6Ljyw6ZqeCoMsoUgVmG9ChhA-YD8yml9sOfueTX8-6-QTMdqfMMav2_vnztMphm3KbKTYYm_-Tty0)

---

#### UC07: Quản lý khách hàng

![Sơ đồ ca sử dụng UC07](http://www.plantuml.com/plantuml/png/9Oun2i8m68NtdEBHlU0cE4YbYvCWuW5Y-fk4DgaaVv4YpixUGF4M5gUUf3UnrVLxttkyBB8CtBXAL7HaS8rWjM4KDf1YMtiHI-jFCaW7gRWEEsuhWZnJh1s97yAgSDQBgI2z7jDaFNGti1cwkzV8smE53N62Ym2QI4h6qTddi_a2ssPulpog_eFIz4zbOFg7rmban6I8g_Yj8qsNVv2HByQ_Nm)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Quản lý khách hàng |
| **Mô tả** | Xem danh sách thành viên, thêm tài khoản mới, cập nhật thông tin hoặc xóa tài khoản khách hàng. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Admin đăng nhập thành công và chọn phân hệ Khách hàng. |
| **Hậu điều kiện** | Dữ liệu khách hàng trong database được cập nhật chính xác. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Chọn menu "Khách Hàng" trên thanh điều hướng | |
| | 2. Lấy toàn bộ danh sách khách hàng từ CSDL và hiển thị lên bảng điều khiển |
| 3. Chọn thao tác thêm mới bằng cách nhấn "+ Thêm Khách" | |
| | 4. Hiển thị form nhập thông tin (Tên khách hàng, Số điện thoại, Số tiền nạp lần đầu) |
| 5. Nhập đầy đủ thông tin yêu cầu và nhấn "Tạo Khách Hàng" | |
| | 6. Kiểm tra tính hợp lệ của số điện thoại và số tiền nạp |
| | 7. Lưu thông tin thành viên mới vào bảng `khach_hang` |
| | 8. Cập nhật lại bảng hiển thị danh sách khách hàng |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| 3a. Chọn 1 khách hàng → Nhấn "Sửa" | |
| | **Tại bước 3a.1:** Hiển thị form chỉnh sửa Tên/SĐT và cập nhật database |
| 3b. Chọn 1 khách hàng → Nhấn "Xóa" | |
| | **Tại bước 3b.1:** Hiển thị xác nhận và xóa khách hàng khỏi database |
| 3c. Nhập từ khóa vào ô tìm kiếm → nhấn "Tìm" | |
| | **Tại bước 3c.1:** Lọc hiển thị kết quả tìm kiếm |
| | **Tại bước 6:** Nếu tên trống hoặc tiền nạp ≤ 0 → Thông báo lỗi và yêu cầu nhập lại |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC07](http://www.plantuml.com/plantuml/png/PLBBIiD05DtdAmvTY689Fb6gudDHKDmqFn3xo0IJcvecWj2Dk70XWYv4H8GM4L4G50KXMQRsF-PFl3DHJ76LpECyxwnr8ZkCOjypkkiDtwMkeJUCohI9JI7JSuBVf1YbRP7rwmBLh4TE2L6OFHCYOPF0uDALwN4CCNoLwIqvIqQtAjDJFfNf9JbTep9ZmWhZ8npAv87GKA2EPjlFIOKYnM7MWs2g4zBGCtXAOqyczvhpnrvbriIkq4IbILWYU_QHUolz5vupKIqojeBGnvPhUu73EmfQT50GcSz5Njgms4GPjS65LOQbWX989lqszcpYw39vZ346oXrPV1Byi-6sCBwjRFZScws4FpXkvDBg12-QX76-Vz-6nwW90wWiS5yoVLFqwGrVFU3krF-LooXj8LA61HnUtQ4kXdrNrwT7eMIo5mxbVJqTBULyFCsTuMjSq1xjtkV9QOe0cxMj7OrQD36uoDxvjA4RA_D0UlxFrS8GymcDZ4xXEr7GDffUfydXSrKncd8ZwmTgTSsfz6iATLyGWKm-wxz9yxANLp3rHqeDjsMiyOVVxJS)

---

#### UC08: Nạp tiền

![Sơ đồ ca sử dụng UC08](http://www.plantuml.com/plantuml/png/NOynQiD054JxFSMWtYQTWp32mKqgGmW-m7hrersy-ZAxNmHZKgHoxHlOv1OMgNIIlKaaXJGf__nvpCmgYWxIrbvvUXD8W-0gAoXT82EkOHNtZWyww1hQI1DUvUW9-frYKvEQ93oLjMCr0fghuPizf-uCiQcxS8NrSKTXGvBXf80saj5ny6pN3uyvDkb-Eq1SwZuvWuuOvN-sHOwNDjs_6Bx_njxsDsDX-ojNVyHY84OGytantSYnN3eslYsfADI7cYfYDYj-0rR4vJ3w1m)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Nạp tiền khách hàng |
| **Mô tả** | Nạp thêm tiền vào tài khoản hội viên của khách hàng, hệ thống tự động quy đổi thành giờ chơi và điểm tích lũy. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Đã chọn một khách hàng thành viên trong danh sách. |
| **Hậu điều kiện** | Tài khoản khách hàng tăng số dư, tăng giờ chơi và điểm tích lũy trong database. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Chọn khách hàng thành viên từ bảng và nhấn nút "💰 Nạp Tiền" | |
| | 2. Hiển thị dialog nạp tiền gồm thông tin khách hàng và ô nhập số tiền nạp |
| 3. Nhập số tiền cần nạp | |
| | 4. Quy đổi thời gian chơi tăng thêm (10.000đ = 1 giờ) và điểm thưởng tương ứng, hiển thị xem trước (preview) trên giao diện |
| 5. Nhấn nút xác nhận "Nạp Tiền" | |
| | 6. Cập nhật số dư tài khoản, tổng giờ chơi và điểm tích lũy của khách hàng trong database |
| | 7. Đóng dialog, làm mới danh sách khách hàng và hiển thị thông báo thành công |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| | **Tại bước 3:** Nếu nhập số tiền không phải là số hoặc số tiền nạp nhỏ hơn hoặc bằng 0 → Hệ thống thông báo lỗi: "Số tiền nạp không hợp lệ!" và không cho xác nhận |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC08](http://www.plantuml.com/plantuml/png/XPBFIiD04CRlUOeF3rAn1FyBBQYr5GIbAAqFK9kQNPfiYrb5eHVnq8C8zY0Y8hGK4OL2GK_9SRNliM_YPEEVYWTFOMVcc_b-CraBPELG7lcUrSevFXSjomIipAoDFDFHfK2TgLwLWQckS76ikX1CXmy24qKTzfeeSnsTYOcirThKKHkIH1tXjgpCd8rDofqB4xj0WLUyXWjXLDAeijzJC_CsYjHtq4H03JxpgEhmKIIIlmCMREmUdUAjewDhZacmyMakFUunJTauwbEdw9RyIwQUVPpKVCZ3qL17TzKn3uk91s8JAfGu8Mu3E_WNbXyWLHhpdK2kO6PrYefg0gcyUfcoWCoIZRsTGgwyaN0wes4QaW38MsAvoaOzRlRyPLsg0UtVUtywZSCDv4k5xMpSQzd6svLw8QbZTfk6fxe-VCBYS2g28J2dszTXduh63ndh0zbeMZKlg26rnTIhS8t1TTLhmDFH3VzrYOIeRkf0sFrcNDJ-wICSVc2jqOT-fWy)

---

#### UC09: Đổi thưởng

![Sơ đồ ca sử dụng UC09](http://www.plantuml.com/plantuml/png/NO-nIiH048RxVOgVz7VO7OOZd5nZ9OZu0Ejcp2wtcSZk13d4sbOBQnLVmDfW5V4zzatSn4AuSkR_FcR-JHGTf6-zydGja0x1DLPGkq16NCSgxXpVwA1RQ2DTk92z9-XRYbrBQbxXf6uTgqdGtEIqE4t30yIcuOaRRFTN5Cv82jmfe8zaTCpCvVRekCJtOngUNMP_FjBmmaq17J551-Ygn7cVFjyPVlp2pevlniAEh__6AXkJYEMoccUKMAyT6z_NL5NgNirlOh6e_WviYEjS_1S)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Đổi thưởng bằng điểm |
| **Mô tả** | Khách hàng thành viên sử dụng điểm tích lũy (⭐) để đổi lấy đồ ăn hoặc thức uống miễn phí trong thực đơn hỗ trợ đổi thưởng. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Khách hàng được chọn có điểm tích lũy lớn hơn 0 và đã chọn món quà đổi thưởng hợp lệ. |
| **Hậu điều kiện** | Điểm tích lũy của khách hàng bị trừ, hệ thống ghi nhận lịch sử đổi thưởng vào database. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Chọn khách hàng thành viên và nhấn nút "🎁 Đổi Thưởng" | |
| | 2. Hiển thị dialog đổi thưởng có số điểm hiện tại của khách và danh sách thực đơn hỗ trợ đổi thưởng (các món có cấu hình điểm đổi > 0) |
| 3. Chọn các món ăn/nước uống muốn đổi và điều chỉnh số lượng | |
| | 4. Tự động tính tổng điểm cần dùng và hiển thị điểm còn lại sau khi đổi |
| 5. Nhấn nút "Đổi Thưởng" | |
| | 6. Kiểm tra xem điểm tích lũy hiện có của khách hàng có đủ để thực hiện giao dịch hay không |
| | 7. Lưu các bản ghi lịch sử đổi thưởng vào bảng `lich_su_doi_thuong` |
| | 8. Thực hiện trừ điểm tích lũy tương ứng trong bảng `khach_hang` |
| | 9. Làm mới thông tin giao diện khách hàng và thông báo đổi thưởng thành công |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| | **Tại bước 6:** Nếu tổng số điểm cần đổi lớn hơn số điểm tích lũy hiện có của khách hàng → Hệ thống hiển thị thông báo lỗi: "Không đủ điểm để thực hiện đổi thưởng!" và từ chối giao dịch |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC09](http://www.plantuml.com/plantuml/png/ZLDDQnDH5DtFhpXqKHAG4RzG4zJMf51HYjZu0wQJTDx3cJiryr8GiikYYr9e5-B2XGaYGIKWfXlpbY_wFzu_yRutQHgr2pVpXZFtddFkkMys2XLrLIzBW_vcEvFK3pmGr6w4Q0YhJmYlX1d50i8CAO5oXy2XD5y8wo1XPvy8Q_DJgzzAjCIlRrP_e6Ij7lIthJxYUglFAEa7jPiXjgNL0_BOCPeoIlC4ypFV-RfdXinNS3L3hYw3CXFMJNz-VWF1o14XDjFwqcZjrj9Yp0vnm5x6L39LCZCbp0TqlUVrgyZyouNS8TTuhTGP_aZ9ltvlXsYP2S-gk8EIfQlOgxJD3yRMBr7pdP3QsKYYY7gScLn8hVYz4sAdp8lCJFrFQ78VbVbfgR1O0qU0XmzmWPPkIgb7LUxi42eDCwq6GErkY2SxkrilMaZOZzlLn9dKnyp3qq_-29yZoR5dPnm66hlDPtN7S2_4o-VDpTOMLDVghrUj9hPw7FrrIRevFzkHYlQYekE9xeTicNV2iD_xDQHcc26p-htaIewvy0GB0wjqhhzG-K7GIOiEAa-5EUU5EjB7PfIxUTv9rt1EvMqQN-NIVHYipdl9Icsv7spmmN_1Rm)

---

#### UC10: Quản lý menu dịch vụ

![Sơ đồ ca sử dụng UC10](http://www.plantuml.com/plantuml/png/9Oun2i8m68NtdEBHlQ2hWrIwE0aY7Y2slsqmIINvgnHnTd3o3E8b34wU93UnhUltliTxcMVfk3LQQDepk85JLSqebQE2LME5FoXxb4uQo88Rj-5E4-IPV6D8Z0YBqYWhXeAqLPyconXku3g6XwsGTpjoA-845m6qdWhfUsURJoSphDluVbhexmU6R8ioXdjHunJ3Au7q62nn5UC2qdJ-1ndPil_y0m)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Quản lý menu dịch vụ |
| **Mô tả** | Admin quản lý danh sách các món ăn, nước uống phục vụ tại quán (CRUD thực đơn). |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Admin đăng nhập thành công và lựa chọn phân hệ Dịch vụ. |
| **Hậu điều kiện** | Danh sách thực đơn trong database được cập nhật (thêm/sửa/xóa món ăn). |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Chọn menu "Dịch Vụ" trên sidebar điều hướng | |
| | 2. Truy vấn danh sách các dịch vụ ăn uống từ bảng `do_an_uong` |
| | 3. Hiển thị bảng menu (Tên món, Phân loại, Đơn giá, Điểm đổi thưởng, Tình trạng còn hàng) |
| 4. Thực hiện các thao tác Thêm món / Sửa món / Xóa món | |
| | 5. Hiển thị form nhập liệu tương ứng với thao tác được chọn |
| 6. Điền thông tin món ăn và nhấn "Lưu" | |
| | 7. Kiểm tra dữ liệu hợp lệ (tên không trống, giá bán > 0) và lưu vào database |
| | 8. Làm mới bảng hiển thị thực đơn dịch vụ |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC10](http://www.plantuml.com/plantuml/png/PPBFQjj04CRl-nGVFXGRYjB_AN9BqyQ7G4Cl2QLNHPArIxKh8wqC1LzA3pc4GaqFFUGI4t9883GbXO9rNEFts3Vfx2ffNVSYCRFppU-RsTsgT5JgMkPiyZAHGasOJx3mVe1jRflF2Z9LDJe3snp576zjSzQ1Bis5GYMIz20giPWAstoimPTNjZbMMPzDTcnp24twgSecB7mGOB-iFs1ivsSAstk3NIIHugZCZ7fwm69gcwzOV59UTXwZzk8-2ny6s270pSaH3kpyL6Lj0qbsOYIjjp45_JzpX8y2lE4UkC_DXTpOiyrbjF7EN4UGvbfvrYratV3ZDUP0H7cHOLYK4egQNexemFma6reetsu5_2J0uejRYLelmXsl9akTtULLtVaFB8ReldPWIJkEt63VaLDyMB_eKLMgq2N1wGWvvNeC23S3kYdNR9KLUuVUg6x1OtDI-CNtdUYfSqZfTgAxoCs9TBiy5hVRvQlJk--FcAveELDEN-bYnDAyIibi2-N_cdfbPeMBledL4V1-lHXTxHwIBltuu8MT_xhX81DcXcVFSQ_t5waICMHRzAC7-ni)

---

#### UC11: Thống kê doanh thu

![Sơ đồ ca sử dụng UC11](http://www.plantuml.com/plantuml/png/BOwn3S8m54HxJt5Ado8j1GfAGqK33F29Fub5R2FxHoX2rDIimGefcIIRu0JQz-vELqQX8BtjLCTdWNW4qxG2RGBNOhnJyMBSbG9PK2q-x6Ne67JZw2sh1M6ZhN5gBf1haissq_Y4jDFuSWsguSHXnvBXhe0-SaqnPOvLKQnm-8SkdpUq9zUcMf-18cQl7chPHfwlVw1afzFRBm)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Thống kê doanh thu |
| **Mô tả** | Xem báo cáo tài chính tổng hợp về doanh thu giờ chơi và doanh thu dịch vụ theo khoảng thời gian tùy chọn dưới dạng biểu đồ và bảng số liệu. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Admin đăng nhập thành công và lựa chọn phân hệ Thống kê. |
| **Hậu điều kiện** | Hiển thị biểu đồ doanh thu và bảng tổng hợp số liệu chính xác theo khoảng ngày được chọn. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Chọn menu "Thống Kê" trên thanh điều hướng | |
| | 2. Hiển thị giao diện bộ lọc thống kê (Từ ngày, Đến ngày) và các nút chọn nhanh (7 ngày qua, 30 ngày qua, Hôm nay) |
| 3. Chọn khoảng thời gian thống kê và nhấn nút "🔍 Xem thống kê" | |
| | 4. Thực hiện truy vấn tổng tiền máy và tiền dịch vụ từ các phiên chơi đã kết thúc trong khoảng thời gian đã chọn |
| | 5. Tổng hợp dữ liệu doanh thu theo từng ngày |
| | 6. Vẽ biểu đồ cột trực quan hiển thị doanh thu tăng trưởng theo thời gian sử dụng Graphics2D |
| | 7. Hiển thị bảng tổng hợp chi tiết (Ngày, tổng số phiên hoạt động, doanh thu) và các thẻ chỉ số (Tổng doanh thu, Tổng số phiên, Doanh thu trung bình/ngày) |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| | **Tại bước 3:** Nếu ngày bắt đầu được chọn lớn hơn ngày kết thúc → Hệ thống thông báo lỗi: "Khoảng ngày thống kê không hợp lệ!" và yêu cầu chọn lại |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC11](http://www.plantuml.com/plantuml/png/PLAxIWD15EttAmuf9884V4EY-4X0KR5Hn7QpMJDBihDnTqOGqeX54B4G4H4RGn0XZOfMEuZ5YFynV-ATZKI9rUK-pZtddfdbHBYna67JQQ_Kme2tdQpW52SBA3EZBZX2dqlaTYcvv7LiwduE8jPz3i5Spl1v6HXr8i6-deswu_MIqrutgaFT3D1sYbC5hDFCASzgvwWQTOicxVQmKN65NsPHbFWnnk3fhWUkKm5lmCqjHMcehJWzLDLWaKbx98ZNzVqnOQicVGYHs_T3_DNwJqvmWFnsXY74ao3z9dsKM5Z4eDemwOUWSPrwI-CKVOvyMR-EEq1nXdXc2zYDvJ6EY8-Zl5FPce0mwfhuYABNGYsojWWcBJWQw2jPz5m1UoPzHzMQ8Isnke97PWYinMwB1LuoLI5lZNhnS2XTdg7cHztxWvx0jAscAlFh30dTEB32EfDbJHhlIEe7484zpIvDHDHo_6RY8x_9zDjGEZrA9pjqLNSZczq4eqR_-8Y6xbDh1FkxczU20sUP0dsjRm)

---

#### UC12: Đặt máy bảo trì

![Sơ đồ ca sử dụng UC12](http://www.plantuml.com/plantuml/png/LOynQiD044NxFSL7lOrZS2ECi74JCBWnES1QcaXBjBjcTumjGkgqoH6Ckv0-81Sfb8liJRnIqgISF-yDVnQUfUE3haLDZmosSAgi68LobBEoHlWdPVRIIGsPitLRRcg2F9AtciGGOLbePKGlI5F6RN8Vhg_WAbpVJObLioEt8KxmB823frpwo3oixgOfVjv2-yNGtRd1BhGN2tRTPmBfqGF_XSainJgqtx5drRKvzfNgFimVF9b5k7Sm7cV3Z1JpEPsOJ95buaKCDJ4QPR-d5p6FZzy0)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Đặt máy bảo trì |
| **Mô tả** | Đưa một máy tính vào trạng thái bảo trì hoặc khóa máy do sự cố phần cứng/phần mềm. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Máy tính được chọn đang ở trạng thái "Trống" hoặc đang thanh toán phiên chơi. |
| **Hậu điều kiện** | Trạng thái máy tính được cập nhật thành "Bảo trì" trong database, không cho phép mở phiên chơi mới trên máy này. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Click vào máy tính đang hoạt động hoặc trống trên lưới | |
| | 2. Trong dialog thông tin máy, nhấn nút "Bảo Trì" |
| 3. Xác nhận chuyển trạng thái máy tính sang bảo trì | |
| | 4. Cập nhật trạng thái máy tính thành "Bảo trì" trong CSDL |
| | 5. Đóng dialog, làm mới lưới máy tính (card máy chuyển sang viền màu Vàng và có nhãn trạng thái "Bảo trì") |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC12](http://www.plantuml.com/plantuml/png/PP11IyD048Nl-oiUFKj0gvVaqje8FNWGhE8r998i9fEIRWj2BkB1WmZsx2N1cxGWA1Gsnot-Z_qdJXEGWfSTzZ7ppNipcAcWKFCi5UKmoYILeXM4U-XWbChm3WjJvSXCVGzbrfH05UQ3aFvyskPDUg8Ss-O9Ah7Dak9Ik4SEnj8sZzHgp_1baEOnVymtSP7KiPWACbhXrEht79F2hBm_2sxVmOsfGr1YzPeGCkc5i5rQlL7eTIFiOzNxl__OmTM5FvoSSOFLDRMhQxcVO1Qmka_XDsTfTEcVUy8zSR1zDLyiHAtt0uJiOjgPKGY38kfeBMV1OHzseQevhar5iSSNpATYS4iHd_KN)

---

#### UC13: Đăng xuất

![Sơ đồ ca sử dụng UC13](http://www.plantuml.com/plantuml/png/9Oun3e9054JxFSN4diBOMHWCZPMDyG1V-C96TjViVgB4M5dOwY6yXBHSX9kuOFlcpMJI8EIbCRMg-IWG1w_BIb1epxbePrKuQNicJmQKY_CxQMi6NJWumsf2M1T6MpKMo9OndMs6xWcfXkvjIsJjWVsMPOQR0fh0EONexBFvOed-rJ-YSss6xoSQ531oTLVJ9f9azGSfsoA-_04)

| Thuộc tính | Mô tả |
|-----------|-------|
| **Tên Use Case** | Đăng xuất |
| **Mô tả** | Thoát khỏi phiên làm việc hiện tại và đóng ứng dụng an toàn. |
| **Actor chính** | Admin |
| **Actor phụ** | Không |
| **Tiền điều kiện** | Ứng dụng đang hoạt động ở màn hình điều khiển chính. |
| **Hậu điều kiện** | Ứng dụng được giải phóng bộ nhớ và đóng hoàn toàn. |

**Luồng sự kiện chính:**

| Admin | Hệ thống |
|---|---|
| 1. Click vào nút "Đăng Xuất" ở góc cuối thanh sidebar | |
| | 2. Hiển thị hộp thoại hỏi xác nhận: "Bạn có chắc chắn muốn đăng xuất và đóng ứng dụng?" |
| 3. Chọn "Yes" | |
| | 4. Thực hiện đóng kết nối CSDL hiện tại để đảm bảo an toàn dữ liệu |
| | 5. Giải phóng tài nguyên giao diện (dispose) và thoát chương trình (System.exit) |

**Luồng sự kiện thay thế và ngoại lệ:**

| Admin | Hệ thống |
|---|---|
| | **Tại bước 3:** Nếu chọn "No" → Hệ thống đóng dialog xác nhận và giữ nguyên trạng thái giao diện chính để tiếp tục làm việc |

**Biểu đồ hoạt động phân làn (Activity Diagram Swimlane):**

![Biểu đồ hoạt động phân làn UC13](http://www.plantuml.com/plantuml/png/TP6_IyD05CVt-ok-T4f00XPTsi6AWXsaIvSwnYHD3fDBQQwbXMmTEZZOZawsI154GN1BXWuN-d_SV-9BB5e4b_lnxhtlz_FUjH9f3-Ke2bbwxaPSfAmAiCQnXKwWius0K9b4hLWKC-6ZDwAOh47dJt16EbzooC0M0HBkUhVsiCdIjixd5AGtuQUiKRVGvZgVYIfs3reU1tIETRRcvUs1OwBM3WJPlGiKoyfeKXarVx1u7qRl82rGAt8jbgGc_3EJ13q1uyPBOEnHlnLCam5_c836YOLYeJufpIQmcCHmfxEjXAXwkkXUNfEJoYYXNM-M5QSMhhZEdZa6GLNeSpk6IwtD1SPgLRQarX9EiFlOhUbT3jKR8HhTQIAzoF8cN9gbKYBZ0VF2n8FHYM4Gsilq7yezeiljCFOFftG4IInRcgFEDmuHSVKg4AfLX759vFmQ2PVtMOis-kGl)

---

## 3.4. Phân tích hành vi và cấu trúc hệ thống

### 3.4.1. Biểu đồ hoạt động (Activity Diagram)

#### 1. Luồng Bắt đầu phiên chơi (UC04)

![Luồng Bắt đầu phiên chơi UC04](http://www.plantuml.com/plantuml/png/LP2nQiCm48PtFyKzHxma9O4MIWYDEmVL2f68BHbBAdJkqA4K4ZfsiIcbK0ZjLYgFCdaFlKbECXIoI7Udx__lT6EbIQrDMIGnI4OJMd81US7pBPIkVO2izjrURD9aTEcx9z0ifb18Ge4ImK2vDcUmPV5YhXbOxhj74Ubde9mKSWCNtdvhw7VUVXfOCEw-HFhV4C4NSUO2J7hxcuDWtXugq8Z3lNiFlGb7uHph7-BC5QkPjws4VezJSj377zy51NPRYwMP6enBtxq5_TLyERl9qBAHSETjmAg0hPHPKHD_SRkOJh8Pw1fDyLdZT7pOYYAOzxjmKlSNuMlNb8Cvg6CRHtWTOEqEWgMuOLabuxMWkEuJ)

#### 2. Luồng Gọi đồ ăn/uống (UC06)

![Luồng Gọi đồ ăn/uống UC06](http://www.plantuml.com/plantuml/png/NP8nQy905CVtV8et5OL2TnrgYQMMjWxLeMjCOkumUP7c53eMXr94qA6xGQHGA4XrQMxeSEBtk6_IbqjfjLEET___-__UkzGIuToBGHHQvc1Lpho88xWXTtiGgVG1jbC70_3K9mPLgz9aEbiYN6WvuN2ZraYrXfPF89YMCnA6iUD1vEC0F2t7BeEXbajmrGS2KtFJey6r7A5nZA6ZimNP_ZkgloIacY2Xh16s8pmP5357vk8P6IHKW72tqdAnJtYdKXUGpk_ac-rI0kLTADbgVT0IrJo67bCR34xBXEIZqPGjW4fRPsaCzHWRZacq6MzpN_mKghdaijawlsqN0SLyXeo3u3hx4Z3Cszkj-hLHsqJJBw0422rVI4v2-OY7ozBorO4-u-ejdt-NSkFUJWcONe8xOHmS4siE3Hr5Z2AFPhJbJS6LYBXl-M7YG-dAoCmy1tjtyxspmauPAsT_BNyzqEDTgqOV-aM-0G)

#### 3. Luồng Kết thúc phiên chơi & Thanh toán (UC05)

![Luồng Kết thúc phiên chơi UC05](http://www.plantuml.com/plantuml/png/RPF1Qjj048RlUeev5OVc2QH3apY5Gdeer7q08JdQnT8gM2k3Zo677a8XffHIIi7214EQGei3eTf33Yly7licdLcjQZl9JMZc-_NFFwE3GePZMMQfPnyy_p3Ek80evT48CbrDeRaAHGAn_YkImFFV6FKH93DgXY_bk9p2nDJN0iuOrpV8iNN58J9g6LeQYO7-9PX5Vd18U2Ziyvy8fAaV7F0293VgN3nYZFf2NsbBiL6N4SFFgMjeBWIKrWDPmlf5gte9CG_J71aMaa2kAm5Chq9ePkkAx76IgnzauF5JwBbXaVn4VNDacfbHtqIoXn930RqZKozo75JVxda0_eTtnuU3rsqm1UMogGVUC2s6q3iXUQHr7MqZsm7v29YwejcOnl6RAxs0qOOBa2Dx9rYDA3lhR482hpfZVRqY-RPneESSHYn7Cy9Ei7IfFMr7HuEnKJ-xMEKs-XBwTdhS4wxBBUV_osP6IMT1f-CcAt0H4Az_unPT1-fPu-GxWOcUvz1_V_os1LrAJDzXoftKaQvoI8twIbweugRejFTXgczAFAnwMThm5f2P-buwIH7pK-yvwTtZs3sexbRh9TNBZM0drmsukoox-vQr5q1fsaDiCH-NkQ8VnXxYFgHwdg6q-exjwygEzTcTUI7pC-y0CVpz_W4)

#### 4. Luồng Đổi thưởng bằng điểm (UC09)

![Luồng Đổi thưởng bằng điểm UC09](http://www.plantuml.com/plantuml/png/VPBFIW9H5CRtynHd5VeAIgMeK2GIEJt0DADpBysSAUSQj6xX8W9TH8iMZKX8Y13e9k_oMkzntwGprpyr1gtkl_Dz_BvpRYaMJaTqmy0o6wjGzaAEu38j7n0kcHgw39XAq2zQXGRJYrU4PL_BHmus-thNyiMy76dP0q6Y0VeWz6B4mNEGGMpqePeXqECJY8wM8rWEZC5rLoMfbcjvXqPz3nvtWiZ_NG5xT4fBGc2qz0XEpS3LSkoi-Oexr6jLJ3GGf8mZmsYhAH49CaP_Q-dgnHZ1Kn__1-Hjo2txVoWEzc5pkp9QSH_cAKqB8LTHixm5K3XkD6jdDWIKaDf1ODDCmXkLH51fLkl5jFZyj5gsQwPLaosdK5DI1fzljvdecz6GhAwI448jdpEjtosBHNHbjOAu1RaJfkRe6xxC28IvXmirZ5Bv75VZ66zOVcpGusshH0lzdsy)

---

### 3.4.2. Biểu đồ tuần tự (Sequence Diagram)

#### 1. Đăng nhập (UC01)

![Biểu đồ tuần tự UC01](http://www.plantuml.com/plantuml/png/bLInRjim4Dtv5LSDWGpKIqO3CPB6HGmuTGF4w4vBZ4XOEWeYbQ1Zcw53bsRgs1P1Xm8DkXGe80qTv1_Xd_GaDwuToGYwIEBntRjtZpmT6iipcoSnuu7L6Ho5YKAMKam5AkLeuKHnFL82HnoZgUJfrYPjv1EvQtSYx5Ihu_FHwLRO6-_3Y5i-vqPum0sCzpih7ak5ah564lI7BIKmWAbqnLqAkH4PyaGyXPGRSwMpyD6ihmXO5IHc-M7vTde2qx4hlfzvh0KdYbQNn45sAOrd8hlGMI92_wXPFxyKQ7kidR23PQ9SUPs0pJZ4Eb81-FUjmC41UBnkmeEzlNLV6-4Uux65Swx0okedHc2fuUNDyfe-iV65GKVHVd_OQKeDcacDqJEkVQ-XhJuhM4ZjYbk4-lKhW8HuBGLTyIr_GXAWJTKKMDa_W77T8992Lxw7e6QICA--Q8XT-L4n4Hi1intoQLKL67LtCTouNLHdeKGoo_WgvjUwDhO9J5Tk02zS-GaMhlXj0LrvGysjxQS4WZJIjtWd6-YNamUqDRhhL6sMYrsgEvmtmhvIHirZuLzmCgJt7xcXCgasmdyqfnacIa1njHtpDmznXRhtwvJ6v3M4hlm1CJdrBgUnDNAkEStNFxyULfXbTCnmwSetOCYtlwdj8ZklIKGuQeoo409PtH7ONnUcIK0HzvZ0a1tIWtvTVm0)

#### 2. Bắt đầu phiên chơi (UC04)

![Biểu đồ tuần tự UC04](http://www.plantuml.com/plantuml/png/ZLHDQzj04BtlhnXqQEnY-v2ZQKrSMqMbSHmiXHx5H1BU9TBAIBi5dtiifSa_i0-Xq4ifv5BxgD3_eN_IaPgu-cWW1rjuvhqtxyskV98gJ9IEGeQUYXCO-v6GR4KruOaLIWNdA8DmXcj7I5vhlEVeSGlbSZgUrnbS1DBMKzrkFUWqouPr35DKU8bfO02cO1qpLbg1_gZc08Om2OLt1H-pJGmU9Zv4sNODXfFa-nkvD5WDJUogJMA7r44rmKwNrJg491D3iCrJS-B0ItYxcC_Wge2ud32Cklt-gAMt258TAZjGu2CDJBEjnubMFdYs8M9zJ27H27GgKlNgW1er4XI4lwiOWYNo_IS9YkVxp-0B3ECb66_otGy5zpVvxfk65HVPTsaylJoYVf4rW_029Cztj_8WTLr8jJTPFrSIkqGrHLsMEvLcbpMGZxjzTsQR2uSUphmm6aWtrQvF8EX4w4Quxe6YOhdq7Yn5xD84rqVTWy5Wq3qSGSk8Gwb-UhGNGH_AnC7BxcZrJH__MRvATD1A-k-Q4iB3rHb7vIHqE0v7GLvdXLNZ_fegu6U_wDfrMONpcFVYV3fsJA1WhYgKRTEXUKHo5Ud0QpYgI1p11yjS56Xgb2CEUMjsdifQdV_Sd0rCYD4i5W-_wGLvvaKiNq2F_zuA-jRhifCMkQ9ieulRjF_AJWBfqx_C7m)

#### 3. Gọi đồ ăn/uống (UC06)

![Biểu đồ tuần tự UC06](http://www.plantuml.com/plantuml/png/ZLGnRzf05DxzAxvwg4W531bHYuA0XaeG9A1fD-iuB7ogVOVmUM3kM4LgrA53bg8gIwL8gTe93nqSyJ_kd_ITCIdGHEratxlttlUzxtlsIQpPN2THw32krHoDIIIaCwEOu6B6f4PBi51DJmLJFNLmenhojPBJLgD_49STzdVOxHoZnJGRizXtmM9qZXqdnqEblW-36fgXuEyWinKLdfhqGg2N_P0kz3px9Z79ip4B19sS_LhRx0ypQXMoHND3gNJax5vH9d6gOTZkjfiZFCFBGR-7YVAOz1BAmPjEUz06Lz8Bg1el23tn7QgfLEg7A0C_Ja8zz3LqODBl77UNwwN4tNk9nAINSheFdJSucBahOlrycrK_7CjMREIgeIDCIeqjqWUCpUehiPim6I0owRM5DkcdNUX7PHvPbJcrkP3WsP8Z8fNBa85PtSmGKpN2zQr9BS3JF7mjuRwrcMSsXmRyI6Ew3tcmtGA2qKmLvrBnj4xS9soDU7KsR0z6z1ZroOP2zLB4F3BQ9rhq5h556PhqyQQ2bL6jLezs3Dc1x1BLXQsuRwJ95iH21awer0pd9hqILh3F8f_Q6cOdvOKU3l0FYZmGdXQ-zlQuRW_b3JWjKncnyaAxKGLXNquU8Rs-JSZLx0jPoREVr94me8LP_T9xWZsqFlbo_k_I48XjFywMQlk1vIBiWZzndHEYId-7tm)

#### 4. Kết thúc phiên & Thanh toán (UC05)

![Biểu đồ tuần tự UC05](http://www.plantuml.com/plantuml/png/ZLMnRjim4Dtr5GSliHgxGyO02M9ORWMuJeAOIRi9Z2I8X2NIiAWK7ekE7TeCtMCKMGfaQfVOGmS5_G_tItgauaIIdI2BRP37T-_UlJiVf8fEL9R45lMLd40dIBYmndZ6VJwcGe73QImZmgXWHDBgtJ7ZeHXcJYOYftDKVIQ5IzUF-upwRDF5W4u95wn-t71tmA6AdjCqR01DmTsnB4CIsljrRh0BtPZx8_3f980adqsXSVSDaq6GtugeOTNZ4Q9Q0YB4T6hE77pNPSYAXHB9DHEAdxPLYqS4PBWBmzwxNfV0AtXpSZI0iGxoqimBC0pUkxsJ7YJKI-WKzi00GUVG0JN179vYbEFnLi7KPylvRBfb8MgxlR-1tqcOPh4QXafdoNyAI9UB6mYMYsiHhR7J2EiYbQ8sIVBOka8E8aL4j2B5EIfX06oh55ZJONWwQ2fyvaMSseKcWHGUesKvZ4f648FtK78bFraklkCZnPUBJqAN-SLdS87LGbDfJcTL6fiBBY8trheMI_8RmLRvZ8Vs89LlkSJcOZK3oV7twrLOmMxFPD1jtNuobsPM67SNN4J_B40nB0SxpdMw-Hy5BFz5uUxoxqpVKOqlyvbupk-7Q9Th0IAVuwpy-teBVGD4M3xtN-RtKQW8otnplcfj2zHzpvyq_Ecnqo6zcjE7FO8QIGynqTQPZy88-R5f5tYUAlgzmcwj3K3-0qQ6lzBydna2hEyAvV7ptsXX6kkzai_G6FeuWZY_qWWwuaAFY0Lhoc4PvLMaFJB9DAapsaLws6A7XmdgqNTR9SB622q8yCx9fAz3RKIlGDKK6kcxmlbQdbIYLCPI-hjT5gEO5tclaFcvNSw85-PhkvB_OPxC9FLTfBEIhLPXHRHG19liyRY6yO5FnuUCAgAxX56ys84jQ939Sd692zMsIl6rkd7POSCndwwwskZxvrjLwWZrqF0AZGhl5yrmJ1ESqw4CpEQsdnz6X4TWECzdiZnrAmUPjArY4_Wicvh9JNKxa_mgWm_um3f0DV4_z3y)

#### 5. Nạp tiền khách hàng (UC08)

![Biểu đồ tuần tự UC08](http://www.plantuml.com/plantuml/png/VL8zJy9G5DtxAmuT30Q8oaXY0w749YHej2eRUPJQzo9zRUZ3tJWQ1qO74nXSJDYSOApnV_IVU5lgHp7QvVNTyt5FxsqpKcoYflvOOuuA9cYDV26ra6h24I6J2gPWuy3hiT0MRX7fSkPmYqdFR1qN0DsgmsIA3Ldawc0HhBgcPSwe6KL3DD3coVf1ufh72uU3ntFfOGUI9wjd2RsNh1OXR96iRwMk5RMrcc5i4fEDHPGx2KLkzxWA9Zva9bMPzE_sFMgq314bwrdEpONV6VgKBBiiS-nN4ACm37AL6-I2jGFf7Od05AvVzbdNgY8AKcHNAoZ8WORMmFc9sR8xD0p21fn0d7LiaWn6KnpcPoMtg4AH-S0J0K5VhnMKD_3UmJuzkrMCg3SnicCRnQNLEUt0PqH8uscK8XlhrlVOwLuudFXLUbl9K_RsKDLawlwtBB8Z8mpZHV2vudpswaUZ4jh9-f5uL7pnqOT7d2Su_7qXS1FFgV8sIzVkbtIjwSeH_SSV)

#### 6. Đổi thưởng bằng điểm (UC09)

![Biểu đồ tuần tự UC09](http://www.plantuml.com/plantuml/png/bLG_Qzj05D_rAPurb0Ji9MEW9cvbK75eIgp-sSJBIUYEI7UkTIfaxj2X59AXTCZG11D2AOL22eLebDlleM_ITv9ZHxOWTB1atxtt-tVll9TgdEeiYIraMarX42H2MXDQ4qnCK6fm1COgSfJmUAParDWRSMJSHHavWuF6nhvWV9mjczQtRNS77DHuX6be0wRWxbXMHGsz_ZeZxC9JNkQV91ppuf9nuCK5hJu2oSlRAmds_BJCFmlm-D-VPVvLHhQrZj3hzVkrDG9pHPc_bw09yoC4LINCpynY0fnU7oImueRm0vGSaZA_Ol0sAov0bxU_AO27TJAI2HDITothpdVq-G-rRT2ZMg2UubAiBdvG6l6VRoV0olmQ6_bO66kOdsvKKiqlQGnJXmKRH8sak0Ybnsdv2QejUtIliqL3nzwsmZWDsqWfbja4OigMA6Eb9l2gpC-52VSBFOkRHKxpCnFAZ06hWgJY5eNzbkaYiH4NzVfMWYEt2mawQY2xaAfzKzIbemuJwjckK5iW29Z6SXUUFHyF3prwUGSGKvcVPdwWXAzhVeBtZytS-nmDBFqCb8yLan-lg4nF5sI49-PRpKhuLS2j1ZnpRfo6XrAcwb06RUVJRrmwQjFJp26wXMjju8nHN5XjLDzPVFd26NX3MDc0yT2hLCFZ-jLh8i5hTtWu9A-qRuYibPSrEMikclIcVdEqoK7uq2XwzV0T5PTgUONgItcFhqEpLym8hyo_mvkx6UtObiboZnxqF_SF)

---

### 3.4.3. Biểu đồ lớp phân tích (Class Diagram)

![Biểu đồ lớp phân tích](http://www.plantuml.com/plantuml/png/dLMxRjmm4Epr5SHLvnXNf3K2mmy1iU4xmy0fxjSI8IuWaOv47d08KgHAbI95Ydn0IXT1Egj8aVo8_YGhKIzAbXFaAdAMkvoTMUfESWsPDcdYXGdaERkEZKBVo9YzynZzLYWrIy6kjpf3YcWkVP3njO1x1qz1Nmam3hO3339LGuSnrnjO7Z0gsG51NuiYDcbmkYuu3TpMLNlylTVGty0-G2cU9-_i4mLeWKYPkuIp666Zy3KgDoqZbe5eppPe2EQ2hhEy4Y1ECYPMTqeb7EmzXn4bLe8-XP-12Z8SHnAulRmPW5rt94OeBk1lvZZxFD9EUxdoZGDeujnrMnMCaATsvuFcJCQaAQYUnOr0BhTcRaZikZ72GfTIyvXd17STZ3dNWS6jCQepq7uxJIrsHHCZJ3Xf8C1HswvVjTZd0lPZgJLnt2fgdmAsN8zNDPVTD7JnJWTVdSetwj_Cw6VBYTyBaEjk05lnVTK03LKMAbbhDhXQper1htNjqwmdZlimK5cBgb-WPzaCpXfp_UfS8EcXcukF6GagjcJPHhLA2fxIPkaUF60xXL6lwPeUhdqZDTurv6RDZrLpm8LEvLZq7glEv6fjL9iqSi8wJJPOkwSkhp4KDEmAQUvcpBRhqMZ_5xd917Gli4A8K_jzMxnSiDNgc2rUB9ptUCHYWKoAyl51UltdOlvuNXOFB2gBRqHX61bcjAvGyRBuX2pzzKDwBJWyE7BnY8L4HcFv-5DxtMjvDa4BKnPVGxOhYmyp_CU6L4bbyJra0ilYetpwecdIMxDdlp-NnHVqJhYCw1_m3m)

---

## 3.5. Kết luận chương
Chương 3 đã tiến hành khảo sát chi tiết hiện trạng quy trình nghiệp vụ thủ công của quán internet, phân tích và đưa ra giải pháp số hóa toàn diện thông qua hệ thống **CyberNet**. 
Thông qua mô hình ca sử dụng (Use Case Model), 13 chức năng và mối tương tác của Admin đã được phân rã rõ ràng qua các biểu đồ phân hệ và bảng đặc tả chi tiết.
Đồng thời, hành vi động của hệ thống được làm rõ qua các biểu đồ hoạt động (Activity Diagrams) và biểu đồ tuần tự (Sequence Diagrams) cho 6 nghiệp vụ cốt lõi, cùng biểu đồ cấu trúc tĩnh Class Diagram. Đây là cơ sở dữ liệu phân tích vững chắc phục vụ cho việc thiết kế kiến trúc phần mềm, cơ sở dữ liệu và xây dựng giao diện chi tiết ở Chương tiếp theo.


# CHƯƠNG 4. THIẾT KẾ HỆ THỐNG

> 📌 **Người thực hiện:**
> - **Thành viên 4:** Phần 4.1 – 4.3
> - **Thành viên 1:** Phần 4.4 – 4.6

## 4.1. Kiến trúc tổng thể

> 📌 **Người thực hiện: Thành viên 4**

### Mô hình kiến trúc MVC + DAO

Hệ thống CyberNet áp dụng kiến trúc **MVC kết hợp DAO Pattern**, tổ chức theo 4 package chính:

```
com.mycompany.quanlyquaninternet/
├── QuanLyQuanInternet.java          ← Main class (Entry Point)
├── entity/                          ← MODEL (7 entity class)
│   ├── NguoiDung.java
│   ├── MayTinh.java
│   ├── KhachHang.java
│   ├── PhienSuDung.java
│   ├── DoAnUong.java
│   ├── DonHang.java
│   └── ChiTietDonHang.java
├── dao/                             ← DATA ACCESS (7 DAO class)
│   ├── KetNoiCSDL.java              ← Singleton DB Connection
│   ├── MayTinhDAO.java
│   ├── KhachHangDAO.java
│   ├── PhienSuDungDAO.java
│   ├── DoAnUongDAO.java
│   ├── DonHangDAO.java
│   └── LichSuDoiThuongDAO.java
├── controller/                      ← CONTROLLER
│   └── DieuKhienDangNhap.java
└── view/                            ← VIEW (8 class giao diện)
    ├── GiaoDienDangNhap.java
    ├── GiaoDienChinh.java
    ├── PanelTongQuan.java
    ├── PanelMayTinh.java
    ├── PanelKhachHang.java
    ├── PanelPhienSuDung.java
    ├── PanelDoAnUong.java
    └── PanelThongKe.java
```

### Sơ đồ kiến trúc hệ thống

```mermaid
flowchart TB
    subgraph VIEW["VIEW Layer (Java Swing + FlatLaf Dark)"]
        GDN[GiaoDienDangNhap]
        GDC[GiaoDienChinh]
        PT[PanelTongQuan]
        PMT[PanelMayTinh]
        PKH[PanelKhachHang]
        PPS[PanelPhienSuDung]
        PDA[PanelDoAnUong]
        PTK[PanelThongKe]
    end

    subgraph CONTROLLER["CONTROLLER Layer"]
        DK[DieuKhienDangNhap]
    end

    subgraph DAO["DAO Layer (Data Access Object)"]
        KN[KetNoiCSDL - Singleton]
        MTDAO[MayTinhDAO]
        KHDAO[KhachHangDAO]
        PSDAO[PhienSuDungDAO]
        DADAO[DoAnUongDAO]
        DHDAO[DonHangDAO]
        LSDAO[LichSuDoiThuongDAO]
    end

    subgraph DB["DATABASE (H2 Embedded)"]
        H2[(H2 Database\nquanlyquaninternet.mv.db)]
    end

    GDN --> DK
    DK --> GDC
    GDC --> PT & PMT & PKH & PPS & PDA & PTK
    PMT --> MTDAO & PSDAO & KHDAO & DADAO & DHDAO
    PKH --> KHDAO & DADAO & LSDAO
    PT --> MTDAO & PSDAO & DHDAO
    PTK --> PSDAO
    MTDAO & KHDAO & PSDAO & DADAO & DHDAO & LSDAO --> KN
    KN --> H2
```

## 4.2. Thiết kế cơ sở dữ liệu

> 📌 **Người thực hiện: Thành viên 4**

### ERD (Entity Relationship Diagram)

```mermaid
erDiagram
    NGUOI_DUNG {
        int ma PK
        varchar ten_dang_nhap UK
        varchar mat_khau
        varchar vai_tro
    }

    KHACH_HANG {
        int ma PK
        varchar ten
        varchar sdt
        double so_du
        double tong_gio
        int diem
        datetime ngay_tao
    }

    MAY_TINH {
        int ma PK
        varchar ten
        varchar loai
        double gia_moi_gio
        varchar trang_thai
        varchar cau_hinh
    }

    PHIEN_SU_DUNG {
        int ma PK
        int ma_may_tinh FK
        int ma_khach_hang FK
        varchar ten_khach
        datetime gio_bat_dau
        datetime gio_ket_thuc
        double tong_tien
        varchar trang_thai
    }

    DO_AN_UONG {
        int ma PK
        varchar ten
        double gia
        varchar phan_loai
        int diem_doi
        boolean con_hang
    }

    DON_HANG {
        int ma PK
        int ma_phien FK
        int ma_may_tinh FK
        double tong_gia
        datetime thoi_gian_dat
    }

    CHI_TIET_DON_HANG {
        int ma PK
        int ma_don_hang FK
        int ma_do_an FK
        varchar ten_do_an
        int so_luong
        double gia
    }

    LICH_SU_DOI_THUONG {
        int ma PK
        int ma_khach_hang FK
        int ma_do_an FK
        varchar ten_do_an
        int diem_da_dung
        datetime ngay_doi
    }

    MAY_TINH ||--o{ PHIEN_SU_DUNG : "có nhiều phiên"
    KHACH_HANG ||--o{ PHIEN_SU_DUNG : "sử dụng"
    PHIEN_SU_DUNG ||--o{ DON_HANG : "có đơn hàng"
    MAY_TINH ||--o{ DON_HANG : "gọi món tại máy"
    DON_HANG ||--|{ CHI_TIET_DON_HANG : "chứa"
    DO_AN_UONG ||--o{ CHI_TIET_DON_HANG : "được gọi"
    KHACH_HANG ||--o{ LICH_SU_DOI_THUONG : "đổi thưởng"
    DO_AN_UONG ||--o{ LICH_SU_DOI_THUONG : "phần thưởng"
```

### Các bảng dữ liệu chính

#### Bảng: nguoi_dung (Tài khoản đăng nhập)

| STT | Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|-----|---------|---------------|-----------|-------|
| 1 | ma | INT | PK, AUTO_INCREMENT | Mã người dùng |
| 2 | ten_dang_nhap | VARCHAR(50) | UNIQUE, NOT NULL | Tên đăng nhập |
| 3 | mat_khau | VARCHAR(100) | NOT NULL | Mật khẩu |
| 4 | vai_tro | VARCHAR(20) | DEFAULT 'staff' | Vai trò (admin/staff) |

#### Bảng: may_tinh (Máy tính)

| STT | Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|-----|---------|---------------|-----------|-------|
| 1 | ma | INT | PK, AUTO_INCREMENT | Mã máy |
| 2 | ten | VARCHAR(50) | NOT NULL | Tên máy (Máy 01 – Máy 20) |
| 3 | loai | VARCHAR(20) | DEFAULT 'Thường' | Loại: Thường / VIP |
| 4 | gia_moi_gio | DOUBLE | DEFAULT 10000 | Giá thuê mỗi giờ (VNĐ) |
| 5 | trang_thai | VARCHAR(20) | DEFAULT 'Trống' | Trống / Đang dùng / Bảo trì |
| 6 | cau_hinh | VARCHAR(200) | - | Cấu hình phần cứng |

#### Bảng: khach_hang (Khách hàng)

| STT | Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|-----|---------|---------------|-----------|-------|
| 1 | ma | INT | PK, AUTO_INCREMENT | Mã khách |
| 2 | ten | VARCHAR(100) | NOT NULL | Tên khách hàng |
| 3 | sdt | VARCHAR(20) | - | Số điện thoại |
| 4 | so_du | DOUBLE | DEFAULT 0 | Số dư tài khoản (VNĐ) |
| 5 | tong_gio | DOUBLE | DEFAULT 0 | Tổng giờ đã chơi |
| 6 | diem | INT | DEFAULT 0 | Điểm tích lũy |
| 7 | ngay_tao | DATETIME | DEFAULT NOW() | Ngày tạo tài khoản |

#### Bảng: phien_su_dung (Phiên sử dụng máy)

| STT | Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|-----|---------|---------------|-----------|-------|
| 1 | ma | INT | PK, AUTO_INCREMENT | Mã phiên |
| 2 | ma_may_tinh | INT | FK → may_tinh(ma), NOT NULL | Máy tính sử dụng |
| 3 | ma_khach_hang | INT | FK → khach_hang(ma), NULL | Mã khách (null = vãng lai) |
| 4 | ten_khach | VARCHAR(100) | - | Tên khách hiển thị |
| 5 | gio_bat_dau | DATETIME | NOT NULL | Thời gian bắt đầu |
| 6 | gio_ket_thuc | DATETIME | NULL | Thời gian kết thúc |
| 7 | tong_tien | DOUBLE | DEFAULT 0 | Tổng tiền phiên |
| 8 | trang_thai | VARCHAR(20) | DEFAULT 'Đang chạy' | Đang chạy / Kết thúc |

#### Bảng: do_an_uong (Menu đồ ăn/uống)

| STT | Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|-----|---------|---------------|-----------|-------|
| 1 | ma | INT | PK, AUTO_INCREMENT | Mã món |
| 2 | ten | VARCHAR(100) | NOT NULL | Tên món |
| 3 | gia | DOUBLE | NOT NULL | Giá bán (VNĐ) |
| 4 | phan_loai | VARCHAR(50) | DEFAULT 'Nước uống' | Đồ ăn / Nước uống |
| 5 | diem_doi | INT | DEFAULT 0 | Số điểm để đổi thưởng |
| 6 | con_hang | BOOLEAN | DEFAULT TRUE | Còn hàng hay hết |

#### Bảng: don_hang (Đơn gọi món)

| STT | Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|-----|---------|---------------|-----------|-------|
| 1 | ma | INT | PK, AUTO_INCREMENT | Mã đơn |
| 2 | ma_phien | INT | FK → phien_su_dung(ma) | Phiên liên kết |
| 3 | ma_may_tinh | INT | FK → may_tinh(ma), NOT NULL | Máy đặt món |
| 4 | tong_gia | DOUBLE | DEFAULT 0 | Tổng giá đơn hàng |
| 5 | thoi_gian_dat | DATETIME | DEFAULT NOW() | Thời gian đặt |

#### Bảng: chi_tiet_don_hang (Chi tiết đơn hàng)

| STT | Tên cột | Kiểu dữ liệu | Ràng buộc | Mô tả |
|-----|---------|---------------|-----------|-------|
| 1 | ma | INT | PK, AUTO_INCREMENT | Mã chi tiết |
| 2 | ma_don_hang | INT | FK → don_hang(ma), ON DELETE CASCADE | Đơn hàng |
| 3 | ma_do_an | INT | FK → do_an_uong(ma) | Món ăn/uống |
| 4 | ten_do_an | VARCHAR(100) | - | Tên món (lưu thừa) |
| 5 | so_luong | INT | DEFAULT 1 | Số lượng |
| 6 | gia | DOUBLE | NOT NULL | Đơn giá tại thời điểm đặt |

## 4.3. Thiết kế cấu trúc phần mềm

> 📌 **Người thực hiện: Thành viên 4**

### Package Diagram

```mermaid
graph TB
    subgraph Main["QuanLyQuanInternet.java"]
        M[main - Entry Point]
    end

    subgraph Entity["Package: entity (7 classes)"]
        E1[NguoiDung]
        E2[MayTinh]
        E3[KhachHang]
        E4[PhienSuDung]
        E5[DoAnUong]
        E6[DonHang]
        E7[ChiTietDonHang]
    end

    subgraph DAO["Package: dao (7 classes)"]
        D0[KetNoiCSDL - Singleton]
        D1[MayTinhDAO]
        D2[KhachHangDAO]
        D3[PhienSuDungDAO]
        D4[DoAnUongDAO]
        D5[DonHangDAO]
        D6[LichSuDoiThuongDAO]
    end

    subgraph Controller["Package: controller (1 class)"]
        C1[DieuKhienDangNhap]
    end

    subgraph View["Package: view (8 classes)"]
        V1[GiaoDienDangNhap]
        V2[GiaoDienChinh]
        V3[PanelTongQuan]
        V4[PanelMayTinh]
        V5[PanelKhachHang]
        V6[PanelPhienSuDung]
        V7[PanelDoAnUong]
        V8[PanelThongKe]
    end

    M --> V1
    M --> C1
    C1 --> V1
    C1 --> V2
    V2 --> V3 & V4 & V5 & V6 & V7 & V8
    View --> DAO
    DAO --> Entity
    DAO --> D0
```

### Tổng quan class theo package

| Package | Số class | Vai trò |
|---------|---------|---------|
| `entity` | 7 | Lớp thực thể, ánh xạ bảng CSDL |
| `dao` | 7 | Truy xuất dữ liệu (CRUD + logic truy vấn) |
| `controller` | 1 | Xử lý logic đăng nhập |
| `view` | 8 | Giao diện người dùng (Swing) |
| **Tổng** | **24 class** | |

## 4.4. Thiết kế giao diện người dùng

> 📌 **Người thực hiện: Thành viên 1 (Nhóm trưởng)**

### Bảng màu giao diện (Dark Mode)

| Thành phần | Mã màu | Mô tả |
|-----------|--------|-------|
| Nền Sidebar | `rgb(17, 14, 45)` | Tím đậm rất tối |
| Nền nội dung | `rgb(24, 21, 55)` | Tím đậm tối |
| Thẻ (Card) | `rgb(30, 27, 65)` | Tím đậm nhẹ hơn |
| Màu nhấn (Accent) | `rgb(99, 102, 241)` | Indigo sáng |
| Chữ chính | `rgb(255, 255, 255)` | Trắng |
| Chữ phụ | `rgb(160, 160, 190)` | Xám tím nhạt |
| Xanh (Trống) | `rgb(16, 185, 129)` | Emerald |
| Đỏ (Đang dùng) | `rgb(239, 68, 68)` | Red |
| Vàng (VIP/Bảo trì) | `rgb(245, 158, 11)` | Amber |
| Tím (Nạp tiền) | `rgb(168, 85, 247)` | Purple |

### Màn hình 1: Đăng nhập (GiaoDienDangNhap)

**Kích thước:** 500×600px, không thay đổi kích thước.

**Bố cục:**
- Nền gradient từ `rgb(15,12,41)` → `rgb(48,43,99)` với hiệu ứng hình tròn mờ.
- Card trung tâm `380×450px` với bo góc 24px, viền indigo mờ.
- Icon game pad 🎮, tiêu đề "CYBER NET", phụ đề "Hệ thống quản lý quán internet".
- 2 trường nhập: Tên đăng nhập, Mật khẩu (bo góc, viền đổi màu khi focus).
- Nút ĐĂNG NHẬP gradient indigo → purple.
- Label thông báo lỗi (màu đỏ).

### Màn hình 2: Giao diện chính (GiaoDienChinh)

**Kích thước:** 1280×800px, tối thiểu 1100×700px.

**Bố cục:**
- **Sidebar trái (220px):** Logo 🎮 CYBER NET, 6 mục menu (Dashboard, Máy Tính, Khách Hàng, Phiên SD, Dịch Vụ, Thống Kê) với icon emoji, nút Đăng Xuất. Menu active có thanh indigo bên trái.
- **Header (55px):** Tên trang hiện tại, đồng hồ realtime HH:mm:ss, icon Admin.
- **Nội dung (CardLayout):** 6 panel chuyển đổi bằng click menu.

### Màn hình 3: Dashboard (PanelTongQuan)

**Bố cục:**
- **Hàng trên (4 card):** Máy Trống (xanh), Đang Dùng (đỏ), Doanh Thu Máy (xanh dương), Doanh Thu DV (tím) — mỗi card có icon, label và giá trị lớn với thanh màu bên trái.
- **Bảng phiên hoạt động:** Hiển thị realtime: mã, tên máy, khách hàng, giờ bắt đầu, thời gian đã dùng, chi phí hiện tại.

### Màn hình 4: Quản lý Máy Tính (PanelMayTinh)

**Bố cục:**
- **Thanh trên:** 4 nút lọc (Tất cả, Trống, Đang dùng, Bảo trì) + nút Làm mới + Thêm Máy.
- **Lưới máy (GridLayout 5 cột):** Mỗi máy là 1 card (180×140px) với viền màu theo trạng thái, icon 🖥️, tên máy, loại (VIP = vàng), trạng thái.
- **Click máy:** Trống → dialog bắt đầu phiên; Đang dùng → dialog thông tin phiên + gọi món; Bảo trì → đặt lại trống.

### Màn hình 5: Quản lý Khách Hàng (PanelKhachHang)

**Bố cục:**
- **Thanh trên:** Ô tìm kiếm + nút Tìm/Tất cả + nút Thêm Khách.
- **Bảng:** Mã, Tên, SĐT, Số dư, Tổng giờ, Điểm ⭐.
- **Thanh dưới:** 4 nút: 🎁 Đổi Thưởng (cam), 💰 Nạp Tiền (tím), ✏️ Sửa (indigo), 🗑 Xóa (đỏ).

### Màn hình 6: Thống Kê (PanelThongKe)

**Bố cục:**
- **Thanh trên:** 2 JDateChooser (Từ ngày, Đến ngày) + nút Xem + nút nhanh (7 ngày, 30 ngày, Hôm nay).
- **3 card tóm tắt:** Tổng Doanh Thu (xanh), Tổng Phiên (indigo), TB/Ngày (tím).
- **Biểu đồ cột:** Vẽ bằng Graphics2D với gradient indigo→purple, hiển thị giá trị trên mỗi cột, ngày dưới trục x.
- **Bảng dưới:** Ngày, Doanh thu, Số phiên.

## 4.5. Thiết kế hành vi hệ thống

> 📌 **Người thực hiện: Thành viên 1 (Nhóm trưởng)**

### Sequence Diagram — Kết thúc phiên & tính tiền

```mermaid
sequenceDiagram
    participant Admin
    participant PanelMayTinh
    participant PhienSuDungDAO
    participant DonHangDAO
    participant KhachHangDAO
    participant MayTinhDAO

    Admin->>PanelMayTinh: Click máy "Đang dùng"
    PanelMayTinh->>PhienSuDungDAO: layPhienDangChayTheoMay(maMay)
    PhienSuDungDAO-->>PanelMayTinh: PhienSuDung phien
    PanelMayTinh->>PanelMayTinh: tinhSoGio(), tinhTien()
    PanelMayTinh->>DonHangDAO: layTongTienDonHang(maPhien)
    DonHangDAO-->>PanelMayTinh: tienDV
    PanelMayTinh-->>Admin: Dialog: tiền máy + tiền DV = TỔNG
    Admin->>PanelMayTinh: Nhấn "⏹ Kết Thúc"
    PanelMayTinh->>PhienSuDungDAO: ketThucPhien(maPhien, tongTien)
    PanelMayTinh->>MayTinhDAO: capNhatTrangThai(maMay, "Trống")
    alt Khách thành viên
        PanelMayTinh->>KhachHangDAO: truTien(maKH, tongTien)
        PanelMayTinh->>KhachHangDAO: congGio(maKH, soGio)
        PanelMayTinh->>KhachHangDAO: congDiem(maKH, diem)
    end
    PanelMayTinh-->>Admin: Thông báo kết quả
```

### State Machine Diagram — Trạng thái máy tính

```mermaid
stateDiagram-v2
    [*] --> Trống: Khởi tạo
    Trống --> DangDung: Bắt đầu phiên
    DangDung --> Trống: Kết thúc phiên
    DangDung --> BaoTri: Chuyển bảo trì
    BaoTri --> Trống: Hoàn tất bảo trì

    state Trống {
        [*] --> SanSang
        SanSang: Máy sẵn sàng\nHiển thị viền xanh
    }
    state DangDung {
        [*] --> TinhGio
        TinhGio: Đang tính giờ\nHiển thị viền đỏ
    }
    state BaoTri {
        [*] --> DangSua
        DangSua: Đang sửa chữa\nHiển thị viền vàng
    }
```

## 4.6. Kết luận chương

Chương 4 đã thiết kế hoàn chỉnh hệ thống CyberNet:

- **Kiến trúc MVC + DAO** với 24 class tổ chức trong 4 package.
- **Cơ sở dữ liệu:** 7 bảng chính + 1 bảng lịch sử đổi thưởng với ERD đầy đủ.
- **Giao diện:** 6 panel chức năng chính với Dark Mode, bảng màu nhất quán.
- **Hành vi hệ thống:** Sequence Diagram chi tiết cho quy trình kết thúc phiên, State Machine cho trạng thái máy tính.

---

<div style="page-break-after: always;"></div>

# CHƯƠNG 5. CÀI ĐẶT VÀ THỬ NGHIỆM

> 📌 **Người thực hiện:**
> - **Thành viên 2:** Phần 5.1, 5.3, 5.4
> - **Thành viên 4:** Phần 5.2

## 5.1. Môi trường triển khai

> 📌 **Người thực hiện: Thành viên 2**

### Phần cứng yêu cầu tối thiểu

| Thành phần | Cấu hình tối thiểu | Cấu hình khuyến nghị |
|-----------|-------------------|---------------------|
| CPU | Intel Core i3 hoặc tương đương | Intel Core i5 trở lên |
| RAM | 4 GB | 8 GB |
| Ổ cứng | 500 MB trống | 1 GB trống (SSD) |
| Màn hình | 1280×800 pixel | 1920×1080 pixel |

### Phần mềm yêu cầu

| STT | Phần mềm | Phiên bản | Mục đích |
|-----|----------|-----------|----------|
| 1 | Java Runtime (JRE/JDK) | 17+ | Chạy ứng dụng Java |
| 2 | Hệ điều hành | Windows 10+ / macOS / Linux | Nền tảng chạy |
| 3 | Apache Maven (dev) | 3.x | Build dự án (chỉ khi phát triển) |
| 4 | Git (dev) | 2.x | Quản lý mã nguồn |

### Cách triển khai

```bash
# Build dự án thành Fat-JAR
mvn clean package

# Chạy ứng dụng
java -jar QuanLyQuanInternet.jar
```

> **Lưu ý:** CSDL H2 embedded tự động tạo file `data/quanlyquaninternet.mv.db` và khởi tạo schema + dữ liệu mẫu khi chạy lần đầu. Không cần cài đặt MySQL.

## 5.2. Cài đặt các chức năng chính

> 📌 **Người thực hiện: Thành viên 4**

### Chức năng 1: Đăng nhập

**Mô tả:** Giao diện đăng nhập với thiết kế glassmorphism, gradient nền tím, card đăng nhập bo góc với hiệu ứng viền. Hỗ trợ nhấn Enter để đăng nhập nhanh.

**Thông tin kỹ thuật:**
- Class: `GiaoDienDangNhap` (226 dòng code)
- Controller: `DieuKhienDangNhap` (62 dòng code)
- Tài khoản mặc định: admin/admin
- Kiểm tra kết nối CSDL trước khi cho phép đăng nhập

### Chức năng 2: Dashboard tổng quan

**Mô tả:** Hiển thị 4 thẻ thống kê realtime (Máy Trống, Đang Dùng, Doanh Thu Máy, Doanh Thu DV) và bảng phiên đang hoạt động với cập nhật thời gian thực.

**Thông tin kỹ thuật:**
- Class: `PanelTongQuan` (172 dòng code)
- Dữ liệu từ: `MayTinhDAO.demTheoTrangThai()`, `PhienSuDungDAO.layDoanhThuHomNay()`, `DonHangDAO.layDoanhThuDVHomNay()`
- Hiển thị thời gian phiên dạng: "Xh Xxp" (giờ/phút)

### Chức năng 3: Quản lý máy tính (Grid View)

**Mô tả:** Hiển thị 20 máy tính dạng lưới 5 cột, mỗi máy là một card với icon 🖥️, viền màu theo trạng thái (xanh=trống, đỏ=đang dùng, vàng=bảo trì). Click để tương tác.

**Thông tin kỹ thuật:**
- Class: `PanelMayTinh` (512 dòng code) — class lớn nhất
- Chức năng: Lọc trạng thái, Thêm máy, Bắt đầu/Kết thúc phiên, Gọi món, Bảo trì
- Popup dialog: 4 dialog (Bắt đầu phiên, Thông tin phiên, Gọi món, Thêm máy)

### Chức năng 4: Quản lý khách hàng

**Mô tả:** Bảng danh sách khách hàng với tìm kiếm, thêm/sửa/xóa, nạp tiền, đổi thưởng. Hiển thị xem trước giờ chơi và điểm khi nạp tiền.

**Thông tin kỹ thuật:**
- Class: `PanelKhachHang` (344 dòng code)
- Tính năng nổi bật: Live preview khi nạp tiền (nhập số tiền → hiển thị số giờ + điểm tương ứng)
- Đổi thưởng: Chọn nhiều phần thưởng cùng lúc, kiểm tra đủ điểm

### Chức năng 5: Gọi đồ ăn/uống

**Mô tả:** Bảng menu với checkbox chọn món, cột số lượng có thể chỉnh sửa. Đơn hàng tự động gắn với phiên sử dụng hiện tại.

**Thông tin kỹ thuật:**
- Tích hợp trong `PanelMayTinh.hienThiDialogGoiMon()`
- Tạo `DonHang` + `ChiTietDonHang` và lưu vào CSDL
- Tiền dịch vụ được cộng vào tổng khi kết thúc phiên

### Chức năng 6: Thống kê doanh thu

**Mô tả:** Chọn khoảng ngày (JDateChooser), xem thống kê doanh thu với biểu đồ cột gradient và bảng chi tiết. Có nút nhanh: 7 ngày, 30 ngày, Hôm nay.

**Thông tin kỹ thuật:**
- Class: `PanelThongKe` (137 dòng code)
- Biểu đồ vẽ bằng `Graphics2D` custom (không dùng thư viện bên ngoài)
- Tính toán: Tổng doanh thu, Tổng phiên, Trung bình/ngày

## 5.3. Kết quả thực nghiệm

> 📌 **Người thực hiện: Thành viên 2**

### Dữ liệu mẫu thử nghiệm

| Loại dữ liệu | Số lượng | Chi tiết |
|--------------|---------|---------|
| Tài khoản admin | 1 | admin/admin |
| Máy tính Thường | 10 | Máy 01–10, 10.000đ/giờ, Core i5 |
| Máy tính VIP | 10 | Máy 11–20, 15.000đ/giờ, Core i7/i9 |
| Khách hàng mẫu | 2 | Nguyễn Văn An, Trần Thị Bình |
| Menu đồ ăn/uống | 3 | Mì tôm (15K), Coca-Cola (12K), Cơm rang (30K) |

### Kết quả kiểm thử chức năng

| STT | Chức năng | Kết quả | Ghi chú |
|-----|-----------|---------|---------|
| 1 | Đăng nhập đúng tài khoản | ✅ Thành công | Chuyển sang Dashboard |
| 2 | Đăng nhập sai tài khoản | ✅ Thành công | Hiển thị thông báo lỗi |
| 3 | Xem Dashboard | ✅ Thành công | 4 thẻ cập nhật đúng |
| 4 | Bắt đầu phiên (khách thành viên) | ✅ Thành công | Máy chuyển đỏ, phiên được tạo |
| 5 | Bắt đầu phiên (khách vãng lai) | ✅ Thành công | Tên mặc định "Khách vãng lai" |
| 6 | Kết thúc phiên | ✅ Thành công | Tính tiền chính xác, trừ tiền, cộng điểm |
| 7 | Gọi món trong phiên | ✅ Thành công | Đơn hàng gắn đúng phiên |
| 8 | Thêm/sửa/xóa khách hàng | ✅ Thành công | CRUD hoạt động tốt |
| 9 | Nạp tiền + live preview | ✅ Thành công | Hiển thị đúng giờ + điểm |
| 10 | Đổi thưởng bằng điểm | ✅ Thành công | Trừ điểm, ghi lịch sử |
| 11 | Lọc máy theo trạng thái | ✅ Thành công | Lọc đúng các trạng thái |
| 12 | Thống kê doanh thu | ✅ Thành công | Biểu đồ + bảng hiển thị đúng |
| 13 | Đồng hồ realtime trên header | ✅ Thành công | Cập nhật mỗi giây |

### Đánh giá hiệu năng

| Tiêu chí | Kết quả |
|----------|---------|
| Thời gian khởi động ứng dụng | ~2 giây (bao gồm khởi tạo CSDL) |
| Thời gian đăng nhập | < 1 giây |
| Thời gian load danh sách 20 máy | < 0.5 giây |
| Dung lượng file JAR | ~5 MB (Fat-JAR bao gồm dependencies) |
| Dung lượng CSDL ban đầu | ~49 KB |
| RAM sử dụng | ~80–120 MB |

## 5.4. Đánh giá hệ thống

> 📌 **Người thực hiện: Thành viên 2 (phối hợp cả nhóm)**

### Ưu điểm

- **Giao diện đẹp, hiện đại:** Dark Mode chuyên nghiệp với FlatLaf, emoji icons, gradient, bo góc — tạo trải nghiệm tốt cho người dùng.
- **Triển khai đơn giản:** Đóng gói 1 file JAR duy nhất, CSDL embedded H2 tự tạo — không cần cài server.
- **Đầy đủ chức năng:** 6 module chính (Dashboard, Máy tính, Khách hàng, Phiên SD, Dịch vụ, Thống kê) phủ hết nghiệp vụ quản lý quán.
- **Tính tiền tự động:** Tính theo thời gian thực, phân biệt giá Thường/VIP.
- **Hệ thống tích điểm:** Tính năng nạp tiền + cộng giờ/điểm + đổi thưởng tạo giá trị cho khách hàng thân thiết.
- **Biểu đồ doanh thu:** Vẽ custom bằng Graphics2D, không phụ thuộc thư viện ngoài.
- **Mã nguồn Việt hóa hoàn toàn:** Tên biến, tên phương thức, comment đều bằng tiếng Việt — dễ đọc, dễ bảo trì.

### Hạn chế

- **Đăng nhập đơn giản:** Chỉ 1 tài khoản admin cứng (admin/admin), chưa hỗ trợ đăng ký, phân quyền staff, mã hóa mật khẩu.
- **Không hỗ trợ mạng:** Chạy standalone trên 1 máy duy nhất, chưa có kiến trúc client-server.
- **Chưa có báo cáo xuất file:** Chưa xuất được báo cáo doanh thu ra PDF/Excel.
- **Chưa có notification:** Không tự động cảnh báo khi phiên quá lâu hoặc hết tiền.
- **Chưa quản lý ca làm việc:** Chưa phân chia ca nhân viên.
- **CSDL embedded:** H2 Database phù hợp quy mô nhỏ, nếu mở rộng cần chuyển sang MySQL/PostgreSQL.

---

<div style="page-break-after: always;"></div>

# KẾT LUẬN VÀ HƯỚNG PHÁT TRIỂN

> 📌 **Người thực hiện: Thành viên 1 (Nhóm trưởng) — Tổng hợp từ cả nhóm**

## I. Kết quả đạt được

- ✅ Xây dựng thành công ứng dụng **"CyberNet — Hệ thống Quản Lý Quán Internet"** bằng Java Swing với giao diện Dark Mode hiện đại.
- ✅ Hoàn thiện **6 module chức năng chính:** Dashboard, Quản lý Máy tính (grid view), Quản lý Khách hàng, Quản lý Phiên sử dụng, Dịch vụ Đồ ăn/uống, Thống kê Doanh thu.
- ✅ Thiết kế **cơ sở dữ liệu 7 bảng** với H2 embedded, tự khởi tạo khi chạy.
- ✅ Áp dụng **kiến trúc MVC + DAO Pattern + Singleton Pattern** trong 24 class Java.
- ✅ Hệ thống **nạp tiền + tích điểm + đổi thưởng** hoàn chỉnh.
- ✅ **Biểu đồ doanh thu custom** bằng Graphics2D.
- ✅ Đóng gói thành **1 file Fat-JAR** duy nhất, dễ triển khai.
- ✅ Kiểm thử thành công **13/13 chức năng** trên dữ liệu mẫu.

## II. Hạn chế

- ⚠️ Chưa hỗ trợ phân quyền nhiều tài khoản (chỉ 1 admin cứng).
- ⚠️ Chưa có kiến trúc client-server, không hỗ trợ quản lý từ xa.
- ⚠️ Chưa xuất báo cáo PDF/Excel.
- ⚠️ Chưa có cảnh báo tự động (hết tiền, phiên quá giờ).

## III. Hướng phát triển tiếp theo

- 🔮 **Phân quyền tài khoản:** Thêm role staff, quản lý ca làm việc, mã hóa mật khẩu (BCrypt).
- 🔮 **Client-Server:** Chuyển sang kiến trúc client-server để quản lý từ nhiều máy tính.
- 🔮 **Xuất báo cáo:** Tích hợp Apache POI (Excel) hoặc iText (PDF) để xuất báo cáo doanh thu.
- 🔮 **Notification:** Thêm cảnh báo âm thanh/popup khi khách sắp hết tiền hoặc phiên quá 4 giờ.
- 🔮 **Chuyển CSDL:** Migrate từ H2 sang MySQL/PostgreSQL cho quy mô lớn hơn.
- 🔮 **Web dashboard:** Xây dựng web dashboard bằng Spring Boot để quản lý từ xa qua trình duyệt.
- 🔮 **Tích hợp thanh toán:** Hỗ trợ quét QR (VNPay/MoMo) để nạp tiền.

---

<div style="page-break-after: always;"></div>

# TÀI LIỆU THAM KHẢO

1. Phạm Hữu Khang, *Lập trình Java cơ bản*, NXB Lao động Xã hội, 2020.
2. Nguyễn Văn Vỵ, *Phân tích và Thiết kế Hệ thống Thông tin*, NXB Giáo dục, 2019.
3. Herbert Schildt, *Java: The Complete Reference*, McGraw Hill, 2021.
4. Oracle, "Java Swing Tutorial", https://docs.oracle.com/javase/tutorial/uiswing/, truy cập 10/2025.
5. FlatLaf Documentation, "FlatLaf - Flat Look and Feel", https://www.formdev.com/flatlaf/, truy cập 10/2025.
6. H2 Database Engine, "H2 Database Documentation", https://www.h2database.com/, truy cập 10/2025.
7. JCalendar, "JCalendar Documentation", https://toedter.com/jcalendar/, truy cập 10/2025.
8. Martin Fowler, *Patterns of Enterprise Application Architecture*, Addison-Wesley, 2002.
9. Apache Maven, "Maven Getting Started Guide", https://maven.apache.org/guides/, truy cập 10/2025.
