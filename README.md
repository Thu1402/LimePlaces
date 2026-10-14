# Lime Places — Android Studio (Java)

Dự án Android native được chuyển từ prototype React.

## Mở dự án

1. Mở Android Studio.
2. Chọn **Open** và trỏ đến thư mục `android`.
3. Chờ Gradle Sync hoàn tất.
4. Chạy module `app` trên emulator hoặc thiết bị Android 7.0 trở lên.

## Công nghệ

- Java 17
- AndroidX Fragment + RecyclerView
- Material Components
- Glide để tải ảnh địa điểm

Mã Java nằm trong `app/src/main/java/com/limeplaces/app`.


## Cấu trúc giao diện XML đã tách

Các layout gốc của từng màn hình nằm trong `app/src/main/res/layout/`:

- `activity_main.xml`: khung màn hình và bottom navigation.
- `fragment_home.xml`: vùng nội dung trang chủ.
- `fragment_detail.xml`: vùng nội dung chi tiết địa điểm.
- `fragment_add_place.xml`: vùng nội dung thêm địa điểm.
- `fragment_map.xml`: khung bản đồ.
- `fragment_friends.xml`: vùng nội dung bạn bè.
- `fragment_profile.xml`: vùng nội dung hồ sơ.
- `item_place.xml`: mẫu XML tham khảo cho thẻ địa điểm.
- `item_friend.xml`: mẫu XML cho dòng bạn bè.

Mỗi Fragment hiện đã inflate layout XML riêng bằng `inflater.inflate(...)`; Java tiếp tục gắn dữ liệu và xử lý thao tác người dùng. Điều này giúp chỉnh nhanh bố cục gốc, nền, padding và cấu trúc từng màn hình. Một số thành phần động (ví dụ danh sách địa điểm, thẻ bạn bè, nút hành động) hiện vẫn được tạo trong Java; các file `item_*.xml` là mẫu để chuyển các thành phần đó sang XML ở bước tiếp theo.
