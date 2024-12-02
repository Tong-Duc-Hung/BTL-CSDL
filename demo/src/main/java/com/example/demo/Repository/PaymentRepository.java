package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Entity.PaymentEntity;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

// Đánh dấu lớp này là một Repository của Spring, sẽ được Spring quản lý
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

    // Tìm Payment theo bookingId
    Optional<PaymentEntity> findByBooking_BookingId(int bookingId);

    // Tìm Payments theo trạng thái thanh toán (PaymentStatus)
    List<PaymentEntity> findByPaymentStatus(PaymentEntity.PaymentStatus paymentStatus);

    // Sửa phương thức findById trả về Optional<PaymentEntity>
    Optional<PaymentEntity> findById(int paymentId);

    // Tìm Payments theo ngày thanh toán (PaymentDate)
    List<PaymentEntity> findByPaymentDateBetween(Date startDate, Date endDate);

    // Tìm Payments theo bankId
    List<PaymentEntity> findByBank_BankId(int bankId);
}
