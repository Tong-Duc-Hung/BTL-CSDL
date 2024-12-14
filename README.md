# BTL-CSDL
## Mục lục
1. [Các folder và chức năng của các folder trong backend](#các-folder-và-chức-năng-của-các-folder-trong-backend) 
2. [Các hàm và các chức năng của các hàm trong Controller](#các-hàm-và-các-chức-năng-của-các-hàm-trong-controller)
3. [Cấu hình](#cấu-hình)
4. [Tài liệu hỗ trợ](#tài-liệu-hỗ-trợ)
5. [Ngôn ngữ sử dụng](#ngôn-ngữ-sử-dụng)
6. [Người làm](#người-làm)

## Các folder và chức năng của các folder trong backend
### Config
- Folder này chịu trách nhiệm cho việc phân quyền trong web.
### Model/Entity
- Folder này chứa các lớp đối tượng và thực thể ánh xạ với cơ sở dữ liệu.
### DTO
- Folder này chứa các lớp Data Transfer Objects, sử dụng để trao đổi dữ liệu giữa các lớp và dịch vụ.
### Service
- Folder này chứa các lớp dịch vụ xử lý logic nghiệp vụ.
### Controller
- Folder này chứa các lớp điều khiển, xử lí các yêu cầu HTTP.
### Exception
- Folder này chức các lớp xử lí ngoại lệ.
### Repository
- Folder này chức các lớp giao tiếp với cơ sở dữ liệu, sử dụng Spring Data JPA.
### Mapper
- Folder này chứa các lớp ánh xạ dữ liệu giữa DTO và các thực thể.
## Các hàm và các chức năng của các hàm trong Controller
### **AuditoriumController.java**
1. **`createAuditorium`**: Tạo mới một phòng chiếu.  
2. **`getAuditoriumById`**: Lấy thông tin phòng chiếu theo ID.  
3. **`deleteAuditorium`**: Xóa một phòng chiếu theo ID.  
4. **`getAllAuditoriums`**: Lấy danh sách tất cả các phòng chiếu.  
### **BookingController.java**
1. **`createBooking`**: Tạo một đơn đặt vé mới.  
2. **`getBookingById`**: Lấy thông tin đặt vé theo ID.  
3. **`getAllBookings`**: Lấy danh sách tất cả các đơn đặt vé.  
4. **`cancelBooking`**: Hủy đơn đặt vé theo ID.  
5. **`getBookingsByUserId`**: Lấy danh sách đơn đặt vé theo ID người dùng.  

### **FeedbackController.java**
1. **`createCustomerFeedback`**: Tạo phản hồi từ khách hàng.  
2. **`getCustomerFeedbackById`**: Lấy phản hồi của khách hàng theo ID.  
3. **`updateStaffResponse`**: Cập nhật phản hồi của nhân viên cho ý kiến của khách hàng.  
4. **`deleteFeedback`**: Xóa phản hồi theo ID.  
5. **`getAllCustomerFeedbacks`**: Lấy danh sách tất cả các phản hồi từ khách hàng.  
6. **`getAllStaffResponses`**: Lấy danh sách tất cả phản hồi từ nhân viên.  

---

### **MovieController.java**
1. **`createMovie`**: Tạo thông tin phim mới.  
2. **`deleteMovie`**: Xóa phim theo ID.  
3. **`getAllMovies`**: Lấy danh sách tất cả các phim.  
4. **`getMovieById`**: Lấy thông tin phim theo ID.  
5. **`findMoviesByReleaseDate`**: Tìm phim theo ngày phát hành.  
6. **`findMoviesByGenre`**: Tìm phim theo thể loại.  
7. **`findMoviesByRating`**: Tìm phim theo đánh giá.  
8. **`findMovies`**: Tìm kiếm phim dựa trên các tiêu chí khác.  

---

### **PaymentController.java**
1. **`createInitialPayment`**: Tạo một thanh toán ban đầu.  
2. **`processPayment`**: Xử lý thanh toán.  
3. **`getPaymentById`**: Lấy thông tin thanh toán theo ID.  
4. **`getAllPayments`**: Lấy danh sách tất cả các thanh toán.  

---

### **ReviewController.java**
1. **`createReview`**: Tạo một đánh giá phim.  
2. **`getReviewById`**: Lấy đánh giá phim theo ID.  
3. **`updateReview`**: Cập nhật đánh giá phim.  
4. **`deleteReview`**: Xóa đánh giá phim theo ID.  
5. **`getAllReviews`**: Lấy danh sách tất cả các đánh giá.  
6. **`getReviewsByMovieId`**: Lấy danh sách đánh giá theo ID phim.  

---

### **SeatController.java**
1. **`createSeat`**: Tạo thông tin chỗ ngồi.  
2. **`getSeatById`**: Lấy thông tin chỗ ngồi theo ID.  
3. **`deleteSeat`**: Xóa chỗ ngồi theo ID.  
4. **`getAllSeats`**: Lấy danh sách tất cả chỗ ngồi.  
5. **`getSeatsByAuditoriumId`**: Lấy danh sách chỗ ngồi theo ID phòng chiếu.  

---

### **ShowtimeController.java**
1. **`createShowtime`**: Tạo một suất chiếu mới.  
2. **`getShowtimeById`**: Lấy thông tin suất chiếu theo ID.  
3. **`deleteShowtimeById`**: Xóa suất chiếu theo ID.  
4. **`getAuditoriumByShowtimeId`**: Lấy phòng chiếu theo ID suất chiếu.  
5. **`getShowtimesByMovieId`**: Lấy danh sách suất chiếu theo ID phim.  
6. **`getTheaterByShowtimeId`**: Lấy rạp chiếu theo ID suất chiếu.  
7. **`getAvailableSeatsByShowtimeId`**: Lấy danh sách ghế trống theo ID suất chiếu.  
8. **`getTotalSeatsByShowtimeId`**: Lấy tổng số ghế theo ID suất chiếu.  

---

### **SupportedBankController.java**
1. **`getAllSupportedBanks`**: Lấy danh sách tất cả các ngân hàng được hỗ trợ.  
2. **`createSupportedBank`**: Thêm một ngân hàng hỗ trợ mới.  
3. **`deleteSupportedBank`**: Xóa ngân hàng hỗ trợ theo ID.  

---

### **TheaterController.java**
1. **`createTheater`**: Tạo thông tin rạp chiếu mới.  
2. **`deleteTheater`**: Xóa rạp chiếu theo ID.  
3. **`getTheaterById`**: Lấy thông tin rạp chiếu theo ID.  
4. **`getAllTheaters`**: Lấy danh sách tất cả các rạp chiếu.  
5. **`searchTheaterByName`**: Tìm rạp chiếu theo tên.  
6. **`getAuditoriumsByTheaterId`**: Lấy danh sách phòng chiếu theo ID rạp.  

---

### **TicketController.java**
1. **`createTicket`**: Tạo thông tin vé.  
2. **`getTicketById`**: Lấy thông tin vé theo ID.  
3. **`cancelTicketForCustomer`**: Hủy vé theo yêu cầu khách hàng.  
4. **`cancelTicketAdmin`**: Hủy vé do admin yêu cầu.  
5. **`deleteTicket`**: Xóa vé theo ID.  
6. **`getTicketsByBookingId`**: Lấy danh sách vé theo ID đặt vé.  
7. **`getAllTickets`**: Lấy danh sách tất cả các vé.  

---

### **UserController.java**
1. **`registerUser`**: Đăng ký tài khoản mới.  
2. **`login`**: Đăng nhập.  
3. **`logout`**: Đăng xuất.  
4. **`getUserProfile`**: Lấy thông tin người dùng.  
5. **`getUserRole`**: Lấy vai trò người dùng.  
6. **`updateUser`**: Cập nhật thông tin người dùng.  
7. **`changePassword`**: Đổi mật khẩu.  
8. **`getAllUsers`**: Lấy danh sách tất cả người dùng.  
9. **`deleteUser`**: Xóa người dùng theo ID.  
10. **`findUserByUserName`**: Tìm kiếm người dùng theo tên tài khoản.  

## Cấu hình
### application.properties trong folder resources chứa các phần: 
- Tên web: Đặt tên cho ứng dụng web.
- Cổng server: Xác định cổng mà ứng dụng sẽ chạy.
- Kết nối đến MySQL: Cấu hình URL kết nối, tên người dùng và mật khẩu để kết nối đến cơ sở dữ liệu MySQL.
- Cấu hình JPA: Tự động cập nhật schema cơ sở dữ liệu, Hiển thị câu lệnh SQL, Tối ưu Hibernate cho MySQL, Xác định chiến lược đặt tên cho các bảng và cột trong cơ sở dữ liệu.
- Thời gian timeout cho session: Xác định thời gian timeout cho session của người dùng là 30 phút.
### pom.xml
- Spring Boot Starter Web: Dùng để xây dựng các ứng dụng web, bao gồm RESTful APIs.
- Spring Boot Starter Test: Cung cấp các công cụ và thư viện để kiểm thử ứng dụng Spring Boot.
- MapStruct: Thư viện dùng để ánh xạ dữ liệu giữa các lớp DTO và thực thể.
- MapStruct Processor: Cung cấp các công cụ xử lí ánh xạ dữ liệu của MapStruct.
- Spring Data JPA: Dùng để làm việc với cơ sở dữ liệu qua JPA (Java Persistence API).
- MySQL Connector: Kết nối và tương tác với cơ sở dữ liệu MySQL.
- Jakarta Bean Validation: Thư viện dùng để kiểm tra tính hợp lệ của dữ liệu.
- Hibernate Commons Annotations: Thư viện hỗ trợ các anotations của Hibernate.
- Spring Boot Maven Plugin: Plugin này giúp quản lý quá trình build và chạy ứng dụng Spring Boot.
## Tài liệu hỗ trợ
- Các AI dùng: ChatGPT, Copilot AI: hỗ trợ viết code.
- Github: tham khảo cấu trúc của project trên github.
## Ngôn ngữ sử dụng
- Ngôn ngữ: java.
- Framework: Spring Boot.
## Người làm
- Tống Đức Hùng.
