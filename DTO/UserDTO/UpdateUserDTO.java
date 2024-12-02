package com.example.demo.DTO.UserDTO;

public class UpdateUserDTO {
    private String email; // Email
    private String phoneNumber; // Số điện thoại
    private String avatar; // Ảnh đại diện

    // Hàm khởi tạo có tham số
    public UpdateUserDTO(String email, String phoneNumber, String avatar) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    // Các phương thức getter và setter
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
}
