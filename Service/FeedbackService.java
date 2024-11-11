package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.FeedbackDTO.*;
import com.example.demo.Mapper.FeedbackMapper;
import com.example.demo.Model.Entity.FeedbackEntity;
import com.example.demo.Model.Entity.FeedbackEntity.FeedbackStatus;
import com.example.demo.Repository.FeedbackRepository;
import com.example.demo.Exception.ValidationException;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

    // Tạo phản hồi của khách hàng
    public CustomerFeedbackDetailsDTO createCustomerFeedback(CreateCustomerFeedbackDTO createCustomerFeedbackDTO) {
        // Kiểm tra xem feedbackStatus có được cung cấp hay không trong DTO
        if (createCustomerFeedbackDTO.getFeedbackStatus() == null) {
            // Nếu feedbackStatus không có, gán giá trị mặc định là pending
            createCustomerFeedbackDTO.setFeedbackStatus(FeedbackStatus.pending);
        }
        
        // Chuyển đổi từ DTO sang Entity thông qua mapper
        FeedbackEntity feedback = feedbackMapper.toFeedbackEntity(createCustomerFeedbackDTO);
        
        // Lưu phản hồi vào cơ sở dữ liệu
        feedback = feedbackRepository.save(feedback);
        
        // Chuyển đổi Entity sang DTO trả về cho client
        return feedbackMapper.toCustomerFeedbackDetailsDTO(feedback);
    }    

    // Xem thông tin chi tiết Feedback của khách hàng
    public CustomerFeedbackDetailsDTO getCustomerFeedbackById(int feedbackId) {
        // Tìm phản hồi theo ID
        FeedbackEntity feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy phản hồi của khách hàng"));
        return feedbackMapper.toCustomerFeedbackDetailsDTO(feedback);
    }

    // Cập nhật phản hồi của nhân viên
    public StaffResponseDetailsDTO updateStaffResponse(int feedbackId, CreateStaffResponseDTO createStaffResponseDTO) {
        // Tìm phản hồi dựa trên feedbackId
        FeedbackEntity feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy phản hồi"));
    
        // Cập nhật phản hồi của nhân viên
        feedback.setStaffResponse(createStaffResponseDTO.getStaffResponse());
    
        // Cập nhật trạng thái phản hồi thành 'resolved'
        feedback.setFeedbackStatus(FeedbackStatus.resolved);
    
        // Lưu cập nhật vào cơ sở dữ liệu
        feedback = feedbackRepository.save(feedback);
    
        // Chuyển đổi phản hồi đã cập nhật thành DTO để trả về
        return feedbackMapper.toStaffResponseDetailsDTO(feedback);
    }    

    // Xóa Feedback
    public void deleteFeedback(int feedbackId) {
        // Kiểm tra xem phản hồi có tồn tại không
        if (!feedbackRepository.existsById(feedbackId)) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phản hồi");
        }
        // Xóa phản hồi theo ID
        feedbackRepository.deleteById(feedbackId);
    }

    // Lấy danh sách tất cả Feedbacks của khách hàng
    public List<CustomerFeedbackDetailsDTO> getAllCustomerFeedbacks() {
        List<FeedbackEntity> feedbacks = feedbackRepository.findAll();
        return feedbackMapper.toCustomerFeedbackDetailsDTOList(feedbacks);
    }

    // Lấy danh sách tất cả phản hồi của nhân viên
    public List<StaffResponseDetailsDTO> getAllStaffResponses() {
        List<FeedbackEntity> feedbacks = feedbackRepository.findAll();
        return feedbackMapper.toStaffResponseDetailsDTOList(feedbacks);
    }
}
