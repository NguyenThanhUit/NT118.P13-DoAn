# PHÁT TRIỂN ỨNG DỤNG TRÊN THIẾT BỊ DI ĐỘNG - NHÓM 5

## TÊN ĐỀ TÀI: XÂY DỰNG ỨNG DỤNG QUẢN LÝ QUAN HỆ KHÁCH HÀNG (CRM APP)

### Hướng dẫn sử dụng:
1. **Đăng nhập Admin**: Sử dụng tài khoản:
   - Tên đăng nhập: `admin`
   - Mật khẩu: Tùy chọn.
2. **Tạo tài khoản Employee**:
   - Vào phần **"Create Account"** trong **fragment settings** để tạo tài khoản cho nhân viên.
3. **Đăng nhập Employee**:
   - Đăng xuất và đăng nhập lại bằng tài khoản Employee vừa tạo.

---

## Phân công công việc thành viên:

| **STT** | **Thành viên**         | **Nhiệm vụ**                                                                 | **Tiến độ** |
|---------|------------------------|-------------------------------------------------------------------------------|-------------|
| 1       | Lê Anh Hào             | Java tương tác với UI Reports và Statistic, XML UI: Statistics                | 100%        |
| 2       | Phạm Thanh Lâm         | Java tương tác với UI Thêm, xóa, sửa Employee trong Admin. Tính năng liên lạc với Customer | 100%        |
| 3       | Phan Duy Thọ           | XML UI: Login, Create account và các giao diện của admin                      | 100%        |
| 4       | Nguyễn Thanh           | Java tương tác với UI và database của Customer, Orders, Goods                 | 100%        |
| 5       | Nguyễn Hữu Thắng       | XML UI: Các giao diện của employee                                            | 100%        |

---

## Công nghệ sử dụng:
- **Ngôn ngữ lập trình**: Java.
- **Framework**: Android Studio.
- **Cơ sở dữ liệu**: SQLite.
- **Công cụ thiết kế**: Figma.

---

## Tính năng:
1. **Lưu trữ thông tin**:
   - Thông tin khách hàng.
   - Sản phẩm tồn kho.
   - Đơn đặt hàng.
   - Nhiệm vụ và thông báo từ người quản lý.

2. **Khách hàng**:
   - Phân loại, tìm kiếm, liên lạc (số điện thoại, Gmail).
   - Thêm, xóa, sửa thông tin khách hàng.

3. **Nhân viên**:
   - Thêm, xóa, sửa thông tin nhân viên.
   - Thêm, xóa, sửa tài khoản cho nhân viên.

4. **Sản phẩm**:
   - Thêm, xóa, sửa sản phẩm trong kho.

5. **Đơn hàng**:
   - Thêm, xóa, sửa đơn hàng dựa trên sản phẩm tồn kho.

6. **Nhiệm vụ**:
   - Người quản lý có thể thêm, xóa, sửa nhiệm vụ cho nhân viên.

7. **Thông báo sự kiện/chiến dịch**:
   - Người quản lý thêm, xóa, sửa các thông báo dành cho nhân viên.

8. **Thống kê**:
   - Doanh thu đạt được.
   - Doanh thu tiềm năng.

---

## Sự khác biệt:
Ứng dụng được triển khai theo **cấu trúc hiện đại MVVM (Model-View-ViewModel)**, giúp tăng khả năng mở rộng, bảo trì và tối ưu hiệu năng. Đây là kiến trúc phổ biến trong các ứng dụng Android hiện nay.
