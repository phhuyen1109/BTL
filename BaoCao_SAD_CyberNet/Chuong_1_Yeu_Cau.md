# CHƯƠNG 1: YÊU CẦU (REQUIREMENTS)

> **👤 PHÂN CÔNG THỰC HIỆN CHƯƠNG NÀY:**
> - **Thành viên 2 (UI/UX, Frontend):** Chịu trách nhiệm viết mục 1.1, 1.2, 1.3 và vẽ các Mockup Giao diện đính kèm ở 1.6.
> - **Thành viên 3 (BA, Phân tích nghiệp vụ):** Chịu trách nhiệm viết mục 1.4, 1.5, và đặc tả chi tiết các Use-case tại 1.6.

---

## 1.1 Đặt vấn đề (Problem statement)
**(👤 Người viết: Thành viên 2)**

### 1.1.1 Bối cảnh
Hiện nay, việc kinh doanh các điểm truy cập Internet (Cyber Game, Quán Net) ngày càng phát triển. Tuy nhiên, quy trình quản lý giờ chơi, thanh toán tiền giờ, gọi đồ ăn thức uống ở nhiều quán vẫn còn thủ công hoặc sử dụng các hệ thống rời rạc, dẫn đến sai sót, thất thoát doanh thu và trải nghiệm khách hàng không tốt.

### 1.1.2 Mục tiêu
Xây dựng một phần mềm quản lý quán Internet (CyberNet) tập trung, tự động hóa toàn bộ quy trình: từ việc cấp phát máy, tính giờ, quản lý nạp tiền, gọi đồ ăn thức uống, cho đến thống kê doanh thu. Hệ thống giúp tối ưu hóa nhân sự và mang lại tính minh bạch cao.

### 1.1.3 Người dùng mục tiêu
- **Quản lý / Chủ quán (Admin):** Nắm quyền cao nhất, quản lý toàn bộ hệ thống, xem thống kê, quản lý nhân viên, và thêm/sửa/xóa dịch vụ.
- **Nhân viên thu ngân (Staff):** Tạo/kết thúc phiên máy, nạp tiền cho khách, bán đồ ăn uống, đổi thưởng.
- **Khách hàng (Customer):** (Không tác động trực tiếp vào hệ thống quản lý) Sở hữu tài khoản tích điểm, số dư để chơi và mua đồ.

### 1.1.4 Lợi ích khi sử dụng phần mềm
- Giảm thiểu sai sót tính toán tiền giờ và tiền dịch vụ.
- Cải thiện tốc độ phục vụ đồ ăn/uống cho khách.
- Khuyến khích khách hàng nạp nhiều tiền thông qua cơ chế tích điểm - đổi thưởng.
- Dễ dàng thống kê, xuất báo cáo cho quản lý vào cuối ngày/tuần.

### 1.1.5 Phạm vi của dự án
- **Trong phạm vi:** Quản lý tài khoản đăng nhập; Quản lý thông tin và trạng thái Máy tính; Quản lý thông tin, số dư, điểm của Khách hàng; Quản lý phiên sử dụng; Quản lý Menu (đồ ăn/uống) và hóa đơn dịch vụ; Thống kê doanh thu.
- **Ngoài phạm vi:** Hệ thống không xử lý khóa màn hình máy trạm (Client) trực tiếp như phần mềm CSM/Gcafe mà chỉ quản lý logic tính giờ trên máy chủ; Không kết nối trực tiếp cổng thanh toán ngân hàng (chỉ ghi nhận nạp tiền mặt).

## 1.2 Thuật ngữ (Glossary)
**(👤 Người viết: Thành viên 2)**
- **CyberNet:** Tên của hệ thống phần mềm quản lý.
- **Máy trạm (Client):** Máy tính dành cho khách hàng sử dụng dịch vụ.
- **Phiên sử dụng (Session):** Khoảng thời gian từ lúc khách bắt đầu mở máy chơi đến khi kết thúc đóng máy tính tiền.
- **Khách vãng lai:** Khách không có tài khoản, chơi trả tiền trực tiếp.
- **Điểm tích lũy:** Điểm thưởng khách nhận được khi nạp tiền/chơi giờ, dùng để đổi dịch vụ miễn phí.
- **Menu/Dịch vụ:** Đồ ăn và thức uống bán kèm tại quán.
- **FlatLaf:** Thư viện giao diện Java được sử dụng để làm đẹp ứng dụng (Dark theme).
- **Fat-JAR:** Định dạng đóng gói ứng dụng Java chứa luôn toàn bộ thư viện bên trong để dễ dàng thực thi.
- **H2 Database:** Hệ quản trị cơ sở dữ liệu nhúng siêu nhẹ được tích hợp thẳng vào phần mềm.

## 1.3 Thông số kỹ thuật bổ sung
**(👤 Người viết: Thành viên 2)**
- **Hiệu năng:** Ứng dụng phải mở nhanh dưới 3 giây, thao tác chuyển đổi panel không bị giật lag.
- **Lưu trữ:** Sử dụng CSDL H2, tự động sinh file DB lưu nội bộ, không phụ thuộc vào server ngoại vi.
- **Khả năng tự động hóa:** Dashboard và danh sách Phiên sử dụng phải tự động làm mới (Auto-refresh) mỗi 30 giây để cập nhật thời gian thực.
- **Tính tiện dụng:** Giao diện Dark mode chống mỏi mắt cho nhân viên làm ca đêm.

