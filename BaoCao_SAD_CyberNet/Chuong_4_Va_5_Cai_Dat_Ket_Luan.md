# CHƯƠNG 4: CÀI ĐẶT (IMPLEMENTATION) & CHƯƠNG 5: KẾT LUẬN

> **👤 PHÂN CÔNG THỰC HIỆN CHƯƠNG NÀY:**
> - **Thành viên 4 (Backend, Tester):** Viết mục 4.1 và 4.2. Chịu trách nhiệm quản lý cấu trúc mã nguồn, deploy ứng dụng.
> - **Thành viên 2 (UI/UX, Frontend):** Viết mục 5.1 và 5.2. Chụp ảnh ứng dụng minh họa và đánh giá giao diện.
> - **Thành viên 1 (Trưởng nhóm):** Viết mục 5.3 và 5.4, chuẩn bị slide thuyết trình tổng hợp và phân công bảo vệ đồ án.

---

## 4. CÀI ĐẶT (IMPLEMENTATION)

### 4.1 Lựa chọn công nghệ
**(👤 Người viết: Thành viên 4)**

Dự án CyberNet được xây dựng nhằm mục tiêu dễ dàng triển khai, không yêu cầu cài đặt phần mềm bên thứ ba rườm rà. Các công nghệ được sử dụng bao gồm:
1. **Ngôn ngữ lập trình: Java (phiên bản 17)** - Đảm bảo tính hướng đối tượng, dễ mở rộng và bảo trì.
2. **Giao diện người dùng: Java Swing kết hợp thư viện FlatLaf** - Khắc phục điểm yếu giao diện nhàm chán của Swing mặc định. Cung cấp Dark Theme (giao diện tối) cực kỳ hiện đại, bo góc mềm mại, phù hợp với môi trường sử dụng ban đêm ở quán Internet.
3. **Cơ sở dữ liệu: H2 Database (Embedded Mode)** - Đây là hệ quản trị CSDL siêu nhẹ viết bằng Java. Thay vì bắt người dùng phải cài đặt MySQL/SQL Server, H2 tự động sinh ra một file `.db` tại thư mục chứa phần mềm, giúp tính di động (portable) đạt mức tối đa.
4. **Quản lý dependencies: Maven** - Được sử dụng để tự động tải các thư viện (FlatLaf, H2, JCalendar) và sử dụng `maven-shade-plugin` để đóng gói toàn bộ dự án thành một file duy nhất (Fat-JAR). Người dùng cuối chỉ cần click chạy `ChayApp.bat` là dùng được ngay.

### 4.2 Cấu trúc mã nguồn
**(👤 Người viết: Thành viên 4)**

Mã nguồn được tổ chức theo cấu trúc chuẩn Maven và MVC:
```text
com.mycompany.quanlyquaninternet/
├── QuanLyQuanInternet.java          [Main class - entry point]
├── controller/
│   └── DieuKhienDangNhap.java       [Xử lý logic đăng nhập]
├── dao/
│   ├── KetNoiCSDL.java              [Quản lý connect tới H2 DB]
│   └── ...DAO.java                  [Các class CRUD]
├── entity/
│   ├── KhachHang.java               [Lớp thực thể POJO]
│   └── ...
└── view/
    ├── GiaoDienDangNhap.java        [Frame đăng nhập]
    ├── GiaoDienChinh.java           [Frame chứa menu sidebar]
    ├── PanelTongQuan.java           [Dashboard]
    └── ...Panel.java                [Các chức năng chi tiết]
```

---

## 5. KẾT LUẬN

### 5.1 Kết quả đạt được
**(👤 Người viết: Thành viên 2)**

- **Về mặt công năng:** Ứng dụng đã hoàn thành 100% các chức năng cơ bản đã đề ra. Quản lý luồng tính tiền từ máy tính đến các dịch vụ phát sinh rất chuẩn xác. Tính năng Tự động tính tiền thành Giờ chơi và Điểm đổi thưởng là một điểm nhấn tốt.
- **Về mặt giao diện:** Sử dụng FlatLaf thành công, mang đến cho hệ thống vẻ ngoài chuyên nghiệp, sang trọng, màu sắc và độ tương phản tốt, vượt trội hơn so với các giao diện bài tập lớn thông thường. Các biểu tượng emoji được sử dụng linh hoạt giúp giao diện trực quan.
- **Về mặt vận hành:** Đã đóng gói thành công Fat-JAR, chạy được ngay trên bất kỳ máy nào có cài sẵn Java Runtime Environment mà không cần cài thêm cơ sở dữ liệu.

*(Đính kèm 3-4 hình ảnh chụp màn hình ứng dụng thực tế: Màn hình Login, Dashboard, Màn hình Quản lý Phiên).*

### 5.2 Đánh giá hệ thống
**(👤 Người viết: Thành viên 2)**

- **Ưu điểm:**
  - Hoạt động nhẹ, không tốn tài nguyên RAM/CPU.
  - Portable, dễ dàng sao chép sang máy khác làm việc tiếp (do file CSDL nằm trong folder `data/`).
  - Phân tách code MVC rõ ràng, giúp các thành viên dễ làm việc nhóm, không bị xung đột (conflict) Git.
- **Nhược điểm:**
  - Chưa áp dụng mã hóa mật khẩu (Hashing) cho tài khoản admin.
  - Phụ thuộc hoàn toàn vào tác vụ thủ công của thu ngân (VD: phải click kết thúc phiên thay vì kết nối tự động với phần mềm Client dưới máy trạm).

### 5.3 Hướng phát triển trong tương lai
**(👤 Người viết: Thành viên 1)**

Nếu có thêm thời gian, nhóm sẽ phát triển hệ thống theo các hướng sau:
1. **Phát triển Client App:** Xây dựng một phần mềm Client nhỏ chạy dưới máy trạm của khách. Khi trên Server bấm "Mở máy", Client sẽ mở khóa màn hình. Khi khách hết tiền, tự động khóa màn hình.
2. **Tích hợp thanh toán QR Code:** Khi nạp tiền hoặc kết thúc phiên cho khách vãng lai, hiển thị mã QR VietQR (động) để khách hàng chuyển khoản nhanh, phần mềm gọi API xác nhận giao dịch tự động.
3. **Quản lý kho:** Nâng cấp chức năng "Quản lý Đồ ăn/uống" thành một hệ thống kho hoàn chỉnh (Quản lý nhập kho, xuất kho, tồn kho theo thời gian thực).

### 5.4 Kết luận chung
**(👤 Người viết: Thành viên 1)**

Thông qua đồ án môn Phân tích và Thiết kế Phần mềm, nhóm đã áp dụng thành công những kiến thức lý thuyết học được trên lớp vào một dự án thực tế. Nhóm đã trải qua đầy đủ các vòng đời của phát triển phần mềm: từ lúc khảo sát yêu cầu, mô hình hóa luồng nghiệp vụ, phân tích Use-case, thiết kế kiến trúc hệ thống, cho đến lập trình và kiểm thử.

Hệ thống "Quản lý Quán Internet CyberNet" tuy là một sản phẩm phục vụ môn học, nhưng đã đạt độ hoàn thiện cao, giải quyết trực tiếp được bài toán quản lý phòng máy một cách tối ưu. Nhóm xin gửi lời cảm ơn đến giảng viên **TS. Mai Thúy Nga** đã hướng dẫn, định hướng cho nhóm trong suốt quá trình hoàn thiện đồ án này.
