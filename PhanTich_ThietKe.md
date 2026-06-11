# CHƯƠNG 3. PHÂN TÍCH HỆ THỐNG

---

## 3.1. Khảo sát hiện trạng

### 3.1.1. Quy trình nghiệp vụ hiện tại
Tại các quán internet có quy mô nhỏ và vừa chưa áp dụng các phần mềm quản lý chuyên nghiệp, quy trình vận hành thủ công diễn ra như sau:
1. **Khách đến chơi:** Khách hàng đến quầy gặp nhân viên quản lý. Nhân viên ghi chép tên khách, số máy được phân phối và giờ bắt đầu vào sổ theo dõi. Nhân viên đi đến máy tính của khách và bật công tắc mở máy thủ công (hoặc mở máy thông qua một phần mềm điều khiển đơn giản không liên kết với hệ thống tài chính).
2. **Gọi dịch vụ (Đồ ăn, thức uống):** Trong quá trình sử dụng máy, khách hàng đi tới quầy hoặc gọi nhân viên để đặt đồ ăn/nước uống. Nhân viên ghi chép đơn hàng ra giấy, chuẩn bị đồ ăn và mang đến máy cho khách. Đơn hàng được giữ lại quầy để cộng tiền khi khách thanh toán.
3. **Thanh toán & Kết thúc:** Khách hàng ra quầy báo máy nghỉ. Nhân viên đối chiếu giờ bắt đầu và giờ kết thúc chơi để tính tiền máy (sử dụng máy tính cầm tay để tính số giờ nhân với đơn giá). Sau đó cộng thêm tiền các phiếu gọi dịch vụ ăn uống tương ứng để ra tổng hóa đơn thanh toán. Khách hàng trả tiền mặt, nhân viên gạch tên khách trong sổ theo dõi và tắt máy thủ công.
4. **Báo cáo doanh thu:** Cuối ngày làm việc, chủ quán hoặc nhân viên thực hiện cộng dồn toàn bộ số tiền máy và tiền dịch vụ đã ghi nhận trong sổ để tính tổng doanh thu trong ngày, ghi chép vào sổ quỹ để theo dõi.

### 3.1.2. Các vấn đề, hạn chế
Từ quy trình khảo sát thực tế trên, hệ thống thủ công bộc lộ nhiều hạn chế nghiêm trọng:
- **Sai sót trong tính tiền:** Việc tính toán tiền giờ thủ công bằng máy tính cầm tay dễ xảy ra sai sót, đặc biệt khi lượng khách ra vào đông hoặc thời gian chơi lẻ (ví dụ: 1 giờ 37 phút).
- **Khó quản lý trạng thái máy:** Nhân viên khó theo dõi trực quan máy nào đang trống, máy nào đang hoạt động hoặc máy nào bị lỗi (bảo trì) khi quy mô phòng máy tăng từ 20 máy trở lên.
- **Thất thoát doanh thu dịch vụ:** Các đơn gọi đồ ăn/thức uống ghi chép bằng giấy dễ bị thất lạc, ghi thiếu hoặc nhân viên quên cộng vào hóa đơn thanh toán khi khách ra về.
- **Thiếu tính gắn kết khách hàng:** Không quản lý được thông tin khách hàng thân thiết, không có cơ chế nạp tiền trước, tích điểm thành viên hay chính sách ưu đãi đổi thưởng để giữ chân khách hàng.
- **Báo cáo thống kê chậm trễ và thiếu chính xác:** Việc tổng hợp doanh thu cuối ngày bằng sổ sách mất thời gian, dễ nhầm lẫn và không thể cung cấp biểu đồ so sánh xu hướng doanh thu theo tuần, tháng để chủ quán đưa ra quyết định kinh doanh.

---

## 3.2. Phân tích yêu cầu

