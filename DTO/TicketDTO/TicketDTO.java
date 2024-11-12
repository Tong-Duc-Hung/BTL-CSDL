package com.example.demo.DTO.TicketDTO;

import com.example.demo.Model.Entity.TicketEntity.TicketStatus;

import java.math.BigDecimal;

public class TicketDTO {
    private int ticketId; // Mã vé
    private int showtimeId; // Mã suất chiếu
    private int seatId; // Mã ghế
    private int bookingId; // Mã booking
    private BigDecimal ticketPrice; // Giá vé
    private TicketStatus ticketStatus; // Trạng thái vé

    // Hàm khởi tạo không tham số
    public TicketDTO() {}

    // Hàm khởi tạo có tham số
    public TicketDTO(int ticketId, int showtimeId, int seatId, int bookingId, BigDecimal ticketPrice, TicketStatus ticketStatus) {
        this.ticketId = ticketId;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
        this.bookingId = bookingId;
        this.ticketPrice = ticketPrice;
        this.ticketStatus = ticketStatus;
    }

    // Các phương thức getter và setter
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
