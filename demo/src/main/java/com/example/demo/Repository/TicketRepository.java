    package com.example.demo.Repository;

    import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Entity.SeatEntity;
import com.example.demo.Model.Entity.TicketEntity;
    import com.example.demo.Model.Entity.TicketEntity.TicketStatus;
    import java.util.List;
    import java.math.BigDecimal;
    import java.util.Optional;

    @Repository
    public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
        // Kế thừa các phương thức CRUD từ JpaRepository

        // Tìm vé theo ID của vé (ticketId)
        Optional<TicketEntity> findByTicketId(int ticketId);

        // Tìm tất cả vé theo ID của suất chiếu (showtimeId)
        List<TicketEntity> findByShowtime_ShowtimeId(int showtimeId);

        // Tìm tất cả vé theo giá vé (ticketPrice)
        List<TicketEntity> findByTicketPrice(BigDecimal ticketPrice);

        // Tìm tất cả vé theo ID của ghế ngồi (seatId)
        List<TicketEntity> findBySeat_SeatId(int seatId);

        // Kiểm tra xem ghế có bị đặt trước cho suất chiếu cụ thể và có trạng thái khác với 'canceled'
        boolean existsByShowtime_ShowtimeIdAndSeat_SeatIdAndTicketStatusNot(int showtimeId, int seatId, TicketStatus ticketStatus);

        // Tìm tất cả vé theo booking ID và trạng thái vé khác 'canceled'
        List<TicketEntity> findByBooking_BookingIdAndTicketStatusNot(int bookingId, TicketStatus ticketStatus);

        // Tìm tất cả vé theo booking ID
        List<TicketEntity> findByBooking_BookingId(int bookingId);

        // Tìm tất cả vé theo trạng thái vé
        List<TicketEntity> findByTicketStatus(TicketStatus ticketStatus);

        // Tìm vé theo trạng thái 'confirmed' và 'canceled' của suất chiếu
        List<TicketEntity> findByShowtime_ShowtimeIdAndTicketStatus(int showtimeId, TicketStatus ticketStatus);

        // Tìm tất cả vé chưa bị hủy theo suất chiếu
        List<TicketEntity> findByShowtime_ShowtimeIdAndTicketStatusNot(int showtimeId, TicketStatus ticketStatus);

         // Tìm tất cả vé theo ID của suất chiếu (showtimeId) và có trạng thái không phải 'CANCEL'
        @Query("SELECT t.seat FROM TicketEntity t WHERE t.showtime.showtimeId = :showtimeId AND t.ticketStatus != 'cancel'")
        List<SeatEntity> findSeatsBookedByShowtimeIdAndNotCanceled(@Param("showtimeId") int showtimeId);
}