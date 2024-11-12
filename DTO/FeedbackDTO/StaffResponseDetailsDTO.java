package com.example.demo.DTO.FeedbackDTO;

public class StaffResponseDetailsDTO {
    private int feedbackId; // Mã phản hồi
    private int userId; // Mã người dùng
    private String feedbackText; // Nội dung phản hồi
    private String staffResponse; // Phản hồi của nhân viên

    // Hàm khởi tạo không tham số
    public StaffResponseDetailsDTO() {}

    // Hàm khởi tạo có tham số
    public StaffResponseDetailsDTO(int feedbackId, int userId, String feedbackText, String staffResponse) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.feedbackText = feedbackText;
        this.staffResponse = staffResponse;
    }

    // Các phương thức getter và setter
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getStaffResponse() {
        return staffResponse;
    }

    public void setStaffResponse(String staffResponse) {
        this.staffResponse = staffResponse;
    }
}
