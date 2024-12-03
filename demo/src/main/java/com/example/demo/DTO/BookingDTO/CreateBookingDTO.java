package com.example.demo.DTO.BookingDTO;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import com.example.demo.Model.Entity.BookingEntity.BookingStatus;

public class CreateBookingDTO {
    private int userId; // Mã người dùng
    private int showtimeId; // Mã suất chiếu
    private Date bookingDate; // Ngày đặt booking
    private BookingStatus bookingStatus; // Trạng thái booking
    private int seatsBooked; // Số ghế đã đặt
    private int originalSeatsBooked; // Số ghế ban đầu đã đặt
    private List<Integer> seatIds = new ArrayList<>(); // Danh sách mã ghế

    // Hàm khởi tạo không tham số
    public CreateBookingDTO() {}

    // Hàm khởi tạo có tham số
    public CreateBookingDTO(int userId, int showtimeId, Date bookingDate, BookingStatus bookingStatus, int seatsBooked, int originalSeatsBooked, List<Integer> seatIds) {
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.seatsBooked = seatsBooked;
        this.originalSeatsBooked = originalSeatsBooked;
        this.seatIds = seatIds;
    }

    // Các phương thức getter và setter
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
    public List<Integer> getSeatIds() {
        return seatIds;
    }
    public void setSeatIds(List<Integer> seatIds) {
        this.seatIds = seatIds;
    }
}
