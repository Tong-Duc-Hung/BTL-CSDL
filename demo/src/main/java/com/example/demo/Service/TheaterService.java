package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.AuditoriumDTO.AuditoriumDTO;
import com.example.demo.DTO.TheaterDTO.TheaterDTO;
import com.example.demo.Exception.ValidationException;
import com.example.demo.Mapper.TheaterMapper;
import com.example.demo.Model.Entity.AuditoriumEntity;
import com.example.demo.Model.Entity.TheaterEntity;
import com.example.demo.Repository.TheaterRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterMapper theaterMapper;

    // Phương thức tạo rạp chiếu phim mới
    public TheaterDTO createTheater(TheaterDTO theaterDTO) {
        // Kiểm tra nếu tên rạp không có hoặc rỗng
        if (theaterDTO.getTheaterName() == null || theaterDTO.getTheaterName().isEmpty()) {
            throw new ValidationException.InvalidInputException("Tên rạp không thể trống");
        }

        // Kiểm tra nếu tổng số phòng chiếu nhỏ hơn hoặc bằng 0
        if (theaterDTO.getTotalAuditoriums() <= 0) {
            throw new ValidationException.InvalidInputException("Tổng số phòng chiếu phải lớn hơn 0");
        }

        // Kiểm tra xem tên rạp đã tồn tại hay chưa
        if (theaterRepository.existsByTheaterName(theaterDTO.getTheaterName())) {
            throw new ValidationException.InvalidInputException("Rạp với tên này đã tồn tại");
        }

        // Chuyển đổi DTO thành Entity và lưu vào cơ sở dữ liệu
        TheaterEntity theaterEntity = theaterMapper.toTheaterEntity(theaterDTO);
        theaterEntity = theaterRepository.save(theaterEntity);

        // Trả về đối tượng DTO của rạp đã lưu
        return theaterMapper.toTheaterDTO(theaterEntity);
    }

    // Phương thức xóa rạp chiếu phim
    public void deleteTheater(int theaterId) {
        // Kiểm tra nếu rạp tồn tại bằng ID
        if (!theaterRepository.existsById(theaterId)) {
            throw new ValidationException.ResourceNotFoundException("Rạp không tồn tại với ID: " + theaterId);
        }
        // Xóa rạp theo ID
        theaterRepository.deleteById(theaterId);
    }

    // Phương thức lấy rạp chiếu phim theo ID
    public Optional<TheaterDTO> getTheaterById(int theaterId) {
        Optional<TheaterEntity> theaterEntity = theaterRepository.findById(theaterId);
        // Kiểm tra nếu rạp không tồn tại
        if (theaterEntity.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Rạp không tồn tại với ID: " + theaterId);
        }
        // Trả về đối tượng DTO của rạp
        return theaterEntity.map(theaterMapper::toTheaterDTO);
    }

    // Phương thức lấy danh sách tất cả các rạp chiếu phim
    public List<TheaterDTO> getAllTheaters() {
        List<TheaterEntity> theaters = theaterRepository.findAll();
        // Chuyển đổi danh sách Entity thành danh sách DTO
        return theaterMapper.toDTOList(theaters);
    }

    // Phương thức tìm rạp chiếu phim theo tên
    public List<TheaterDTO> searchTheaterByName(String theaterName) {
        // Kiểm tra nếu tên rạp rỗng
        if (theaterName == null || theaterName.isEmpty()) {
            throw new ValidationException.InvalidInputException("Tên rạp không thể trống để tìm kiếm");
        }
        // Tìm danh sách rạp theo tên
        List<TheaterEntity> theaters = theaterRepository.findByTheaterNameIgnoreCase(theaterName);
        // Kiểm tra nếu không tìm thấy rạp nào
        if (theaters.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy rạp với tên: " + theaterName);
        }
        // Chuyển đổi danh sách Entity thành danh sách DTO
        return theaterMapper.toDTOList(theaters);
    }

    // Phương thức lấy danh sách các phòng chiếu theo ID của rạp
    public List<AuditoriumDTO> getAuditoriumsByTheaterId(int theaterId) {
        TheaterEntity theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Rạp không tồn tại với ID: " + theaterId));
        List<AuditoriumEntity> auditoriums = theater.getAuditoriums();
        // Chuyển đổi danh sách phòng chiếu thành danh sách DTO
        return auditoriums.stream()
                .map(auditorium -> new AuditoriumDTO(auditorium.getAuditoriumId(), auditorium.getTheater().getTheaterId(), auditorium.getAuditoriumName(), auditorium.getAuditoriumSeats()))
                .collect(Collectors.toList());
    }
}
