package com.example.demo.Model.Entity;

import java.math.BigDecimal;
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
    public enum SeatType {
        regular,
        vip
    }

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false, unique = true)
    private int seatId;

    /**
     * Liên kết với bảng theater thông qua theater_id.
     */
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private TheaterEntity theater;

    /**
     * Liên kết với bảng auditorium thông qua auditorium_id.
     */
    @ManyToOne
    @JoinColumn(name = "auditorium_id", nullable = false)
    private AuditoriumEntity auditorium;

    /**
     * Số hiệu ghế ngồi.
     */
    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    /**
     * Loại chỗ ngồi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    private SeatType seatType;

    /**
     * Giá của chỗ ngồi.
     */
    @Column(name = "seat_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal seatPrice;

    /**
     * Constructor mặc định.
     */
    public SeatEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param theater Đối tượng theater liên kết.
     * @param auditorium Đối tượng auditorium liên kết.
     * @param seatNumber Số hiệu ghế ngồi.
     * @param seatType Loại chỗ ngồi.
     * @param seatPrice Giá của chỗ ngồi.
     */
    public SeatEntity(TheaterEntity theater, AuditoriumEntity auditorium, String seatNumber, SeatType seatType, BigDecimal seatPrice) {
        this.theater = theater;
        this.auditorium = auditorium;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatPrice = seatPrice;
    }

    // Các hàm getter và setter.

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
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
