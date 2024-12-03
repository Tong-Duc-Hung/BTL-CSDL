package com.example.demo.DTO.PaymentDTO;

import java.math.BigDecimal;

public class TransactionAmountDTO {
    private int paymentId; // Mã thanh toán
    private BigDecimal totalAmount; // Tổng số tiền
    private BigDecimal paidAmount; // Số tiền đã thanh toán

    // Hàm khởi tạo không tham số
    public TransactionAmountDTO() {}

    // Hàm khởi tạo có tham số
    public TransactionAmountDTO(int paymentId, BigDecimal totalAmount, BigDecimal paidAmount) {
        this.paymentId = paymentId;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }

    // Các phương thức getter và setter
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }
}
