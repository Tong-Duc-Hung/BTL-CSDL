package com.example.demo.DTO.FeedbackDTO;

public class CreateStaffResponseDTO {
    private int feedbackId; // Mã phản hồi
    private String staffResponse; // Phản hồi của nhân viên

    // Hàm khởi tạo không tham số
    public CreateStaffResponseDTO() {}

    // Hàm khởi tạo có tham số
    public CreateStaffResponseDTO(int feedbackId, String staffResponse) {
        this.feedbackId = feedbackId;
        this.staffResponse = staffResponse;
    }

    // Các phương thức getter và setter
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getStaffResponse() {
        return staffResponse;
    }

    public void setStaffResponse(String staffResponse) {
        this.staffResponse = staffResponse;
    }
}
