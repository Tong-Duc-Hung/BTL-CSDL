package com.example.demo.Repository;

import com.example.demo.Model.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    Optional<BookingEntity> findByBookingId(int bookingId);

    BookingEntity findByBookingIdAndUser_UserId(int bookingId, int userId);

    List<BookingEntity> findByUser_UserId(int userId);
}
