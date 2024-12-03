package com.example.demo.DTO.TicketDTO;

public class CancelTicketDTO {
    private int bookingId; // Mã booking
    private int ticketId; // Mã vé
    
    // Hàm khởi tạo không tham số
    public CancelTicketDTO() {}

    // Hàm khởi tạo có tham số
    public CancelTicketDTO(int bookingId, int ticketId) {
        this.bookingId = bookingId;
        this.ticketId = ticketId;
    }

    // Các phương thức getter và setter
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
