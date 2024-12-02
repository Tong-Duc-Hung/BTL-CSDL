package com.example.demo.DTO.TheaterDTO;

public class TheaterDTO {
    private int theaterId; // Mã rạp
    private String theaterName; // Tên rạp
    private String location; // Địa điểm
    private int totalAuditoriums; // Tổng số phòng chiếu
    private String contactInfo; // Thông tin liên hệ
    private String amenities; // Các tiện ích

    // Hàm khởi tạo không tham số
    public TheaterDTO() {}

    // Hàm khởi tạo có tham số
    public TheaterDTO(int theaterId, String theaterName, String location, int totalAuditoriums, String contactInfo, String amenities) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.location = location;
        this.totalAuditoriums = totalAuditoriums;
        this.contactInfo = contactInfo;
        this.amenities = amenities;
    }

    // Các phương thức getter và setter
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
}
