package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.SeatDTO.SeatDTO;
import com.example.demo.Mapper.SeatMapper;
import com.example.demo.Model.Entity.SeatEntity;
import com.example.demo.Repository.SeatRepository;

import com.example.demo.Exception.ValidationException;

import java.util.Optional;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatMapper seatMapper;

    // Tạo ghế ngồi mới
    public SeatDTO createSeat(SeatDTO seatDTO) {
        // Kiểm tra xem ghế đã tồn tại trong cơ sở dữ liệu chưa
        Optional<SeatEntity> existingSeat = seatRepository.findByTheater_TheaterIdAndAuditorium_AuditoriumIdAndSeatNumber(
                seatDTO.getTheaterId(), seatDTO.getAuditoriumId(), seatDTO.getSeatNumber());
    
        if (existingSeat.isPresent()) {
            throw new ValidationException("Ghế đã tồn tại cho rạp, phòng chiếu và số ghế này");
        }
        
        // Nếu ghế chưa tồn tại, tiếp tục tạo mới
        SeatEntity seat = seatMapper.toSeatEntity(seatDTO);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatDTO(seat);
    }

    // Xem thông tin chi tiết ghế ngồi
    public SeatDTO getSeatById(int seatId) {
        // Tìm ghế theo ID
        SeatEntity seat = seatRepository.findById(seatId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy ghế"));
        return seatMapper.toSeatDTO(seat);
    }

    // Xóa ghế ngồi
    public void deleteSeat(int seatId) {
        // Kiểm tra xem ghế có tồn tại không
        if (!seatRepository.existsById(seatId)) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy ghế");
        }
        // Xóa ghế theo ID
        seatRepository.deleteById(seatId);
    }

    // Lấy danh sách tất cả ghế ngồi
    public List<SeatDTO> getAllSeats() {
        List<SeatEntity> seats = seatRepository.findAll();
        return seatMapper.toDTOList(seats);
    }

    // Lấy danh sách ghế ngồi theo auditoriumId
    public List<SeatDTO> getSeatsByAuditoriumId(int auditoriumId) {
        List<SeatEntity> seatEntities = seatRepository.findByAuditorium_AuditoriumId(auditoriumId);
        return seatMapper.toDTOList(seatEntities);
    }
}
