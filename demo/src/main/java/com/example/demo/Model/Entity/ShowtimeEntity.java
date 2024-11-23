package com.example.demo.Model.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Entity đại diện cho bảng showtimes.
 */
@Entity
@Table(name = "showtimes",
       uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "theater_id", "auditorium_id", "show_date", "show_time"}))
public class ShowtimeEntity {

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id", nullable = false, unique = true)
    private int showtimeId;

    /**
     * Liên kết với bảng movies thông qua movie_id.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    /**
     * Liên kết với bảng theaters thông qua theater_id.
     */
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private TheaterEntity theater;

    /**
     * Liên kết với bảng auditoriums thông qua auditorium_id.
     */
    @ManyToOne
    @JoinColumn(name = "auditorium_id", nullable = false)
    private AuditoriumEntity auditorium;

    /**
     * Ngày chiếu phim.
     */
    @Column(name = "show_date", nullable = false)
    private Date showDate;

    /**
     * Giờ chiếu phim.
     */
    @Column(name = "show_time", nullable = false)
    private Time showTime;

    /**
     * Giá vé chiếu phim.
     */
    @Column(name = "time_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal timePrice;

    /**
     * Liên kết với bảng bookings thông qua thuộc tính showtime.
     */
    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingEntity> bookings = new ArrayList<>();

    /**
     * Liên kết với bảng ticketprices thông qua thuộc tính showtime.
     */
    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TicketEntity> ticketprices = new ArrayList<>();

    /**
     * Constructor mặc định.
     */
    public ShowtimeEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param movie Đối tượng movie liên kết.
     * @param theater Đối tượng theater liên kết.
     * @param auditorium Đối tượng auditorium liên kết.
     * @param showDate Ngày chiếu phim.
     * @param showTime Giờ chiếu phim.
     * @param timePrice Giá vé chiếu phim.
     */
    public ShowtimeEntity(MovieEntity movie, TheaterEntity theater, AuditoriumEntity auditorium, Date showDate, Time showTime, BigDecimal timePrice) {
        this.movie = movie;
        this.theater = theater;
        this.auditorium = auditorium;
        this.showDate = showDate;
        this.showTime = showTime;
        this.timePrice = timePrice;
    }

    // Các hàm getter và setter.

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public TheaterEntity getTheater() {
        return theater;
    }

    public void setTheater(TheaterEntity theater) {
        this.theater = theater;
    }

    public AuditoriumEntity getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(AuditoriumEntity auditorium) {
        this.auditorium = auditorium;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public Time getShowTime() {
        return showTime;
    }

    public void setShowTime(Time showTime) {
        this.showTime = showTime;
    }

    public BigDecimal getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(BigDecimal timePrice) {
        this.timePrice = timePrice;
    }

    /**
     * Lấy danh sách bookings liên kết.
     *
     * @return danh sách bookings liên kết.
     */
    public List<BookingEntity> getBookings() {
        return bookings;
    }

    /**
     * Lấy danh sách ticketprices liên kết.
     *
     * @return danh sách ticketprices liên kết.
     */
    public List<TicketEntity> getTicketPrices() {
        return ticketprices;
    }
}