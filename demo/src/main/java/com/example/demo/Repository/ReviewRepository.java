package com.example.demo.Repository;

// Import các gói cần thiết từ Spring Data JPA và Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Entity.ReviewEntity;

import java.util.List;

// Đánh dấu lớp này là một Repository của Spring, sẽ được Spring quản lý
@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    boolean existsByMovie_MovieIdAndUser_UserId(String movieId, int userId);

    // Phương thức tìm kiếm các đánh giá theo ID phim, sử dụng mối quan hệ giữa Review và Movie
    List<ReviewEntity> findByMovie_MovieId(String movieId);
}