## 1.4 Mô hình hóa quy trình nghiệp vụ
**(👤 Người viết: Thành viên 3)**

### 1.4.1 Quy trình Quản lý khách hàng sử dụng máy
1. Khách hàng tới quán, chọn máy trống.
2. Nhân viên mở phần mềm, chọn máy đó, khởi tạo "Phiên sử dụng".
3. Nhân viên chọn phân loại: Khách vãng lai (không trừ tiền số dư) hoặc Khách thành viên (nhập tên/SĐT để trừ tiền tự động).
4. Hệ thống cập nhật trạng thái máy thành "Đang dùng", tính giờ.
5. Khi khách nghỉ, nhân viên chọn "Kết thúc phiên", hệ thống tự động cộng dồn tiền máy và tiền dịch vụ (nếu có).

### 1.4.2 Quy trình Nạp tiền và Đổi thưởng
1. Khách hàng yêu cầu nạp tiền vào tài khoản.
2. Nhân viên chọn tài khoản, nhập số tiền. Hệ thống quy đổi số tiền ra Giờ chơi và Điểm tích lũy theo tỷ lệ 10.000đ = 1 Giờ = 1 Điểm.
3. Nếu khách muốn đổi thưởng đồ ăn/uống, nhân viên mở form "Đổi thưởng", hệ thống kiểm tra điểm của khách, trừ điểm và lưu lịch sử nếu đủ điểm.

## 1.5 Mô hình hóa chức năng
**(👤 Người viết: Thành viên 3)**

### 1.5.1 Các yêu cầu chức năng
**R1. Quản lý Hệ thống & Tài khoản**
- R1.1 Đăng nhập hệ thống.
- R1.2 Xem Dashboard tổng quan (Máy trống, Đang dùng, Tổng phiên, Tổng doanh thu ngày).

**R2. Quản lý Máy tính**
- R2.1 Xem danh sách máy tính và trạng thái.
- R2.2 Mở / Đóng phiên sử dụng trên một máy tính.

**R3. Quản lý Khách hàng**
- R3.1 Thêm, sửa, xóa khách hàng.
- R3.2 Nạp tiền và tự động cộng giờ/điểm.
- R3.3 Đổi thưởng đồ ăn/uống bằng điểm.

**R4. Quản lý Dịch vụ (Đồ ăn/uống)**
- R4.1 Quản lý danh mục sản phẩm (CRUD).
- R4.2 Thêm đơn hàng dịch vụ vào phiên đang chạy của khách.

**R5. Thống kê Báo cáo**
- R5.1 Xem bảng thống kê số phiên và doanh thu theo ngày.
- R5.2 Hiển thị biểu đồ cột doanh thu trực quan.

### 1.5.2 Sơ đồ Use-case
*(Thành viên 3 vẽ sơ đồ Use-case tổng quát và chèn hình ảnh vào đây)*
- Admin (Nhân viên) tương tác với Đăng nhập, Quản lý Khách hàng, Quản lý Máy, Quản lý Dịch vụ, Quản lý Phiên, Thống kê.

## 1.6 Đặc tả các Use-case
**(👤 Người viết: Thành viên 3 viết text, Thành viên 2 chèn ảnh Mockup giao diện minh họa)**

### 1.6.1 UC-01: Bắt đầu phiên sử dụng
- **Tác nhân:** Nhân viên.
- **Tiền điều kiện:** Nhân viên đã đăng nhập, Máy tính đang ở trạng thái "Trống".
- **Luồng thông thường:**
  1. Nhân viên nhấn đúp vào máy "Trống".
  2. Hệ thống hiển thị hộp thoại Bắt đầu phiên.
  3. Nhân viên chọn Khách có tài khoản hoặc Khách vãng lai.
  4. Nhấn "Bắt đầu".
  5. Hệ thống tạo record trong bảng PHIEN_SU_DUNG, đổi trạng thái máy sang "Đang dùng".
- **Giao diện minh họa:** *(Thành viên 2 chèn Mockup)*

### 1.6.2 UC-02: Nạp tiền cho khách hàng
- **Tác nhân:** Nhân viên.
- **Tiền điều kiện:** Chọn đúng khách hàng trong danh sách.
- **Luồng thông thường:**
  1. Chọn khách hàng, nhấn "Nạp tiền".
  2. Hệ thống hiển thị form yêu cầu nhập số tiền.
  3. Nhập số tiền (VD: 50.000), hệ thống tự động preview được cộng 5 giờ và 5 điểm.
  4. Nhấn Lưu. Hệ thống cộng số dư, giờ, điểm vào database.
- **Ngoại lệ:** Nhập số tiền âm hoặc chữ sẽ báo lỗi.
- **Giao diện minh họa:** *(Thành viên 2 chèn Mockup)*
