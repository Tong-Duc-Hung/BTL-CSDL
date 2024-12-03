package com.example.demo.DTO.BookingDTO;

import java.sql.Date;

import com.example.demo.Model.Entity.BookingEntity.BookingStatus;

public class BookingDTO {
    private int bookingId; // Mã booking
    private int userId; // Mã người dùng
    private int showtimeId; // Mã suất chiếu
    private Date bookingDate; // Ngày đặt chỗ
    private BookingStatus bookingStatus; // Trạng thái đặt chỗ
    private int seatsBooked; // Số ghế đã đặt
    private int originalSeatsBooked; // Số ghế ban đầu đã đặt

    // Hàm khởi tạo không tham số
    public BookingDTO() {}

    // Hàm khởi tạo có tham số
    public BookingDTO(int bookingId, int userId, int showtimeId, Date bookingDate, BookingStatus bookingStatus, int seatsBooked, int originalSeatsBooked) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.seatsBooked = seatsBooked;
        this.originalSeatsBooked = originalSeatsBooked;
    }

    // Các phương thức getter và setter
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
    public int getOriginalSeatsBooked() {
        return originalSeatsBooked;
    }
    public void setOriginalSeatsBooked(int originalSeatsBooked) {
        this.originalSeatsBooked = originalSeatsBooked;
    }
}
