package com.example.demo.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * Lớp Entity đại diện cho bảng bookings.
 */
@Entity
@Table(name = "bookings")
public class BookingEntity {

    /**
     * Enum đại diện cho trạng thái đặt vé.
     */
    public enum BookingStatus {
        confirmed,
        canceled
    }

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false, unique = true)
    private int bookingId;

    /**
     * Liên kết với bảng user thông qua user_id.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * Liên kết với bảng showtime thông qua showtime_id.
     */
    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private ShowtimeEntity showtime;

    /**
     * Thuộc tính ngày đặt vé.
     */
    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    /**
     * Thuộc tính trạng thái đặt vé.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus bookingStatus;

    /**
     * Thuộc tính số chỗ đã đặt.
     */
    @Column(name = "seats_booked", nullable = false)
    private int seatsBooked;

    /**
     * Thuộc tính số chỗ ban đầu đã đặt.
     */
    @Column(name = "original_seats_booked", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int originalSeatsBooked;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PaymentEntity payment;

    /**
     * Constructor mặc định.
     */
    public BookingEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param user        Đối tượng user liên kết.
     * @param showtime    Đối tượng showtime liên kết.
     * @param bookingDate Ngày đặt vé.
     * @param bookingStatus Trạng thái đặt vé.
     * @param seatsBooked  Số chỗ đã đặt.
     * @param originalSeatsBooked Số chỗ ban đầu đã đặt.
     */
    public BookingEntity(UserEntity user, ShowtimeEntity showtime, Date bookingDate, BookingStatus bookingStatus, int seatsBooked, int originalSeatsBooked) {
        this.user = user;
        this.showtime = showtime;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.seatsBooked = seatsBooked;
        this.originalSeatsBooked = originalSeatsBooked;
    }

    // Các hàm getter và setter.

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
}
