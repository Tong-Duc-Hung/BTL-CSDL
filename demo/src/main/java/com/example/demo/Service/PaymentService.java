package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.PaymentDTO.PaymentDTO;
import com.example.demo.DTO.PaymentDTO.TransactionAmountDTO;
import com.example.demo.Mapper.PaymentMapper;
import com.example.demo.Model.Entity.BookingEntity;
import com.example.demo.Model.Entity.PaymentEntity;
import com.example.demo.Model.Entity.PaymentEntity.PaymentStatus;
import com.example.demo.Model.Entity.SupportedBankEntity;
import com.example.demo.Model.Entity.TicketEntity;
import com.example.demo.Model.Entity.TicketEntity.TicketStatus;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Repository.TicketRepository;
import com.example.demo.Repository.SupportedBankRepository;
import com.example.demo.Exception.ValidationException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private PaymentMapper paymentMapper;
    
    @Autowired
    private SupportedBankRepository supportedBankRepository;
    
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Tạo Payment ban đầu với trạng thái Pending
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        // Tìm kiếm booking
        BookingEntity bookingEntity = bookingRepository.findByBookingId(paymentDTO.getBookingId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Booking không tồn tại"));
    
        // Kiểm tra xem có PaymentEntity nào đã tồn tại cho bookingId này không
        Optional<PaymentEntity> existingPayment = paymentRepository.findByBooking_BookingId(paymentDTO.getBookingId());
        if (existingPayment.isPresent()) {
            throw new ValidationException.BadRequestException("Thanh toán cho booking này đã tồn tại.");
        }
    
        // Lấy danh sách vé từ booking
        List<TicketEntity> tickets = ticketRepository.findByBooking_BookingId(paymentDTO.getBookingId());
        if (tickets.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy vé nào liên quan đến booking này");
        }
    
        // Tính toán tổng số tiền từ danh sách vé (chỉ tính vé có trạng thái 'confirmed')
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (TicketEntity ticket : tickets) {
            if (ticket.getTicketStatus() == TicketStatus.confirmed) {
                totalAmount = totalAmount.add(ticket.getTicketPrice());
            }
        }
    
        // Nếu không có vé nào được xác nhận, không thể tạo thanh toán
        if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidationException.ResourceNotFoundException("Không có vé nào đủ điều kiện để thanh toán");
        }
    
        paymentDTO.setTotalAmount(totalAmount); // Cập nhật tổng số tiền
        paymentDTO.setPaymentStatus(PaymentStatus.pending);
    
        // Tìm kiếm ngân hàng hỗ trợ từ ID ngân hàng
        SupportedBankEntity bankEntity = supportedBankRepository.findById(paymentDTO.getBankId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Ngân hàng không tồn tại"));
    
        // Chuyển đổi DTO thành Entity và lưu vào cơ sở dữ liệu
        PaymentEntity paymentEntity = paymentMapper.toPaymentEntity(paymentDTO);
        paymentEntity.setBooking(bookingEntity);
        paymentEntity.setBank(bankEntity);
        paymentEntity.setPaymentDate(new Date(System.currentTimeMillis()));
        PaymentEntity savedPayment = paymentRepository.save(paymentEntity);
        
        return paymentMapper.toPaymentDTO(savedPayment);
    }

    // Xử lý thanh toán và cập nhật trạng thái
    public PaymentDTO processPayment(TransactionAmountDTO transactionAmountDTO) {
        // Lấy PaymentEntity từ paymentId
        PaymentEntity paymentEntity = paymentRepository.findById(transactionAmountDTO.getPaymentId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Payment không tồn tại"));
    
        // Kiểm tra trạng thái thanh toán hiện tại
        if (paymentEntity.getPaymentStatus() == PaymentStatus.completed) {
            throw new ValidationException.BadRequestException("Thanh toán đã được thực hiện trước đó.");
        } else if (paymentEntity.getPaymentStatus() == PaymentStatus.canceled) {
            throw new ValidationException.BadRequestException("Thanh toán đã bị hủy trước đó.");
        }
    
        // Lấy tổng số tiền từ PaymentEntity
        BigDecimal totalAmount = paymentEntity.getTotalAmount();
    
        // Lấy số tiền đã thanh toán từ TransactionAmountDTO
        BigDecimal paidAmount = transactionAmountDTO.getPaidAmount();
    
        // Kiểm tra xem số tiền thanh toán có hợp lệ không
        if (paidAmount.compareTo(totalAmount) < 0) {
            paymentEntity.setPaymentStatus(PaymentStatus.canceled);
            throw new ValidationException.BadRequestException("Thanh toán thất bại: Số tiền không đủ.");
        } else if (paidAmount.compareTo(totalAmount) > 0) {
            paymentEntity.setPaymentStatus(PaymentStatus.canceled);
            throw new ValidationException.BadRequestException("Thanh toán thất bại: Số tiền lớn hơn.");
        } else {
            paymentEntity.setPaymentStatus(PaymentStatus.completed);
        }
    
        // Cập nhật trạng thái thanh toán và lưu lại PaymentEntity
        PaymentEntity updatedPayment = paymentRepository.save(paymentEntity);
    
        // Chuyển đổi PaymentEntity thành DTO và trả về
        return paymentMapper.toPaymentDTO(updatedPayment);
    }     

    // Lấy thông tin chi tiết Payment
    public PaymentDTO getPaymentById(int paymentId) {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Payment không tồn tại"));
        return paymentMapper.toPaymentDTO(paymentEntity);
    }

    // Lấy danh sách tất cả Payments
    public List<PaymentDTO> getAllPayments() {
        List<PaymentEntity> paymentEntities = paymentRepository.findAll();
        return paymentMapper.toDTOList(paymentEntities);
    }
}
