package com.example.demo.Model.Entity;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Lớp Entity đại diện cho bảng supportedbanks.
 */
@Entity
@Table(name = "supportedbanks")
public class SupportedBankEntity {

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supportedBank_id", nullable = false, unique = true)
    private int bankId;

    /**
     * Tên ngân hàng, duy nhất.
     */
    @Column(name = "bank_name", nullable = false, unique = true, length = 255)
    private String bankName;

    /**
     * Liên kết với bảng payments.
     */
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PaymentEntity> payments;

    /**
     * Constructor mặc định.
     */
    public SupportedBankEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param bankName Tên ngân hàng.
     */
    public SupportedBankEntity(String bankName) {
        this.bankName = bankName;
    }

    // Các hàm getter và setter.

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

    public Set<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentEntity> payments) {
        this.payments = payments;
    }
}
