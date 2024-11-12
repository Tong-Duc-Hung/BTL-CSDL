package com.example.demo.DTO.BookingDTO;

public class CancelBookingDTO {
    private int bookingId; // Mã booking
    private String reason; // Lý do hủy

    // Hàm khởi tạo không tham số
    public CancelBookingDTO() {}

    // Hàm khởi tạo có tham số
    public CancelBookingDTO(int bookingId, String reason) {
        this.bookingId = bookingId;
        this.reason = reason;
    }

    // Các phương thức getter và setter
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
