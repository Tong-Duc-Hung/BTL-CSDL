package com.example.demo.Controller;

import com.example.demo.DTO.AuditoriumDTO.AuditoriumDTO;
import com.example.demo.DTO.SeatDTO.SeatDTO;
import com.example.demo.DTO.ShowtimeDTO.ShowtimeDTO;
import com.example.demo.DTO.TheaterDTO.TheaterDTO;
import com.example.demo.Service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    /**
     * Tạo lịch chiếu phim - chỉ cho phép ADMIN và STAFF.
     * 
     * @param showtimeDTO Thông tin lịch chiếu phim cần tạo.
     * @return Thông tin lịch chiếu phim vừa được tạo.
     */
    @RequiresRole({Role.admin, Role.staff})
    @PostMapping("/create")
    public ResponseEntity<ShowtimeDTO> createShowtime(@RequestBody ShowtimeDTO showtimeDTO) {
        ShowtimeDTO createdShowtime = showtimeService.createShowtime(showtimeDTO);
        return ResponseEntity.ok(createdShowtime);
    }

    /**
     * Lấy thông tin lịch chiếu phim theo ID.
     * 
     * @param showtimeId ID của lịch chiếu phim.
     * @return Thông tin lịch chiếu phim tương ứng với ID.
     */
    @GetMapping("/{showtimeId}")
    public ResponseEntity<ShowtimeDTO> getShowtimeById(@PathVariable int showtimeId) {
        ShowtimeDTO showtime = showtimeService.getShowtimeById(showtimeId);
        return ResponseEntity.ok(showtime);
    }

    /**
     * Xóa lịch chiếu phim - chỉ cho phép ADMIN.
     * 
     * @param showtimeId ID của lịch chiếu phim cần xóa.
     * @return Trả về mã trạng thái 200 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/{showtimeId}")
    public ResponseEntity<Void> deleteShowtimeById(@PathVariable int showtimeId) {
        showtimeService.deleteShowtime(showtimeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy thông tin phòng chiếu theo showtimeId.
     * 
     * @param showtimeId ID của lịch chiếu phim.
     * @return Thông tin phòng chiếu tương ứng với showtimeId.
     */
    @GetMapping("/{showtimeId}/auditorium")
    public ResponseEntity<AuditoriumDTO> getAuditoriumByShowtimeId(@PathVariable int showtimeId) {
        AuditoriumDTO auditorium = showtimeService.getAuditoriumByShowtimeId(showtimeId);
        return ResponseEntity.ok(auditorium);
    }

    /**
     * Lấy danh sách lịch chiếu phim theo movieId.
     * 
     * @param movieId ID của phim.
     * @return Danh sách các lịch chiếu phim tương ứng với movieId.
     */
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowtimeDTO>> getShowtimesByMovieId(@PathVariable String movieId) {
        List<ShowtimeDTO> showtimes = showtimeService.getShowtimesByMovieId(movieId);
        if (showtimes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(showtimes);
        }
    }

    /**
     * Lấy thông tin rạp chiếu theo showtimeId.
     * 
     * @param showtimeId ID của lịch chiếu phim.
     * @return Thông tin rạp chiếu tương ứng với showtimeId.
     */
    @GetMapping("/{showtimeId}/theater")
    public ResponseEntity<TheaterDTO> getTheaterByShowtimeId(@PathVariable int showtimeId) {
        TheaterDTO theater = showtimeService.getTheaterByShowtimeId(showtimeId);
        return ResponseEntity.ok(theater);
    }

    /**
     * Lấy danh sách ghế trống theo showtimeId.
     * 
     * @param showtimeId ID của lịch chiếu phim.
     * @return Danh sách các ghế trống tương ứng với showtimeId.
     */
    @GetMapping("/{showtimeId}/available-seats")
    public ResponseEntity<List<SeatDTO>> getAvailableSeatsByShowtimeId(@PathVariable int showtimeId) {
        List<SeatDTO> availableSeats = showtimeService.getAvailableSeatsByShowtimeId(showtimeId);
        if (availableSeats.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(availableSeats);
        }
    }

    /**
     * Lấy tổng số ghế theo showtimeId.
     * 
     * @param showtimeId ID của lịch chiếu phim.
     * @return Tổng số ghế tương ứng với showtimeId.
     */
    @GetMapping("/{showtimeId}/total-seats")
    public ResponseEntity<Integer> getTotalSeatsByShowtimeId(@PathVariable int showtimeId) {
        int totalSeats = showtimeService.getTotalSeatsByShowtimeId(showtimeId);
        return ResponseEntity.ok(totalSeats);
    }
}