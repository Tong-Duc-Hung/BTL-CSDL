package com.example.demo.DTO.UserDTO;

public class LoginDTO {
    private String userName; // Tên người dùng
    private String password; // Mật khẩu
    private String role; // Vai trò

    // Hàm khởi tạo không tham số
    public LoginDTO() {}

    // Hàm khởi tạo có tham số
    public LoginDTO(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
