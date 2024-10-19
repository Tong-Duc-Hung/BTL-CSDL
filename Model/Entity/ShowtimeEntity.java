package com.example.movieticketbooking.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Entity đại diện cho bảng showtimes.
*/
@Entity
@Table(name = "showtimes",
       uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "theater_id", "show_date", "show_time"}))
public class ShowtimeEntity {

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id", nullable = false, unique = true)
    private int showtime_id;

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
    @Temporal(TemporalType.DATE)
    @Column(name = "show_date", nullable = false)
    private Date show_date;

    /**
     * Giờ chiếu phim.
    */
    @Temporal(TemporalType.TIME)
    @Column(name = "show_time", nullable = false)
    private Time show_time;

    /**
     * Giá vé chiếu phim.
    */
    @Column(name = "time_price", nullable = false, precision = 10, scale = 2)
    private double time_price;

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
     * @param show_date Ngày chiếu phim.
     * @param show_time Giờ chiếu phim.
     * @param time_price Giá vé chiếu phim.
    */
    public ShowtimeEntity(MovieEntity movie, TheaterEntity theater, AuditoriumEntity auditorium, Date show_date, Time show_time, double time_price) {
        this.movie = movie;
        this.theater = theater;
        this.auditorium = auditorium;
        this.show_date = show_date;
        this.show_time = show_time;
        this.time_price = time_price;
    }

    // Các hàm getter và setter.
    
    public int getShowtime_id() {
        return showtime_id;
    }

    public void setShowtime_id(int showtime_id) {
        this.showtime_id = showtime_id;
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

    public void setAuditorium(AuditoriumEntity auditorium)  {
        this.auditorium = auditorium;
    }

    public Date getShow_date() {
        return show_date;
    }

    public void setShow_date(Date show_date) {
        this.show_date = show_date;
    }

    public Time getShow_time() {
        return show_time;
    }

    public void setShow_time(Time show_time) {
        this.show_time = show_time;
    }

    public double getTime_price() {
        return time_price;
    }

    public void setTime_price(double time_price) {
        this.time_price = time_price;
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