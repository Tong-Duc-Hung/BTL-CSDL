package com.example.demo.Service;

import com.example.demo.Model.Entity.SupportedBankEntity;
import com.example.demo.DTO.SupportedBankDTO.SupportedBankDTO;
import com.example.demo.Mapper.SupportedBankMapper;
import com.example.demo.Repository.SupportedBankRepository;
import com.example.demo.Exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupportedBankService {

    @Autowired
    private SupportedBankRepository supportedBankRepository;

    @Autowired
    private SupportedBankMapper supportedBankMapper;

    // Phương thức lấy danh sách tất cả các ngân hàng được hỗ trợ
    public List<SupportedBankDTO> getAllSupportedBanks() {
        List<SupportedBankEntity> supportedBankEntities = supportedBankRepository.findAll();
        return supportedBankMapper.toDTOList(supportedBankEntities);
    }

    // Phương thức tạo ngân hàng hỗ trợ mới
    public SupportedBankDTO createSupportedBank(SupportedBankDTO supportedBankDTO) {
        // Kiểm tra xem tên ngân hàng có hợp lệ không
        if (supportedBankDTO.getBankName() == null || supportedBankDTO.getBankName().isEmpty()) {
            throw new ValidationException.InvalidInputException("Tên ngân hàng không được để trống.");
        }
    
        // Kiểm tra xem ngân hàng có tồn tại không trước khi thêm mới
        if (supportedBankRepository.existsByBankName(supportedBankDTO.getBankName())) {
            throw new ValidationException.ConflictException("Ngân hàng đã tồn tại.");
        }
    
        // Chuyển đổi DTO sang Entity và lưu vào cơ sở dữ liệu
        SupportedBankEntity supportedBankEntity = supportedBankMapper.toEntity(supportedBankDTO);
        supportedBankEntity = supportedBankRepository.save(supportedBankEntity);
        return supportedBankMapper.toDTO(supportedBankEntity);
    }

    // Phương thức xóa ngân hàng hỗ trợ theo ID
    public void deleteSupportedBank(int bankId) {
        // Kiểm tra nếu ngân hàng tồn tại bằng ID
        if (!supportedBankRepository.existsById(bankId)) {
            throw new ValidationException.ResourceNotFoundException("Ngân hàng không tồn tại.");
        }
        // Xóa ngân hàng theo ID
        supportedBankRepository.deleteById(bankId);
    }
}
