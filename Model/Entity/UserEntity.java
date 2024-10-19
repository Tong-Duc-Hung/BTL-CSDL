package com.example.movieticketbooking.Model.Entity;

import java.util.*;
import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng users.
*/
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "phone_number"}))
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
    private int user_id;

    /**
     * Tên người dùng.
    */
    @Column(name = "username", nullable = false, length = 50)
    private String username;

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
    @Column(name = "phone_number", nullable = false, length = 15, unique = true)
    private String phone_number;

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
     * @param username Tên người dùng.
     * @param password Mật khẩu.
     * @param email Địa chỉ email.
     * @param phone_number Số điện thoại.
     * @param role Vai trò của người dùng.
    */
    public UserEntity(String username, String password, String email, String phone_number, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }

    // Các hàm getter và setter.

    public int getUser_id() {
        return user_id;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername()  {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)  {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Lấy danh sách reviews liên kết.
     *
     * @return danh sách reviews liên kết.
    */
    public List<ReviewEntity> reviews() {
        return reviews;
    }

    /**
     * Lấy danh sách bookings liên kết.
     *
     * @return danh sách bookings liên kết.
    */
    public List<BookingEntity> bookings() {
        return bookings;
    }

    /**
     * Lấy danh sách feedbacks liên kết.
     *
     * @return danh sách feedbacks liên kết.
    */
    public List<FeedbackEntity> Feedbacks() {
        return feedbacks;
    }
}