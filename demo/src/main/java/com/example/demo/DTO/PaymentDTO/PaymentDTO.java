package com.example.demo.DTO.PaymentDTO;

import java.math.BigDecimal;
import java.sql.Date;
import com.example.demo.Model.Entity.PaymentEntity.PaymentStatus;

public class PaymentDTO {
    private int paymentId; // Mã thanh toán
    private int bookingId; // Mã booking
    private int bankId; // Mã ngân hàng
    private Date paymentDate; // Ngày thanh toán
    private BigDecimal totalAmount; // Tổng số tiền
    private PaymentStatus paymentStatus; // Trạng thái thanh toán

    // Hàm khởi tạo không tham số
    public PaymentDTO() {}

    // Hàm khởi tạo có tham số
    public PaymentDTO(int paymentId, int bookingId, Date paymentDate, BigDecimal totalAmount, PaymentStatus paymentStatus, int bankId) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.bankId = bankId;
    }

    // Các phương thức getter và setter
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }
}
