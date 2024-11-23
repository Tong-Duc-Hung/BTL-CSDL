package com.example.demo.Model.Entity;

import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng customer_feedback.
 */
@Entity
@Table(name = "customer_feedback")
public class FeedbackEntity {

    /**
     * Enum đại diện cho loại phản hồi.
     */
    public enum FeedbackType {
        complaint,
        suggestion,
        inquiry,
        other
    }

    /**
     * Enum đại diện cho trạng thái của phản hồi.
     */
    public enum FeedbackStatus {
        pending,
        reviewed,
        resolved
    }

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false, unique = true)
    private int feedbackId;

    /**
     * Liên kết với bảng user thông qua user_id.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * Nội dung phản hồi.
     */
    @Column(name = "feedback_text", nullable = false)
    private String feedbackText;

    /**
     * Loại phản hồi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "feedback_type")
    private FeedbackType feedbackType;

    /**
     * Trạng thái của phản hồi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "feedback_status")
    private FeedbackStatus feedbackStatus;

    /**
     * Phản hồi của nhân viên.
     */
    @Column(name = "staff_response")
    private String staffResponse;

    /**
     * Constructor mặc định.
     */
    public FeedbackEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param user Đối tượng user liên kết.
     * @param feedbackText Nội dung phản hồi.
     * @param feedbackType Loại phản hồi.
     * @param status Trạng thái phản hồi.
     * @param staffResponse Phản hồi của nhân viên.
     */
    public FeedbackEntity(UserEntity user, String feedbackText, FeedbackType feedbackType, FeedbackStatus feedbackStatus, String staffResponse) {
        this.user = user;
        this.feedbackText = feedbackText;
        this.feedbackType = feedbackType;
        this.feedbackStatus = feedbackStatus;
        this.staffResponse = staffResponse;
    }

    // Các hàm getter và setter.

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public String getStaffResponse() {
        return staffResponse;
    }

    public void setStaffResponse(String staffResponse) {
        this.staffResponse = staffResponse;
    }
}