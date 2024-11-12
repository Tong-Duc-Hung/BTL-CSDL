package com.example.demo.DTO.UserDTO;

public class ChangePasswordDTO {

    private String oldPassword; // Mật khẩu cũ
    private String newPassword; // Mật khẩu mới
    private String confirmPassword; // Xác nhận mật khẩu mới

    // Các phương thức getter và setter
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Phương thức kiểm tra mật khẩu mới và xác nhận mật khẩu có khớp không
    public boolean isPasswordMatch() {
        return newPassword != null && newPassword.equals(confirmPassword);
    }

    // Phương thức kiểm tra mật khẩu mới có khác mật khẩu cũ không
    public boolean isNewPasswordDifferent() {
        return newPassword != null && !newPassword.equals(oldPassword);
    }
}
