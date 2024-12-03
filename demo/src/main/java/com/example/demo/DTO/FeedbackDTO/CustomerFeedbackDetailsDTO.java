package com.example.demo.DTO.FeedbackDTO;

public class CustomerFeedbackDetailsDTO {
    private int feedbackId; // Mã phản hồi
    private int userId; // Mã người dùng
    private String feedbackText; // Nội dung phản hồi

    // Hàm khởi tạo không tham số
    public CustomerFeedbackDetailsDTO() {}

    // Hàm khởi tạo có tham số
    public CustomerFeedbackDetailsDTO(int feedbackId, int userId, String feedbackText) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.feedbackText = feedbackText;
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
}
