package com.example.demo.Model.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng payments.
 */
@Entity
@Table(name = "payments")
public class PaymentEntity {

    /**
     * Enum đại diện cho trạng thái thanh toán.
     */
    public enum PaymentStatus {
        pending,
        completed,
        canceled
    }

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, unique = true)
    private int paymentId;

    /**
     * Liên kết với bảng bookings thông qua booking_id.
     */
    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    /**
     * Ngày thanh toán.
     */
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    /**
     * Tổng số tiền thanh toán.
     */
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    /**
     * Trạng thái thanh toán.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    /**
     * Liên kết với bảng SupportedBanks thông qua bank_id.
     */
    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private SupportedBankEntity bank;

    /**
     * Constructor mặc định.
     */
    public PaymentEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param booking Đối tượng booking liên kết.
     * @param paymentDate Ngày thanh toán.
     * @param totalAmount Tổng số tiền thanh toán.
     * @param paymentStatus Trạng thái thanh toán.
     * @param bank Đối tượng bank liên kết.
     */
    public PaymentEntity(BookingEntity booking, Date paymentDate, BigDecimal totalAmount, PaymentStatus paymentStatus, SupportedBankEntity bank) {
        this.booking = booking;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.bank = bank;
    }

    // Các hàm getter và setter.

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
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

    public SupportedBankEntity getBank() {
        return bank;
    }

    public void setBank(SupportedBankEntity bank) {
        this.bank = bank;
    }
}
