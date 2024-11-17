package com.example.demo.Model.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Entity đại diện cho bảng auditoriums.
 */
@Entity
@Table(name = "auditoriums")
public class AuditoriumEntity {

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditorium_id", nullable = false, unique = true)
    private int auditoriumId;

    /**
     * Liên kết với bảng theater thông qua theater_id.
     */
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private TheaterEntity theater;

    /**
     * Tên của auditorium.
     */
    @Column(name = "auditorium_name", nullable = false, length = 255)
    private String auditoriumName;

    /**
     * Số chỗ ngồi của auditorium.
     */
    @Column(name = "auditorium_seats", nullable = false)
    private int auditoriumSeats;

    /**
     * Liên kết với bảng showtimes thông qua thuộc tính auditorium.
     */
    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowtimeEntity> showtimes = new ArrayList<>();

    /**
     * Danh sách ghế liên kết với auditorium.
     */
    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatEntity> seats = new ArrayList<>();

    /**
     * Constructor mặc định.
     */
    public AuditoriumEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param theater Đối tượng theater liên kết.
     * @param auditoriumName Tên của auditorium.
     * @param auditoriumSeats Số chỗ ngồi của auditorium.
     */
    public AuditoriumEntity(TheaterEntity theater, String auditoriumName, int auditoriumSeats) {
        this.theater = theater;
        this.auditoriumName = auditoriumName;
        this.auditoriumSeats = auditoriumSeats;
    }

    // Các hàm getter và setter.

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public TheaterEntity getTheater() {
        return theater;
    }

    public void setTheater(TheaterEntity theater) {
        this.theater = theater;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public int getAuditoriumSeats() {
        return auditoriumSeats;
    }

    public void setAuditoriumSeats(int auditoriumSeats) {
        this.auditoriumSeats = auditoriumSeats;
    }

    /**
     * Lấy danh sách showtimes liên kết.
     *
     * @return danh sách showtimes liên kết.
     */
    public List<ShowtimeEntity> getShowtimes() {
        return showtimes;
    }

    /**
     * Lấy danh sách ghế liên kết.
     *
     * @return danh sách ghế liên kết.
     */
    public List<SeatEntity> getSeats() {
        return seats;
    }
}
