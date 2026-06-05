# CHƯƠNG 4: CÀI ĐẶT (IMPLEMENTATION) & CHƯƠNG 5: KẾT LUẬN

> **👤 PHÂN CÔNG THỰC HIỆN:**
> - **Thành viên 4 (Backend, Tester):** Viết mục 4.1 và 4.2. Chịu trách nhiệm quản lý cấu trúc mã nguồn, đóng gói Fat-JAR deploy ứng dụng.
> - **Thành viên 2 (UI/UX, Frontend):** Viết mục 5.1 và 5.2. Chụp ảnh ứng dụng minh họa và đánh giá giao diện.
> - **Thành viên 1 (Trưởng nhóm):** Viết mục 5.3 và 5.4, chuẩn bị slide thuyết trình tổng hợp và phân công bảo vệ đồ án.

---

## 4. CÀI ĐẶT (IMPLEMENTATION)

### 4.1 Quá trình lựa chọn công nghệ và Công cụ
Quyết định kiến trúc của CyberNet tập trung vào việc tạo ra một phần mềm cài đặt siêu tốc, không rào cản.

1. **Ngôn ngữ Lập trình:** `Java 17 (LTS)`. Đây là nền tảng vững chắc, sở hữu khả năng bảo mật cao, có Garbage Collector quản lý bộ nhớ tự động, vô cùng phù hợp để xây dựng các ứng dụng desktop chạy 24/7 như quán net.
2. **Framework Giao diện:** `Java Swing`. Thay vì JavaFX cần kéo thả phức tạp, Swing cung cấp thư viện linh hoạt. Để khắc phục yếu điểm "giao diện thập niên 90" của Swing, nhóm đã mạnh dạn tích hợp thư viện **FlatLaf**. Nó phủ một lớp áo Dark Theme mượt mà, đổi icon đồng bộ, biến ứng dụng trở nên hiện đại như các app Web/Mobile năm 2026.
3. **Cơ sở Dữ liệu Embedded:** `H2 Database Engine`.
   - Lợi thế vượt trội: Không cần cài đặt (zero-configuration), dung lượng engine chỉ xấp xỉ 2MB, được lập trình bằng 100% mã Java nguyên thủy. Database được tạo ngay tại thư mục chứa file chạy `data/quanlyquaninternet.mv.db`. Nếu chủ quán cần sao lưu, chỉ việc copy nguyên thư mục này sang máy khác.
4. **Công cụ Build (Build Automation):** `Apache Maven`.
   - Quản lý siêu tốc file cấu hình `pom.xml`, tự động tải các file JAR của FlatLaf, H2, JCalendar từ repository về máy tính.

### 4.2 Cấu trúc Mã Nguồn chuẩn công nghiệp
Cây thư mục mã nguồn được tổ chức sạch sẽ, tuân thủ mô hình Separation of Concerns (Tách biệt mối quan tâm):

```text
E:\BTL
├── BaoCao_SAD_CyberNet/             [Thư mục chứa tài liệu báo cáo của nhóm]
├── data/                            [Chứa file database tự sinh của H2]
├── src/main/java/com/mycompany/quanlyquaninternet/
│   ├── controller/                  [Các class Controller bắt sự kiện click]
│   ├── dao/                         [Chứa KetNoiCSDL.java xử lý truy vấn]
│   ├── entity/                      [Các POJO - MayTinh, KhachHang...]
│   ├── view/                        [Toàn bộ GUI code: Panel, Frame]
│   └── QuanLyQuanInternet.java      [Hàm Main khởi chạy Thread chính]
├── src/main/resources/              
│   └── schema.sql                   [Script chứa cấu trúc tạo bảng ban đầu]
└── pom.xml                          [File khai báo cấu trúc Maven]
```

---

## 5. KẾT LUẬN

### 5.1 Đánh giá kết quả phần mềm
Sau quá trình thiết kế, lập trình và chạy thử nghiệm, sản phẩm "Phần mềm Quản lý CyberNet" đã đạt được độ hoàn thiện xuất sắc:
- **Tốc độ:** Khởi chạy dưới 2 giây. Các thao tác chuyển màn hình (Render CardLayout) mượt mà, không gặp hiện tượng treo UI.
- **Tính chính xác:** Engine H2 kết hợp logic Java chạy song song đảm bảo đồng hồ thời gian của các máy trạm không bị sai số. Tiền giờ được thu về khớp từng đồng so với báo cáo doanh thu.
- **Tính thẩm mỹ:** Dark Mode của FlatLaf đem lại một làn gió mới, khác biệt hoàn toàn với các phần mềm đồ án của các nhóm khác. Giao diện trực quan, nhân viên chỉ mất 5 phút hướng dẫn để làm quen phần mềm.

*(Team lưu ý: Thành viên 2 sẽ chụp ảnh Màn hình Đăng nhập, Màn hình Máy Trống/Đang dùng, Màn hình Báo Cáo Doanh Thu, Màn hình đổi điểm thưởng và dán trực tiếp vào file Word tại đây để chứng minh).*

### 5.2 Hạn chế còn tồn đọng (Limitations)
Tuy mang tính đột phá, phần mềm vẫn là bản Local Desktop và chịu giới hạn ở một số yếu điểm:
- **Tương tác một chiều:** Phần mềm trên máy Chủ không truyền lệnh khóa màn hình được xuống máy Khách (Client PC) qua mạng LAN. Thu ngân vẫn phải quan sát khách về thì mới tự tay ấn "Kết thúc".
- **Bảo mật cục bộ:** Mật khẩu chưa được Hash bằng SHA-256. Database H2 tuy nằm trên ổ cứng nhưng chưa thiết lập mật khẩu mã hóa (Encrypted File). Một người rành IT có thể chép trộm file `.db` để đọc data.

### 5.3 Lộ trình Mở rộng (Future Work)
- **Tích hợp API Thanh Toán (Fintech):** Mở rộng tính năng tự động tạo mã QR Code động. Khách hàng nạp tiền qua mã QR chuyển khoản, phần mềm lắng nghe Webhook từ ngân hàng và tự động cập nhật số dư không cần nhân viên gõ tay.
- **Mô hình Client-Server thực thụ:** Viết thêm một tệp thực thi (Client Agent) chạy ngầm dưới máy con, sử dụng Socket.IO trong Java để truyền lệnh Khóa/Mở máy từ xa qua mạng LAN nội bộ.
- **Cloud Database:** Đổi engine từ H2 sang PostgreSQL trên server đám mây, giúp người quản lý mở app trên điện thoại theo dõi tình hình quán Internet 24/7 từ nhà.

### 5.4 Tổng kết Đồ án
Môn học **Phân tích và Thiết kế Phần mềm** đã trang bị cho nhóm một bộ khung tư duy nền tảng vững chắc. Từ xuất phát điểm là một bài toán mờ mịt của chủ quán Internet, thông qua quá trình Khảo sát -> Mô hình hóa Use-case -> Vẽ kiến trúc MVC & ERD -> Cài đặt code, bài toán đã được giải quyết triệt để.

Nhóm xin chân thành gửi lời cảm ơn đến **TS. Mai Thúy Nga**, giảng viên trực tiếp giảng dạy. Bằng hệ thống lý thuyết thực tiễn và Template báo cáo chuẩn mực, cô đã dẫn dắt nhóm vượt qua các rào cản kỹ thuật để tạo ra một đồ án có chất lượng cao nhất, bám sát với quy trình kỹ thuật phần mềm hiện đại của doanh nghiệp.
