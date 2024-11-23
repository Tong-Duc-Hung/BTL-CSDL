package com.example.demo.Model.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Entity đại diện cho bảng users.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * Enum đại diện cho vai trò của người dùng.
     */
    public enum Role {
        admin,
        staff,
        customer
    }

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private int userId;

    /**
     * Tên người dùng.
     */
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String userName;

    /**
     * Mật khẩu của người dùng.
     */
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    /**
     * Địa chỉ email của người dùng.
     */
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    /**
     * Số điện thoại của người dùng.
     */
    @Column(name = "phone_number", length = 15, unique = true)
    private String phoneNumber;

    /**
     * Ảnh đại diện của người dùng.
     */
    @Column(name = "avatar", length = 255)
    private String avatar;

    /**
     * Vai trò của người dùng.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    /**
     * Liên kết với bảng reviews thông qua thuộc tính user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    /**
     * Liên kết với bảng bookings thông qua thuộc tính user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingEntity> bookings = new ArrayList<>();

    /**
     * Liên kết với bảng feedbacks thông qua thuộc tính user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedbackEntity> feedbacks = new ArrayList<>();

    /**
     * Constructor mặc định.
     */
    public UserEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param userName Tên người dùng.
     * @param password Mật khẩu.
     * @param email Địa chỉ email.
     * @param phoneNumber Số điện thoại.
     * @param avatar Ảnh đại diện.
     * @param role Vai trò của người dùng.
     */
    public UserEntity(String userName, String password, String email, String phoneNumber, String avatar, Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.role = role;
    }

    // Các hàm getter và setter.

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}