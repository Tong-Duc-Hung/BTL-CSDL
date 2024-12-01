# BTL-CSDL

## Mục lục
1. [Các bảng và chức năng của các bảng trong database](#các-bảng-và-chức-năng-của-các-bảng-trong-database)  
2. [Mối quan hệ giữa các bảng](#mối-quan-hệ-giữa-các-bảng)  
3. [Thông tin dữ liệu trong các bảng](#thông-tin-dữ-liệu-trong-các-bảng)  
4. [Cách tạo ra dữ liệu trong database](#cách-tạo-ra-dữ-liệu-trong-database)  
5. [Ngôn ngữ sử dụng](#ngôn-ngữ-sử-dụng)  
6. [Người làm](#người-làm)  

## Các bảng và chức năng của các bảng trong database

### Bảng `users`
- Lưu trữ thông tin của người dùng như ID người dùng, tên đăng nhập, mật khẩu, email, số điện thoại, ảnh đại diện và vai trò (admin, khách hàng, nhân viên).

### Bảng `movies`
- Lưu trữ thông tin của phim bao gồm ID của phim, tiêu đề, mô tả, thể loại, đạo diễn, diễn viên, ngôn ngữ, thời lượng, ngày phát hành, đánh giá, hình ảnh, trailer và giá phim.

### Bảng `theaters`
- Lưu trữ thông tin về các rạp chiếu phim như ID của rạp, tên rạp, địa chỉ, tổng số phòng chiếu, thông tin liên lạc và tiện nghi.

### Bảng `auditoriums`
- Lưu trữ thông tin về các phòng chiếu phim bao gồm ID của phòng chiếu, tên phòng, số ghế và ID rạp chiếu phim.

### Bảng `showtimes`
- Lưu trữ thông tin về lịch chiếu phim, bao gồm ID suất chiếu, ID phim, ID rạp, ID phòng, ngày chiếu, giờ chiếu và giá giờ chiếu.

### Bảng `seats`
- Lưu trữ thông tin về ghế ngồi trong các phòng chiếu, bao gồm ID ghế, ID rạp, ID phòng, số ghế, loại ghế (thường, vip) và giá ghế.

### Bảng `bookings`
- Lưu trữ thông tin đặt vé, bao gồm ID của booking, ID người dùng, ID suất chiếu, ngày đặt, trạng thái và số ghế đã đặt.

### Bảng `tickets`
- Lưu trữ thông tin về vé, bao gồm ID vé, ID suất chiếu, ID ghế, ID booking, trạng thái vé và giá vé (giá vé = giá phim + giá ghế + giá giờ).

### Bảng `supportedbanks`
- Lưu trữ thông tin về các ngân hàng hỗ trợ thanh toán.

### Bảng `payments`
- Lưu trữ thông tin về các thanh toán, bao gồm ID booking, ngày thanh toán, tổng số tiền, trạng thái thanh toán và ID ngân hàng.

### Bảng `customer_feedback`
- Lưu trữ phản hồi từ khách hàng, bao gồm ID phản hồi, ID người dùng, nội dung phản hồi, loại phản hồi, trạng thái phản hồi và phản hồi của nhân viên.

### Bảng `reviews`
- Lưu trữ đánh giá phim từ người dùng, bao gồm ID đánh giá, ID phim, ID người dùng, xếp hạng sao (từ 1 đến 5) và nội dung đánh giá.

## Mối quan hệ giữa các bảng
- **users** và **bookings**: Quan hệ một-nhiều (1-N). Một người dùng có thể có nhiều đặt vé.
- **movies** và **showtimes**: Quan hệ một-nhiều (1-N). Một phim có thể có nhiều lịch chiếu.
- **theaters** và **auditoriums**: Quan hệ một-nhiều (1-N). Một rạp chiếu phim có thể có nhiều phòng chiếu.
- **auditoriums** và **showtimes**: Quan hệ một-nhiều (1-N). Một phòng chiếu có thể có nhiều lịch chiếu.
- **showtimes** và **bookings**: Quan hệ một-nhiều (1-N). Một lịch chiếu có thể có nhiều đặt vé.
- **showtimes** và **tickets**: Quan hệ một-nhiều (1-N). Một lịch chiếu có thể có nhiều vé.
- **auditoriums** và **seats**: Quan hệ một-nhiều (1-N). Một phòng chiếu có thể có nhiều ghế ngồi.
- **theaters** và **seats**: Quan hệ một-nhiều (1-N). Một rạp chiếu phim có thể có nhiều ghế ngồi.
- **seats** và **tickets**: Quan hệ một-nhiều (1-N). Một ghế ngồi có thể có nhiều vé.
- **bookings** và **tickets**: Quan hệ một-nhiều (1-N). Một đặt vé có thể có nhiều vé.
- **bookings** và **payments**: Quan hệ một-một (1-1). Một đặt vé chỉ có thể có một thanh toán.
- **supportedbanks** và **payments**: Quan hệ một-nhiều (1-N). Một ngân hàng hỗ trợ có thể có nhiều thanh toán.
- **users** và **customer_feedback**: Quan hệ một-nhiều (1-N). Một người dùng có thể có nhiều phản hồi khách hàng.
- **users** và **reviews**: Quan hệ một-nhiều (1-N). Một người dùng có thể có nhiều đánh giá phim.
- **movies** và **reviews**: Quan hệ một-nhiều (1-N). Một phim có thể có nhiều đánh giá.

## Thông tin dữ liệu trong các bảng
- Các thông tin trong các bảng users, showtimes, theaters, auditoriums, payments, seats, bookings, tickets, supportedbanks, payments, customer_feedback, reviews đều là dữ liệu giả.
- Dữ liệu trong bảng movies trừ movie_id và giá phim là dữ liệu giả thì các dữ liệu còn lại đều là thật đều có nguồn thông tin từ trên google.

## Cách tạo ra dữ liệu trong database
- Dựa vào ChatGpt và Microsoft Copilot để viết mã java tạo dữ liệu giả.
- Đối với các bảng movies thì tìm kiếm và viết dữ liệu.
- Đối với các dữ liệu có mối quan hệ với nhau thì tiến hành tạo dữ liệu giả của một bảng trước rồi truy vấn những thông tin cần thiết sau đó dùng AI tạo ra dữ liệu giả.

## Ngôn ngữ sử dụng
- SQL

## Người làm
- Tống Đức Hùng
