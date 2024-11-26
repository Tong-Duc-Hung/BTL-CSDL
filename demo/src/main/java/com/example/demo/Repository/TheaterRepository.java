package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Entity.TheaterEntity;
import java.util.List;
import java.util.Optional;

// Đánh dấu lớp này là một Repository của Spring, sẽ được Spring quản lý
@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {

    // Kiểm tra xem rạp đã tồn tại theo tên hay chưa
    boolean existsByTheaterName(String theaterName);
    
    // Tìm kiếm rạp chiếu phim theo tên không phân biệt chữ hoa, chữ thường
    List<TheaterEntity> findByTheaterNameIgnoreCase(String theaterName);
    
    // Tìm rạp chiếu phim theo ID
    Optional<TheaterEntity> findById(int theaterId);
    
    // Tìm rạp chiếu phim theo địa điểm
    List<TheaterEntity> findByLocation(String location);
}