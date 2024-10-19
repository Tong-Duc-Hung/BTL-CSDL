package com.example.movieticketbooking.Model.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Entity đại diện cho bảng theaters.
*/
@Entity
@Table(name = "theaters")
public class TheaterEntity {

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id", nullable = false, unique = true)
    private int theater_id;

    /**
     * Tên của rạp chiếu phim.
    */
    @Column(name = "theater_name", length = 255)
    private String theater_name;

    /**
     * Địa chỉ của rạp chiếu phim.
    */
    @Column(name = "location", length = 255)
    private String location;

    /**
     * Tổng số phòng chiếu trong rạp.
    */
    @Column(name = "total_auditoriums")
    private int total_auditoriums;

    /**
     * Thông tin liên hệ của rạp chiếu phim.
    */
    @Column(name = "contact_info", length = 255)
    private String contact_info;

    /**
     * Các tiện nghi của rạp chiếu phim.
    */
    @Column(name = "amenities", length = 255)
    private String amenities;

    /**
     * Danh sách các phòng chiếu liên kết với rạp chiếu phim.
    */
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuditoriumEntity> auditoriums = new ArrayList<>();

    /**
     * Danh sách các ghế ngồi liên kết với rạp chiếu phim.
    */
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatEntity> seats = new ArrayList<>();

    /**
     * Danh sách các buổi chiếu liên kết với rạp chiếu phim.
    */
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowtimeEntity> showtimes = new ArrayList<>();

    /**
     * Constructor mặc định.
    */
    public TheaterEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param theater_name Tên của rạp chiếu phim.
     * @param location Địa chỉ của rạp chiếu phim.
     * @param total_auditoriums Tổng số phòng chiếu trong rạp.
     * @param contact_info Thông tin liên hệ của rạp chiếu phim.
     * @param amenities Các tiện nghi của rạp chiếu phim.
    */
    public TheaterEntity(String theater_name, String location, int total_auditoriums, String contact_info, String amenities) {
        this.theater_name = theater_name;
        this.location = location;
        this.total_auditoriums = total_auditoriums;
        this.contact_info = contact_info;
        this.amenities = amenities;
    }

    // Các hàm getter và setter.

    public int getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(int theater_id) {
        this.theater_id = theater_id;
    }

    public String getTheater_name() {
        return theater_name;
    }

    public void setTheater_name(String theater_name) {
        this.theater_name = theater_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotal_auditoriums() {
        return total_auditoriums;
    }

    public void setTotal_auditoriums(int total_auditoriums) {
        this.total_auditoriums = total_auditoriums;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    /**
     * Lấy danh sách auditoriums liên kết.
     *
     * @return danh sách auditoriums liên kết.
    */
    public List<AuditoriumEntity> getAuditoriums() {
        return auditoriums;
    }

    /**
     * Lấy danh sách seats liên kết.
     *
     * @return danh sách seats liên kết.
    */
    public List<SeatEntity> getSeats() {
        return seats;
    }

    /**
     * Lấy danh sách showtimes liên kết.
     *
     * @return danh sách showtimes liên kết.
    */
    public List<ShowtimeEntity> getShowtimes() {
        return showtimes;
    }
    public void setAuditoriums(List<AuditoriumEntity> auditoriums) {
        this.auditoriums = auditoriums;
    }
}