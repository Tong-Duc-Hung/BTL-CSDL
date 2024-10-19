package com.example.movieticketbooking.Model.Entity;

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
    public enum Feedback_type {
        Complaint,
        Suggestion,
        Inquiry,
        Other
    }

    /**
     * Enum đại diện cho trạng thái của phản hồi.
    */
    public enum Status {
        Pending,
        Reviewed,
        Resolved
    }

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false, unique = true)
    private int feedback_id;

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
    private String feedback_text;

    /**
     * Loại phản hồi.
    */
    @Enumerated(EnumType.STRING)
    @Column(name = "feedback_type", nullable = false)
    private Feedback_type feedback_type;

    /**
     * Trạng thái của phản hồi.
    */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    /**
     * Phản hồi của nhân viên.
    */
    @Column(name = "staff_response")
    private String staff_response;

    /**
     * Constructor mặc định.
    */
    public FeedbackEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param user Đối tượng user liên kết.
     * @param feedback_text Nội dung phản hồi.
     * @param feedback_type Loại phản hồi.
     * @param status Trạng thái phản hồi.
     * @param staff_response Phản hồi của nhân viên.
    */
    public FeedbackEntity(UserEntity user, String feedback_text, Feedback_type feedback_type, Status status, String staff_response) {
        this.user = user;
        this.feedback_text = feedback_text;
        this.feedback_type = feedback_type;
        this.status = status;
        this.staff_response = staff_response;
    }

    // Các hàm getter và setter.

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getFeedback_text() {
        return feedback_text;
    }

    public void setFeedback_text(String feedback_text) {
        this.feedback_text = feedback_text;
    }

    public Feedback_type getFeedback_type() {
        return feedback_type;
    }

    public void setFeedback_type(Feedback_type feedback_type) {
        this.feedback_type = feedback_type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStaff_response() {
        return staff_response;
    }

    public void setStaff_response(String staff_response) {
        this.staff_response = staff_response;
    }
}