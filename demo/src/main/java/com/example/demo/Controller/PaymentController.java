package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.PaymentDTO.PaymentDTO;
import com.example.demo.DTO.PaymentDTO.TransactionAmountDTO;
import com.example.demo.Service.PaymentService;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    /**
     * Tạo thanh toán ban đầu - chỉ cho phép các vai trò ADMIN, STAFF, và CUSTOMER.
     * 
     * @param paymentDTO Thông tin thanh toán cần tạo.
     * @return Thông tin thanh toán vừa được tạo.
     */
    @RequiresRole({Role.admin, Role.staff, Role.customer})
    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> createInitialPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(createdPayment);
    }

    /**
     * Xử lý thanh toán - chỉ cho phép các vai trò ADMIN và STAFF.
     * 
     * @param transactionAmountDTO Thông tin số tiền giao dịch cần xử lý.
     * @return Thông tin thanh toán đã xử lý.
     */
    @RequiresRole({Role.admin, Role.staff})
    @PostMapping("/processPayment")
    public ResponseEntity<PaymentDTO> processPayment(@RequestBody TransactionAmountDTO transactionAmountDTO) {
        PaymentDTO processedPayment = paymentService.processPayment(transactionAmountDTO);
        return ResponseEntity.ok(processedPayment);
    }

    /**
     * Lấy thông tin thanh toán theo ID.
     * 
     * @param paymentId ID của thanh toán.
     * @return Thông tin thanh toán tương ứng với ID.
     */
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable int paymentId) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }

    /**
     * Lấy danh sách tất cả các thanh toán - chỉ cho phép các vai trò ADMIN, STAFF, và CUSTOMER.
     * 
     * @return Danh sách tất cả các thanh toán.
     */
    @RequiresRole({Role.admin, Role.staff, Role.customer})
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> paymentDTOs = paymentService.getAllPayments();
        return ResponseEntity.ok(paymentDTOs);
    }
}