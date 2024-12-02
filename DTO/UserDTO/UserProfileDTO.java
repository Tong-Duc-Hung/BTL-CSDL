package com.example.demo.DTO.UserDTO;

public class UserProfileDTO {
    private int userId; // Mã người dùng
    private String userName; // Tên người dùng
    private String email; // Email
    private String phoneNumber; // Số điện thoại
    private String avatar; // Ảnh đại diện
    private String role; // Vai trò

    // Hàm khởi tạo không tham số
    public UserProfileDTO() {}

    // Hàm khởi tạo có tham số
    public UserProfileDTO(int userId, String userName, String email, String phoneNumber, String avatar, String role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.role = role;
    }

    // Các phương thức getter và setter
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
