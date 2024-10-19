package com.example.movieticketbooking.Model.Entity;

import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng booking_details.
*/
@Entity
@Table(name = "booking_details")
public class Booking_detailEntity {

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_detail_id", nullable = false, unique = true)
    private int booking_detail_id;

    /**
     * Liên kết với bảng booking thông qua booking_id.
    */
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    /**
     * Liên kết với bảng seat thông qua seat_id.
    */
    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private SeatEntity seat;

    /**
     * Constructor mặc định.
    */
    public Booking_detailEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param booking Đối tượng đặt vé liên kết.
     * @param seat Đối tượng chỗ ngồi liên kết.
    */
    public Booking_detailEntity(BookingEntity booking, SeatEntity seat) {
        this.booking = booking;
        this.seat = seat;
    }

    // Các hàm getter và setter.

    public int getBooking_detail_id() {
        return booking_detail_id;
    }

    public void setBooking_detail_id(int booking_detail_id) {
        this.booking_detail_id = booking_detail_id;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public SeatEntity getSeat() {
        return seat;
    }

    public void setSeat(SeatEntity seat) {
        this.seat = seat;
    }
}
