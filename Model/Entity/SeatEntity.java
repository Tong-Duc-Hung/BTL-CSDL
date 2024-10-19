package com.example.movieticketbooking.Model.Entity;

import java.util.*;
import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng seats.
*/
@Entity
@Table(name = "seats")
public class SeatEntity {

    /**
     * Enum đại diện cho loại chỗ ngồi.
    */
    public enum Seat_type {
        Regular,
        Vip
    }

    /**
     * Enum đại diện cho trạng thái chỗ ngồi.
    */
    public enum Seat_status {
        Booked,
        Available
    }

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id", nullable=false, unique=true)
    private int seat_id;

    /**
     * Liên kết với bảng theater thông qua theater_id.
    */
    @ManyToOne
    @JoinColumn(name="theater_id", nullable=false)
    private TheaterEntity theater;

    /**
     * Liên kết với bảng auditorium thông qua auditorium_id.
    */
    @ManyToOne
    @JoinColumn(name="auditorium_id", nullable=false)
    private AuditoriumEntity auditorium;

    /**
     * Liên kết với bảng showtime thông qua showtime_id.
    */
    @ManyToOne
    @JoinColumn(name="showtime_id", nullable=false)
    private ShowtimeEntity showtime;

    /**
     * Số hiệu ghế ngồi.
    */
    @Column(name="seat_number", nullable=false)
    private String seat_number;

    /**
     * Loại chỗ ngồi.
    */
    @Enumerated(EnumType.STRING)
    @Column(name="seat_type", nullable=false)
    private Seat_type seat_type;

    /**
     * Trạng thái chỗ ngồi.
    */
    @Enumerated(EnumType.STRING)
    @Column(name="seat_status", nullable=false)
    private Seat_status seat_status;

    /**
     * Giá của chỗ ngồi.
    */
    @Column(name="seat_price", nullable=false)
    private int seat_price;

    /**
     * Liên kết với bảng showtimes thông qua thuộc tính seat.
    */
    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowtimeEntity> showtimes = new ArrayList<>();

    /**
     * Constructor mặc định.
    */
    public SeatEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param theater Đối tượng theater liên kết.
     * @param auditorium Đối tượng auditorium liên kết.
     * @param showtime Đối tượng showtime liên kết.
     * @param seat_number Số hiệu ghế ngồi.
     * @param seat_type Loại chỗ ngồi.
     * @param seat_status Trạng thái chỗ ngồi.
     * @param seat_price Giá của chỗ ngồi.
    */
    public SeatEntity(TheaterEntity theater, AuditoriumEntity auditorium, ShowtimeEntity showtime, String seat_number, Seat_type seat_type, Seat_status seat_status, int seat_price) {
        this.theater = theater;
        this.auditorium = auditorium;
        this.showtime = showtime;
        this.seat_number = seat_number;
        this.seat_type = seat_type;
        this.seat_status = seat_status;
        this.seat_price = seat_price;
    }

    // Các hàm getter và setter.

    public int getSeat_id() {
        return seat_id;
    }
    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
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

    public ShowtimeEntity getShowtime() {
        return showtime;
    }
    public void setShowtime(ShowtimeEntity showtime) {
        this.showtime = showtime;
    }

    public String getSeat_number() {
        return seat_number;
    }
    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public Seat_type getSeat_type() {
        return seat_type;
    }
    public void setSeat_type(Seat_type seat_type) {
        this.seat_type = seat_type;
    }

    public Seat_status getSeat_status() {
        return seat_status;
    }
    public void setSeat_status(Seat_status seat_status) {
        this.seat_status = seat_status;
    }

    public int getSeat_price() {
        return seat_price;
    }
    public void setSeat_price(int seat_price) {
        this.seat_price = seat_price;
    }

    /**
     * Lấy danh sách showtimes liên kết.
     *
     * @return danh sách showtimes liên kết.
    */
    public List<ShowtimeEntity> getSeats() {
        return showtimes;
    }
}