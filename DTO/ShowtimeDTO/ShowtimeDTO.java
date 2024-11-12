package com.example.demo.DTO.ShowtimeDTO;

import java.sql.Date;
import java.sql.Time;

import java.math.BigDecimal;

public class ShowtimeDTO {
    private int showtimeId; // Mã suất chiếu
    private String movieId; // Mã phim
    private int theaterId; // Mã rạp
    private int auditoriumId; // Mã phòng chiếu
    private Date showDate; // Ngày chiếu
    private Time showTime; // Giờ chiếu
    private BigDecimal timePrice; // Giá vé

    // Hàm khởi tạo không tham số
    public ShowtimeDTO() {}

    // Hàm khởi tạo có tham số
    public ShowtimeDTO(int showtimeId, String movieId, int theaterId, int auditoriumId, Date showDate, Time showTime, BigDecimal timePrice) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.auditoriumId = auditoriumId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.timePrice = timePrice;
    }

    // Các phương thức getter và setter
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
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
}
