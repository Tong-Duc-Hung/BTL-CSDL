package com.example.demo.DTO.SeatDTO;

import com.example.demo.Model.Entity.SeatEntity.*;

import java.math.BigDecimal;
public class SeatDTO {
    private int seatId; // Mã ghế
    private int theaterId; // Mã rạp
    private int auditoriumId; // Mã phòng chiếu
    private String seatNumber; // Số ghế
    private SeatType seatType; // Loại ghế
    private BigDecimal seatPrice; // Giá ghế

    // Hàm khởi tạo không tham số
    public SeatDTO() {}

    // Hàm khởi tạo có tham số
    public SeatDTO(int seatId, int theaterId, int auditoriumId, String seatNumber, SeatType seatType, BigDecimal seatPrice) {
        this.seatId = seatId;
        this.theaterId = theaterId;
        this.auditoriumId = auditoriumId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatPrice = seatPrice;
    }

    // Các phương thức getter và setter
    public int getSeatId() {
        return seatId;
    }
    
    public void setSeatId(int seatId) {
        this.seatId = seatId;
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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(BigDecimal seatPrice) {
        this.seatPrice = seatPrice;
    }
}
