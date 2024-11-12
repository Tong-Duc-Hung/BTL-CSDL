package com.example.demo.DTO.UserDTO;

public class UserRegistrationDTO {

    private String userName; // Tên người dùng
    private String password; // Mật khẩu
    private String email; // Email
    private String phoneNumber; // Số điện thoại

    // Hàm khởi tạo không tham số
    public UserRegistrationDTO() {}

    // Hàm khởi tạo có tham số
    public UserRegistrationDTO(String userName, String password, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Các phương thức getter và setter
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
}