### 3.2.1. Yêu cầu nghiệp vụ
Hệ thống quản lý quán internet CyberNet cần đáp ứng các yêu cầu nghiệp vụ thực tế sau:
- **Phân loại máy và đơn giá:** Quản lý tối thiểu 20 máy tính được phân thành 2 loại: máy Thường (giá mặc định 10.000đ/giờ) và máy VIP (giá mặc định 15.000đ/giờ) để tối ưu hóa nguồn thu.
- **Quản lý khách hàng linh hoạt:** Hỗ trợ cả Khách vãng lai (không cần tài khoản, thanh toán sau) và Khách thành viên (nạp tiền trước vào tài khoản để trừ dần, hỗ trợ tích lũy điểm thưởng).
- **Tự động hóa tính tiền:** Hệ thống tự động tính chi phí chơi dựa trên thời gian thực sử dụng (làm tròn đến 0.1 giờ), tự động cộng tiền dịch vụ để xuất hóa đơn tổng hợp.
- **Cơ chế tích điểm và đổi thưởng:** Khách thành viên khi chơi sẽ được tích lũy điểm thưởng (1 giờ chơi = 1 điểm). Điểm này dùng để đổi lấy các dịch vụ ăn uống miễn phí có cấu hình điểm đổi trong menu.
- **Liên thông dữ liệu:** Khi kết thúc phiên chơi, doanh thu phải tự động cập nhật lên hệ thống báo cáo và bảng điều khiển tổng quan (Dashboard) thời gian thực.

### 3.2.2. Yêu cầu chức năng

| STT | Yêu cầu chức năng | Mô tả chi tiết | Độ ưu tiên |
|-----|-------------------|----------------|-------------|
| YC01 | Đăng nhập hệ thống | Nhân viên quản lý đăng nhập bằng tài khoản admin để bảo mật dữ liệu. | Cao |
| YC02 | Xem Dashboard tổng quan | Theo dõi trực quan số máy trống, đang sử dụng, doanh thu trong ngày và danh sách phiên đang chạy. | Cao |
| YC03 | Quản lý máy tính | Xem danh sách máy dưới dạng lưới, thêm máy mới, sửa cấu hình. | Cao |
| YC04 | Bắt đầu phiên chơi | Click chọn máy trống → chọn khách hàng → kích hoạt tính giờ. | Cao |
| YC05 | Kết thúc phiên chơi | Tính tiền tự động, trừ tiền tài khoản thành viên, cộng điểm, giải phóng máy. | Cao |
| YC06 | Gọi đồ ăn / nước uống | Chọn món từ thực đơn, nhập số lượng và gán đơn hàng vào phiên chơi đang chạy của máy. | Cao |
| YC07 | Quản lý khách hàng | Quản lý danh sách thành viên (CRUD), tìm kiếm theo tên hoặc số điện thoại. | Cao |
| YC08 | Nạp tiền tài khoản | Nạp tiền vào tài khoản hội viên, tự động quy đổi thành giờ chơi và điểm thưởng. | Cao |
| YC09 | Đổi thưởng bằng điểm | Dùng điểm tích lũy của thành viên đổi lấy đồ ăn uống miễn phí. | Trung bình |
| YC10 | Quản lý menu dịch vụ | Quản lý danh mục đồ ăn, nước uống, đơn giá, điểm đổi thưởng và tình trạng còn hàng. | Trung bình |
| YC11 | Thống kê doanh thu | Thống kê doanh thu theo khoảng ngày, vẽ biểu đồ cột trực quan, liệt kê chi tiết giao dịch. | Trung bình |
| YC12 | Đặt máy bảo trì | Chuyển trạng thái máy tính sang bảo trì khi có sự cố kỹ thuật. | Thấp |
| YC13 | Đăng xuất | Thoát khỏi phiên làm việc an toàn, đóng kết nối cơ sở dữ liệu. | Cao |

### 3.2.3. Yêu cầu phi chức năng
- **Bảo mật:** Giao diện quản lý chỉ có admin truy cập được. Cơ sở dữ liệu H2 lưu trữ an toàn dưới dạng file cục bộ có mật khẩu bảo vệ kết nối.
- **Hiệu năng:** Khởi động ứng dụng trong vòng < 2 giây. Tải danh sách máy và thực hiện các thao tác chuyển đổi panel giao diện mượt mà (dưới 300ms) nhờ cơ chế CardLayout.
- **Độ tin cậy:** Áp dụng Singleton Pattern cho kết nối CSDL giúp ngăn ngừa xung đột dữ liệu. Sử dụng Database Transactions để đảm bảo tính toàn vẹn khi lưu trữ thông tin phiên chơi và thanh toán trừ tiền khách hàng.
- **Khả năng mở rộng:** Kiến trúc chia rõ ràng MVC (Model - View - Controller) kết hợp DAO Pattern giúp dễ dàng thay thế CSDL H2 bằng MySQL khi phát triển thành chuỗi phòng máy lớn.
- **Tính khả dụng:** Giao diện thiết kế Dark Mode sang trọng, giảm mỏi mắt cho nhân viên trực máy. Các trạng thái máy có màu sắc viền phân biệt rõ ràng, hỗ trợ emoji dễ nhận diện.

---

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
