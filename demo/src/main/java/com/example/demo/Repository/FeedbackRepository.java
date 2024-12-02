package com.example.demo.Repository;

// Import các gói cần thiết từ Spring Data JPA và Spring Framework
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Đánh dấu lớp này là một Repository của Spring, sẽ được Spring quản lý
@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    // Kế thừa các phương thức CRUD từ JpaRepository
}