# Hướng Dẫn Sử Dụng Git Cơ Bản

Tài liệu này hướng dẫn bạn các thao tác cơ bản nhất khi làm việc với Git: cách đẩy (push) code bạn vừa viết lên dự án và cách kéo (pull) bản cập nhật mới nhất từ dự án về máy.

---

## 1. Cách lấy code mới nhất về máy (Pull code)

Khi làm việc trên một dự án, code trên server (như GitHub hoặc GitLab) có thể đã được cập nhật bởi các thành viên khác. Bạn cần cập nhật những thay đổi đó về máy tính của mình trước khi tiếp tục làm việc.

Chạy lệnh sau trên terminal:

```bash
git pull origin main
```

*(Ghi chú: Thay `main` bằng tên nhánh (branch) dự án của bạn nếu nó ở nhánh khác, ví dụ như `master` hoặc `dev`).*

> [!IMPORTANT]
> **Nguyên tắc vàng:** Luôn `git pull` trước khi bắt đầu viết code hoặc trước khi đẩy code lên để đảm bảo code trên máy của bạn luôn mới nhất và tránh xung đột (conflict).

---

## 2. Cách đẩy code lên dự án (Push code)

Sau khi chỉnh sửa xong code, quy trình để lưu và đẩy code lên dự án gồm 3 bước sau:

### Bước 1: Thêm các thay đổi (Add)

Bạn có **2 cách** để thêm file vào danh sách chờ commit:

**Cách 1: Thêm toàn bộ file đã thay đổi** (dùng khi bạn sửa nhiều file và muốn commit hết):
```bash
git add .
```

**Cách 2: Thêm từng file cụ thể** (dùng khi bạn chỉ muốn commit 1 vài file nhất định):
```bash
git add ten-file-1.java
git add ten-file-2.java
```

> [!WARNING]
> **Lưu ý quan trọng:** Khi dùng `git add .`, **tất cả** file đã thay đổi trong thư mục sẽ được thêm vào — kể cả những file bạn không có ý định commit (ví dụ: file database `.db`, file cấu hình `.vscode/`...). Nếu bạn chỉ muốn commit 1 file cụ thể thì hãy dùng **Cách 2**.

**Mẹo:** Trước khi commit, luôn chạy `git status` để kiểm tra xem những file nào đang được đánh dấu (staged). Nếu thấy file không mong muốn, bạn có thể bỏ nó ra bằng lệnh:
```bash
git reset HEAD ten-file-khong-muon.db
```

### Bước 2: Lưu các thay đổi kèm ghi chú (Commit)
Bạn cần "đóng gói" các thay đổi đó và gán cho nó một lời nhắn ngắn gọn để mọi người (và chính bạn sau này) biết bạn vừa code gì:

```bash
git commit -m "Nội dung mô tả việc vừa làm"
```
*Ví dụ: `git commit -m "Cập nhật lại giao diện trang chủ"`.*

### Bước 3: Đẩy code cập nhật lên dự án (Push)
Chuyển gói code vừa lưu lên máy chủ dùng chung:

```bash
git push origin main
```
*(Tương tự, thay `main` bằng tên nhánh đang làm việc nếu cần).*

---

## 3. Tóm tắt quy trình hoàn chỉnh hàng ngày
Để tránh lỗi trong quá trình làm việc, một quy trình an toàn trên Terminal/Command Line sẽ là:

1. `git pull origin main` (Lấy code mới về).
2. ...Tiến hành code và lưu tệp... 
3. `git add .` (Thêm tất cả thay đổi).
4. `git commit -m "Tính năng/Lỗi đã sửa"` (Đóng gói).
5. `git push origin main` (Đẩy lên dự án cục bộ).
