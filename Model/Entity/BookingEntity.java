package com.example.movieticketbooking.Model.Entity;

import java.util.*;
import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng bookings.
*/
@Entity
@Table(name="bookings")
public class BookingEntity {

    /**
     * Enum đại diện cho trạng thái đặt vé.
    */
    public enum Booking_status {
        Confirmed,
        Canceled
    }

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id", nullable=false, unique=true)
    private int booking_id;

    /**
     * Liên kết với bảng user thông qua user_id.
    */
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    /**
     * Liên kết với bảng showtime thông qua showtime_id.
    */
    @ManyToOne
    @JoinColumn(name="showtime_id", nullable=false)
    private ShowtimeEntity showtime;

    /**
     * Thuộc tính ngày đặt vé.
    */
    @Column(name="booking_date")
    private String booking_date;

    /**
     * Thuộc tính trạng thái đặt vé.
    */
    @Enumerated(EnumType.STRING)
    @Column(name="booking_status", nullable=false)
    private Booking_status booking_status;

    /**
     * Thuộc tính số chỗ đã đặt.
    */
    @Column(name="seats_booked")
    private int seats_booked;

    /**
     * Liên kết với bảng booking_details thông qua thuộc tính booking.
    */
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking_detailEntity> booking_details = new ArrayList<>();

    /**
     * Liên kết với bảng payments thông qua thuộc tính booking.
    */
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaymentEntity> payments = new ArrayList<>();

    /**
     * Constructor mặc định.
    */
    public BookingEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param user Đối tượng user liên kết.
     * @param showtime Đối tượng showtime liên kết.
     * @param booking_date Ngày đặt vé.
     * @param booking_status Trạng thái đặt vé.
     * @param seats_booked Số chỗ đã đặt.
    */
    public BookingEntity(UserEntity user, ShowtimeEntity showtime, String booking_date, Booking_status booking_status, int seats_booked) {
        this.user = user;
        this.showtime = showtime;
        this.booking_date = booking_date;
        this.booking_status = booking_status;
        this.seats_booked = seats_booked;
    }

    // Các hàm getter và setter.

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ShowtimeEntity getShowtime() {
        return showtime;
    }

    public void setShowtime(ShowtimeEntity showtime) {
        this.showtime = showtime;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public Booking_status getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(Booking_status booking_status) {
        this.booking_status = booking_status;
    }

    public int getSeats_booked() {
        return seats_booked;
    }

    public void setSeats_booked(int seats_booked) {
        this.seats_booked = seats_booked;
    }

    /**
     * Lấy danh sách chi tiết đặt vé liên kết.
     *
     * @return danh sách chi tiết đặt vé liên kết.
    */
    public List<Booking_detailEntity> bookings() {
        return booking_details;
    }

    /**
     * Lấy danh sách thanh toán liên kết.
     *
     * @return danh sách thanh toán liên kết.
    */
    public List<PaymentEntity> payments() {
        return payments;
    }
}