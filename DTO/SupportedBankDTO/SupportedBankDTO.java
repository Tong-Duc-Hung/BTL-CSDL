package com.example.demo.DTO.SupportedBankDTO;

public class SupportedBankDTO {
    private int bankId; // Mã ngân hàng
    private String bankName; // Tên ngân hàng

    // Hàm khởi tạo không tham số
    public SupportedBankDTO() {}

    // Hàm khởi tạo có tham số
    public SupportedBankDTO(int bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }

    // Các phương thức getter và setter
    public int getBankId() {
        return bankId;
    }
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
