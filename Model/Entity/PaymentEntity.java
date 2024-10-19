package com.example.movieticketbooking.Model.Entity;

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
    public enum Payment_status {
        Completed,
        Canceled
    }

    /**
     * Khóa chính của bảng.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, unique = true)
    private int payment_id;

    /**
     * Liên kết với bảng bookings thông qua booking_id.
    */
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    /**
     * Ngày thanh toán.
    */
    @Column(name = "payment_date", nullable = false)
    private String payment_date;

    /**
     * Tổng số tiền thanh toán.
    */
    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private double total_amount;

    /**
     * Trạng thái thanh toán.
    */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private Payment_status payment_status;

    /**
     * Constructor mặc định.
    */
    public PaymentEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param booking Đối tượng booking liên kết.
     * @param payment_date Ngày thanh toán.
     * @param total_amount Tổng số tiền thanh toán.
     * @param payment_status Trạng thái thanh toán.
    */
    public PaymentEntity(BookingEntity booking, String payment_date, double total_amount, Payment_status payment_status) {
        this.booking = booking;
        this.payment_date = payment_date;
        this.total_amount = total_amount;
        this.payment_status = payment_status;
    }

    // Các hàm getter và setter.

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public Payment_status getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(Payment_status payment_status) {
        this.payment_status = payment_status;
    }
}