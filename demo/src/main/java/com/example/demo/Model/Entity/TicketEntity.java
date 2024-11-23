package com.example.demo.Model.Entity;

import java.math.BigDecimal;
import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng tickets.
 */
@Entity
@Table(name = "tickets")
public class TicketEntity {

    /**
     * Enum đại diện cho trạng thái vé.
     */
    public enum TicketStatus {
        confirmed,
        canceled
    }

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false, unique = true)
    private int ticketId;

    /**
     * Liên kết với bảng showtimes thông qua showtime_id.
     */
    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private ShowtimeEntity showtime;

    /**
     * Liên kết với bảng seats thông qua seat_id.
     */
    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private SeatEntity seat;

    /**
     * Liên kết với bảng bookings thông qua booking_id.
     */
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    /**
     * Trạng thái vé.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status", nullable = false)
    private TicketStatus ticketStatus;

    /**
     * Giá vé.
     */
    @Column(name = "ticket_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    /**
     * Constructor mặc định.
     */
    public TicketEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param showtime Đối tượng showtime liên kết.
     * @param seat Đối tượng seat liên kết.
     * @param booking Đối tượng booking liên kết.
     * @param ticketStatus Trạng thái vé.
     * @param ticketPrice Giá vé.
     */
    public TicketEntity(ShowtimeEntity showtime, SeatEntity seat, BookingEntity booking, TicketStatus ticketStatus, BigDecimal ticketPrice) {
        this.showtime = showtime;
        this.seat = seat;
        this.booking = booking;
        this.ticketStatus = ticketStatus;
        this.ticketPrice = ticketPrice;
    }

    // Các hàm getter và setter.

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public ShowtimeEntity getShowtime() {
        return showtime;
    }

    public void setShowtime(ShowtimeEntity showtime) {
        this.showtime = showtime;
    }

    public SeatEntity getSeat() {
        return seat;
    }

    public void setSeat(SeatEntity seat) {
        this.seat = seat;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}