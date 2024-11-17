package com.example.demo.Model.Entity;

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
    private int theaterId;

    /**
     * Tên của rạp chiếu phim.
     */
    @Column(name = "theater_name", nullable = false, length = 255)
    private String theaterName;

    /**
     * Địa chỉ của rạp chiếu phim.
     */
    @Column(name = "location", nullable = false, length = 255)
    private String location;

    /**
     * Tổng số phòng chiếu trong rạp.
     */
    @Column(name = "total_auditoriums", nullable = false)
    private int totalAuditoriums;

    /**
     * Thông tin liên hệ của rạp chiếu phim.
     */
    @Column(name = "contact_info", length = 255)
    private String contactInfo;

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
     * @param theaterName Tên của rạp chiếu phim.
     * @param location Địa chỉ của rạp chiếu phim.
     * @param totalAuditoriums Tổng số phòng chiếu trong rạp.
     * @param contactInfo Thông tin liên hệ của rạp chiếu phim.
     * @param amenities Các tiện nghi của rạp chiếu phim.
     */
    public TheaterEntity(String theaterName, String location, int totalAuditoriums, String contactInfo, String amenities) {
        this.theaterName = theaterName;
        this.location = location;
        this.totalAuditoriums = totalAuditoriums;
        this.contactInfo = contactInfo;
        this.amenities = amenities;
    }

    // Các hàm getter và setter.

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalAuditoriums() {
        return totalAuditoriums;
    }

    public void setTotalAuditoriums(int totalAuditoriums) {
        this.totalAuditoriums = totalAuditoriums;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public List<AuditoriumEntity> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<AuditoriumEntity> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public List<SeatEntity> getSeats() {
        return seats;
    }

    public List<ShowtimeEntity> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<ShowtimeEntity> showtimes) {
        this.showtimes = showtimes;
    }
}
