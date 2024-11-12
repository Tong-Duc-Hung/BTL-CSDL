package com.example.demo.DTO.FeedbackDTO;

import com.example.demo.Model.Entity.FeedbackEntity.FeedbackType;
import com.example.demo.Model.Entity.FeedbackEntity.FeedbackStatus;

public class CreateCustomerFeedbackDTO {
    private int userId; // Mã người dùng
    private String feedbackText; // Nội dung phản hồi
    private FeedbackType feedbackType; // Loại phản hồi
    private FeedbackStatus feedbackStatus; // Trạng thái phản hồi

    // Hàm khởi tạo không tham số
    public CreateCustomerFeedbackDTO() {}

    // Hàm khởi tạo có tham số
    public CreateCustomerFeedbackDTO(int userId, String feedbackText, FeedbackType feedbackType, FeedbackStatus feedbackStatus) {
        this.userId = userId;
        this.feedbackText = feedbackText;
        this.feedbackType = feedbackType;
        this.feedbackStatus = feedbackStatus;
    }

    // Các phương thức getter và setter
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

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(FeedbackStatus feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }
}
