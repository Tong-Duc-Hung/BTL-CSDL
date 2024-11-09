package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.FeedbackDTO.*;
import com.example.demo.Service.FeedbackService;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Tạo phản hồi mới của khách hàng
    @RequiresRole(Role.customer)
    @PostMapping("/customer/create")//
    public ResponseEntity<CustomerFeedbackDetailsDTO> createCustomerFeedback(@RequestBody CreateCustomerFeedbackDTO createCustomerFeedbackDTO) {
        CustomerFeedbackDetailsDTO createdFeedback = feedbackService.createCustomerFeedback(createCustomerFeedbackDTO);
        return ResponseEntity.ok(createdFeedback);
    }

    // Lấy phản hồi theo ID của khách hàng
    @RequiresRole({Role.admin, Role.customer})
    @GetMapping("/customer/{feedbackId}")//
    public ResponseEntity<CustomerFeedbackDetailsDTO> getCustomerFeedbackById(@PathVariable int feedbackId) {
        CustomerFeedbackDetailsDTO feedback = feedbackService.getCustomerFeedbackById(feedbackId);
        return feedback != null ? ResponseEntity.ok(feedback) : ResponseEntity.notFound().build();
    }

    // Cập nhật phản hồi của nhân viên
    @RequiresRole(Role.staff)
    @PutMapping("/staff/{feedbackId}")//
    public ResponseEntity<StaffResponseDetailsDTO> updateStaffResponse(@PathVariable int feedbackId, @RequestBody CreateStaffResponseDTO createStaffResponseDTO) {
        StaffResponseDetailsDTO updatedFeedback = feedbackService.updateStaffResponse(feedbackId, createStaffResponseDTO);
        return ResponseEntity.ok(updatedFeedback);
    }

    // Xóa phản hồi - chỉ cho phép ADMIN
    @RequiresRole(Role.admin)
    @DeleteMapping("/{feedbackId}")//
    public ResponseEntity<Void> deleteFeedback(@PathVariable int feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.ok().build();
    }

    // Lấy danh sách tất cả phản hồi của khách hàng - cho phép CUSTOMER và ADMIN
    @RequiresRole({Role.admin, Role.customer})
    @GetMapping("/customer")//
    public ResponseEntity<List<CustomerFeedbackDetailsDTO>> getAllCustomerFeedbacks() {
        List<CustomerFeedbackDetailsDTO> feedbackList = feedbackService.getAllCustomerFeedbacks();
        return ResponseEntity.ok(feedbackList);
    }

    // Lấy danh sách tất cả phản hồi của nhân viên - cho phép STAFF và ADMIN
    @RequiresRole({Role.staff, Role.admin})
    @GetMapping("/staff")//
    public ResponseEntity<List<StaffResponseDetailsDTO>> getAllStaffResponses() {
        List<StaffResponseDetailsDTO> feedbackList = feedbackService.getAllStaffResponses();
        return ResponseEntity.ok(feedbackList);
    }
}
