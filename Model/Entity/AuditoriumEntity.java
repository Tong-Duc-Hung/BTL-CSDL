package com.example.movieticketbooking.Model.Entity;

import java.util.*;
import jakarta.persistence.*;

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
    private int auditorium_id;

    /**
     * Liên kết với bảng theater thông qua theater_id.
    */
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private TheaterEntity theater;

    /**
     * Tên của auditorium.
    */
    @Column(name = "auditorium_name", length = 255)
    private String auditorium_name;

    /**
     * Số chỗ ngồi của auditorium.
    */
    @Column(name = "auditorium_seats")
    private int auditorium_seats;

    /**
     * Liên kết với bảng showtimes thông qua thuộc tính auditorium.
    */
    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowtimeEntity> showtimes = new ArrayList<>();

    /**
     * Constructor mặc định.
    */
    public AuditoriumEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param theater Đối tượng theater liên kết.
     * @param auditorium_name Tên của auditorium.
     * @param auditorium_seats Số chỗ ngồi của auditorium.
    */
    public AuditoriumEntity(TheaterEntity theater, String auditorium_name, Integer auditorium_seats) {
        this.theater = theater;
        this.auditorium_name = auditorium_name;
        this.auditorium_seats = auditorium_seats;
    }

    // Các hàm getter và setter.

    public int getAuditorium_id() {
        return auditorium_id;
    }

    public void setAuditorium_id(int auditorium_id) {
        this.auditorium_id = auditorium_id;
    }

    public TheaterEntity getTheater() {
        return theater;
    }

    public void setTheater(TheaterEntity theater) {
        this.theater = theater;
    }


    public String getAuditorium_name() {
        return auditorium_name;
    }


    public void setAuditorium_name(String auditorium_name) {
        this.auditorium_name = auditorium_name;
    }

    public int getAuditorium_seats() {
        return auditorium_seats;
    }

    public void setAuditorium_seats(int auditorium_seats) {
        this.auditorium_seats = auditorium_seats;
    }

    /**
     * Lấy danh sách showtimes liên kết.
     *
     * @return danh sách showtimes liên kết.
    */
    public List<ShowtimeEntity> getShowtimes() {
        return showtimes;
    }
}