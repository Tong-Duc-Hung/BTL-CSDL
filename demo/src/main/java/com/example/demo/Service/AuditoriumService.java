package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.AuditoriumDTO.AuditoriumDTO;
import com.example.demo.Mapper.AuditoriumMapper;
import com.example.demo.Model.Entity.AuditoriumEntity;
import com.example.demo.Repository.AuditoriumRepository;
import com.example.demo.Exception.ValidationException;
import java.util.List;

@Service
public class AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private AuditoriumMapper auditoriumMapper;

    // Tạo phòng chiếu mới
    public AuditoriumDTO createAuditorium(AuditoriumDTO auditoriumDTO) {
        // Kiểm tra xem phòng chiếu đã tồn tại hay chưa
        if (auditoriumRepository.existsByAuditoriumNameAndTheater_TheaterId(auditoriumDTO.getAuditoriumName(), auditoriumDTO.getTheaterId())) {
            throw new ValidationException.InvalidInputException("Phòng chiếu với tên này tại rạp này đã tồn tại");
        }
    
        // Chuyển đổi DTO sang Entity và lưu vào cơ sở dữ liệu
        AuditoriumEntity auditorium = auditoriumMapper.toAuditoriumEntity(auditoriumDTO);
        auditorium = auditoriumRepository.save(auditorium);

        // Trả về đối tượng DTO của phòng chiếu đã lưu
        return auditoriumMapper.toAuditoriumDTO(auditorium);
    }

    // Xem thông tin chi tiết phòng chiếu
    public AuditoriumDTO getAuditoriumById(int auditoriumId) {
        // Tìm phòng chiếu theo ID
        AuditoriumEntity auditorium = auditoriumRepository.findById(auditoriumId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy phòng chiếu"));
        return auditoriumMapper.toAuditoriumDTO(auditorium);
    }

    // Xóa phòng chiếu
    public void deleteAuditorium(int auditoriumId) {
        // Kiểm tra xem phòng chiếu có tồn tại không
        if (!auditoriumRepository.existsById(auditoriumId)) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phòng chiếu");
        }
        // Xóa phòng chiếu theo ID
        auditoriumRepository.deleteById(auditoriumId);
    }

    // Lấy danh sách tất cả các phòng chiếu
    public List<AuditoriumDTO> getAllAuditoriums() {
        List<AuditoriumEntity> auditoriums = auditoriumRepository.findAll();
        return auditoriumMapper.toDTOList(auditoriums);
    }
}
