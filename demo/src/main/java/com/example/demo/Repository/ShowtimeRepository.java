package com.example.demo.Repository;

// Import các gói cần thiết từ Spring Data JPA và Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Entity.ShowtimeEntity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

// Đánh dấu lớp này là một Repository của Spring, sẽ được Spring quản lý
@Repository
public interface ShowtimeRepository extends JpaRepository<ShowtimeEntity, Integer> {

    // Phương thức tìm kiếm lịch chiếu theo ID phim, sử dụng mối quan hệ Movie và Showtime
    List<ShowtimeEntity> findByMovie_MovieId(String movieId);
    @Query("SELECT s.id FROM ShowtimeEntity s")
    List<Integer> findAllShowtimeIds();
    boolean existsByMovie_MovieIdAndTheater_TheaterIdAndAuditorium_AuditoriumIdAndShowDateAndShowTime(String movieId, int theaterId, int auditoriumId, Date showDate, Time showTime);
}