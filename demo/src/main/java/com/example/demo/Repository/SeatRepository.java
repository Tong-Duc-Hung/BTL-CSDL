package com.example.demo.Repository;

// Import các gói cần thiết từ Spring Data JPA và Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Entity.SeatEntity;

import java.util.Optional;
import java.util.List;

// Đánh dấu lớp này là một Repository của Spring, sẽ được Spring quản lý
@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Integer> {
    Optional<SeatEntity> findByTheater_TheaterIdAndAuditorium_AuditoriumIdAndSeatNumber(
        int theaterId, int auditoriumId, String seatNumber);
    // Phương thức tìm kiếm ghế ngồi theo ID phòng chiếu, sử dụng mối quan hệ giữa Seat và Auditorium
    List<SeatEntity> findByAuditorium_AuditoriumId(int auditoriumId);
}