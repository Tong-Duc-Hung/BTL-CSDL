package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.BookingDTO.*;
import com.example.demo.Service.BookingService;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Tạo đặt vé mới - chỉ cho phép vai trò CUSTOMER.
     * 
     * @param CreatebookingDTO Thông tin đặt vé cần tạo.
     * @return Thông tin đặt vé vừa được tạo.
     */
    @RequiresRole(Role.customer)
    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody CreateBookingDTO CreatebookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(CreatebookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    /**
     * Xem thông tin chi tiết đặt vé - chỉ cho phép vai trò CUSTOMER.
     * 
     * @param bookingId ID của đặt vé.
     * @param userId ID của người dùng.
     * @return Thông tin đặt vé tương ứng với ID và người dùng.
     */
    @RequiresRole(Role.customer)
    @GetMapping("/{userId}/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable int bookingId, @PathVariable int userId) {
        BookingDTO booking = bookingService.getBookingByIdAndUserId(bookingId, userId);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    /**
     * Lấy danh sách tất cả đặt vé - chỉ cho phép vai trò ADMIN và STAFF.
     * 
     * @return Danh sách tất cả các đặt vé.
     */
    @RequiresRole({Role.admin, Role.staff})
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    /**
     * Hủy đặt vé - chỉ cho phép vai trò CUSTOMER hủy đơn của chính họ.
     * 
     * @param cancelBookingDTO Thông tin hủy đặt vé.
     * @return Thông tin đặt vé sau khi hủy.
     */
    @RequiresRole(Role.customer)
    @PutMapping("/cancel")
    public ResponseEntity<BookingDTO> cancelBooking(@RequestBody CancelBookingDTO cancelBookingDTO) {
        BookingDTO canceledBooking = bookingService.cancelBooking(cancelBookingDTO);
        return ResponseEntity.ok(canceledBooking);
    }

    /**
     * Lấy danh sách đặt vé theo userId - chỉ cho phép vai trò CUSTOMER.
     * 
     * @param userId ID của người dùng.
     * @return Danh sách đặt vé tương ứng với userId.
     */
    @RequiresRole(Role.customer)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserId(@PathVariable int userId) {
        List<BookingDTO> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
}
