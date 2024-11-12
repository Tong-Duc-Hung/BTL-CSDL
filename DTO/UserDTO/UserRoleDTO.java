package com.example.demo.DTO.UserDTO;

public class UserRoleDTO {
    private String userName; // Tên người dùng
    private String role; // Vai trò

    // Hàm khởi tạo không tham số
    public UserRoleDTO() {}

    // Hàm khởi tạo có tham số
    public UserRoleDTO(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    // Các phương thức getter và setter
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
