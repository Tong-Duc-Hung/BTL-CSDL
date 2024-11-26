package com.example.demo.Repository;

// Import các gói cần thiết từ Spring Data JPA và Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Entity.AuditoriumEntity;

import java.util.List;
import java.util.Optional;

// Đánh dấu lớp này là một Repository của Spring, sẽ được Spring quản lý
@Repository
public interface AuditoriumRepository extends JpaRepository<AuditoriumEntity, Integer> {
    boolean existsByAuditoriumNameAndTheater_TheaterId(String auditoriumName, int theaterId);

    // Phương thức tìm danh sách phòng chiếu theo ID rạp chiếu phim (theaterId)
    List<AuditoriumEntity> findByTheater_TheaterId(int theaterId);

    // Phương thức tìm phòng chiếu theo ID phòng chiếu và ID rạp chiếu phim
    Optional<AuditoriumEntity> findByAuditoriumIdAndTheater_TheaterId(int auditoriumId, int theaterId);
}