package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AuditoriumDTO.AuditoriumDTO;
import com.example.demo.DTO.SeatDTO.SeatDTO;
import com.example.demo.DTO.ShowtimeDTO.ShowtimeDTO;
import com.example.demo.DTO.TheaterDTO.TheaterDTO;
import com.example.demo.Mapper.ShowtimeMapper;
import com.example.demo.Mapper.TheaterMapper;
import com.example.demo.Mapper.AuditoriumMapper;
import com.example.demo.Mapper.SeatMapper;
import com.example.demo.Model.Entity.AuditoriumEntity;
import com.example.demo.Model.Entity.SeatEntity;
import com.example.demo.Model.Entity.ShowtimeEntity;
import com.example.demo.Model.Entity.TheaterEntity;
import com.example.demo.Repository.TicketRepository;
import com.example.demo.Repository.ShowtimeRepository;
import com.example.demo.Exception.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ShowtimeMapper showtimeMapper;
    @Autowired
    private TheaterMapper theaterMapper;
    @Autowired
    private AuditoriumMapper auditoriumMapper;
    @Autowired
    private SeatMapper seatMapper;
    @Autowired
    private TicketRepository ticketRepository;

    // Tạo suất chiếu mới
    public ShowtimeDTO createShowtime(ShowtimeDTO showtimeDTO) {
        // Kiểm tra xem suất chiếu đã tồn tại hay chưa
        if (showtimeRepository.existsByMovie_MovieIdAndTheater_TheaterIdAndAuditorium_AuditoriumIdAndShowDateAndShowTime(
                showtimeDTO.getMovieId(), showtimeDTO.getTheaterId(), showtimeDTO.getAuditoriumId(),
                showtimeDTO.getShowDate(), showtimeDTO.getShowTime())) {
            throw new ValidationException.InvalidInputException("Suất chiếu này đã tồn tại");
        }

        // Chuyển đổi DTO sang Entity và lưu vào cơ sở dữ liệu
        ShowtimeEntity showtime = showtimeMapper.toShowtimeEntity(showtimeDTO);
        showtime = showtimeRepository.save(showtime);
        return showtimeMapper.toShowtimeDTO(showtime);
    }

    // Xem thông tin chi tiết suất chiếu
    public ShowtimeDTO getShowtimeById(int showtimeId) {
        ShowtimeEntity showtime = showtimeRepository.findById(showtimeId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Showtime không tồn tại"));
        return showtimeMapper.toShowtimeDTO(showtime);
    }

    // Xóa suất chiếu
    public void deleteShowtime(int showtimeId) {
        // Kiểm tra xem suất chiếu có tồn tại không
        if (!showtimeRepository.existsById(showtimeId)) {
            throw new ValidationException.ResourceNotFoundException("Showtime không tồn tại");
        }
        // Xóa suất chiếu theo ID
        showtimeRepository.deleteById(showtimeId);
    }

    // Lấy danh sách suất chiếu
    public List<ShowtimeDTO> getAllShowtimes() {
        List<ShowtimeEntity> showtimes = showtimeRepository.findAll();
        return showtimeMapper.toDTOList(showtimes);
    }

    // Lấy thông tin phòng chiếu theo showtimeId
    public AuditoriumDTO getAuditoriumByShowtimeId(int showtimeId) {
        ShowtimeEntity showtime = showtimeRepository.findById(showtimeId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Showtime không tồn tại"));
        return auditoriumMapper.toAuditoriumDTO(showtime.getAuditorium());
    }

    // Lấy danh sách suất chiếu theo movieId
    public List<ShowtimeDTO> getShowtimesByMovieId(String movieId) {
        List<ShowtimeEntity> showtimeEntities = showtimeRepository.findByMovie_MovieId(movieId);
        return showtimeMapper.toDTOList(showtimeEntities);
    }

    // Lấy thông tin rạp chiếu theo showtimeId
    public TheaterDTO getTheaterByShowtimeId(int showtimeId) {
        ShowtimeEntity showtime = showtimeRepository.findById(showtimeId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Showtime không tồn tại"));
        AuditoriumEntity auditorium = showtime.getAuditorium();
        TheaterEntity theater = auditorium.getTheater();
        return theaterMapper.toTheaterDTO(theater);
    }

    // Lấy danh sách ghế trống theo showtimeId
    public List<SeatDTO> getAvailableSeatsByShowtimeId(int showtimeId) {
        // Lấy thông tin suất chiếu
        ShowtimeEntity showtime = showtimeRepository.findById(showtimeId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Showtime không tồn tại"));
        
        // Lấy danh sách tất cả ghế trong phòng chiếu
        List<SeatEntity> allSeats = showtime.getAuditorium().getSeats();
        
        // Lấy danh sách ghế đã đặt và có trạng thái khác CANCEL
        List<SeatEntity> bookedSeats = ticketRepository.findSeatsBookedByShowtimeIdAndNotCanceled(showtimeId);
        
        // Lọc ra các ghế trống (chưa được đặt hoặc vé bị hủy)
        List<SeatEntity> availableSeats = allSeats.stream()
            .filter(seat -> !bookedSeats.contains(seat))  // Lọc ghế chưa được đặt hoặc đã bị hủy
            .collect(Collectors.toList());
        
        // Ánh xạ danh sách ghế trống thành các DTO
        return seatMapper.toDTOList(availableSeats);
    }
    
    // Lấy tổng số ghế theo showtimeId
    public int getTotalSeatsByShowtimeId(int showtimeId) {
        ShowtimeEntity showtime = showtimeRepository.findById(showtimeId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Showtime không tồn tại"));
    
        // Lấy danh sách các ghế thuộc phòng chiếu
        List<SeatEntity> allSeats = showtime.getAuditorium().getSeats();
        return allSeats.size();
    }
}
