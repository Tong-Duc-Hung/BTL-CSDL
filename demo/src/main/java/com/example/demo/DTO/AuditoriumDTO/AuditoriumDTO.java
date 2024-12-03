package com.example.demo.DTO.AuditoriumDTO;

public class AuditoriumDTO {
    private int auditoriumId; // Mã phòng chiếu
    private int theaterId; // Mã rạp
    private String auditoriumName; // Tên phòng chiếu
    private int auditoriumSeats; // Số ghế trong phòng chiếu

    // Hàm khởi tạo không tham số
    public AuditoriumDTO() {}

    // Hàm khởi tạo có tham số
    public AuditoriumDTO(int auditoriumId, int theaterId, String auditoriumName, int auditoriumSeats) {
        this.auditoriumId = auditoriumId;
        this.theaterId = theaterId;
        this.auditoriumName = auditoriumName;
        this.auditoriumSeats = auditoriumSeats;
    }

    // Các phương thức getter và setter
    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
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
}
