package com.example.demo.Repository;

// Import các gói cần thiết từ Spring Data JPA và mô hình dữ liệu
import com.example.demo.Model.Entity.SupportedBankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Khai báo giao diện SupportedBankRepository kế thừa từ JpaRepository
public interface SupportedBankRepository extends JpaRepository<SupportedBankEntity, Integer> {

    // Phương thức kiểm tra xem tên ngân hàng có tồn tại trong cơ sở dữ liệu không
    boolean existsByBankName(String bankName);
}