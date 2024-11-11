package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.BookingDTO.*;
import com.example.demo.DTO.TicketDTO.TicketDTO;
import com.example.demo.Mapper.BookingMapper;
import com.example.demo.Model.Entity.BookingEntity;
import com.example.demo.Model.Entity.BookingEntity.BookingStatus;
import com.example.demo.Model.Entity.PaymentEntity.PaymentStatus;
import com.example.demo.Model.Entity.PaymentEntity;
import com.example.demo.Model.Entity.TicketEntity;
import com.example.demo.Model.Entity.TicketEntity.TicketStatus;
import com.example.demo.Model.Entity.ShowtimeEntity;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.ShowtimeRepository;
import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Repository.TicketRepository;
import com.example.demo.Exception.ValidationException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BookingMapper bookingMapper;

    // Tạo booking
    public BookingDTO createBooking(CreateBookingDTO createBookingDTO) {
        // Chuyển đổi DTO thành Entity
        BookingEntity bookingEntity = bookingMapper.toCreateBookingEntity(createBookingDTO);
        
        // Lấy suất chiếu
        ShowtimeEntity showtimeEntity = showtimeRepository.findById(createBookingDTO.getShowtimeId())
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Suất chiếu không tồn tại"));
    
        // Liên kết suất chiếu với booking
        bookingEntity.setShowtime(showtimeEntity);
    
        // Lưu BookingEntity
        bookingEntity.setSeatsBooked(createBookingDTO.getSeatIds().size());
        bookingEntity.setOriginalSeatsBooked(createBookingDTO.getSeatIds().size());
        BookingEntity savedBookingEntity = bookingRepository.save(bookingEntity);
    
        // Tạo và lưu các vé liên quan đến booking
        for (Integer seatId : createBookingDTO.getSeatIds()) {
            // Kiểm tra nếu vé cho ghế này đã tồn tại
            boolean isSeatBooked = ticketRepository.existsByShowtime_ShowtimeIdAndSeat_SeatIdAndTicketStatusNot(
                createBookingDTO.getShowtimeId(), seatId, TicketStatus.canceled);
            if (isSeatBooked) {
                throw new IllegalArgumentException("Ghế với ID " + seatId + " đã được đặt.");
            }
    
            // Tạo vé cho ghế này
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setSeatId(seatId);
            ticketDTO.setShowtimeId(createBookingDTO.getShowtimeId());
            ticketDTO.setBookingId(savedBookingEntity.getBookingId()); // Liên kết vé với booking đã lưu
    
            // Tạo vé và lưu thông tin vé vừa tạo
            ticketService.createTicket(ticketDTO);
        }
    
        // Trả về BookingDTO đã lưu
        return bookingMapper.toBookingDTO(savedBookingEntity);
    }

    // Xem thông tin chi tiết đặt vé
    public BookingDTO getBookingByIdAndUserId(int bookingId, int userId) {
        BookingEntity booking = bookingRepository.findByBookingIdAndUser_UserId(bookingId, userId);
        if (booking == null) {
            throw new ValidationException.ResourceNotFoundException("Booking không tìm thấy hoặc người dùng không được uỷ quyền");
        }
        return bookingMapper.toBookingDTO(booking);
    }

    // Lấy danh sách đặt vé
    public List<BookingDTO> getAllBookings() {
        List<BookingEntity> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingDTOList(bookings);
    }

    // Hủy đặt vé
    public BookingDTO cancelBooking(CancelBookingDTO cancelBookingDTO) {
        BookingEntity booking = bookingRepository.findById(cancelBookingDTO.getBookingId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Booking không tìm thấy"));
    
        // Hủy tất cả các vé liên quan đến booking này
        List<TicketEntity> tickets = ticketRepository.findByBooking_BookingIdAndTicketStatusNot(cancelBookingDTO.getBookingId(), TicketStatus.canceled);
        for (TicketEntity ticket : tickets) {
            ticket.setTicketStatus(TicketStatus.canceled);
            ticketRepository.save(ticket);
        }
    
        // Cập nhật trạng thái booking
        booking.setBookingStatus(BookingStatus.canceled);
        booking = bookingRepository.save(booking);
    
        // Tìm và cập nhật payment liên quan đến booking này
        PaymentEntity payment = paymentRepository.findByBooking_BookingId(cancelBookingDTO.getBookingId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy thanh toán cho booking id: " + cancelBookingDTO.getBookingId()));
        payment.setTotalAmount(BigDecimal.ZERO);
        payment.setPaymentStatus(PaymentStatus.canceled);
        paymentRepository.save(payment);
    
        return bookingMapper.toBookingDTO(booking);
    }

    // Lấy danh sách đặt vé theo userId
    public List<BookingDTO> getBookingsByUserId(int userId) {
        List<BookingEntity> bookings = bookingRepository.findByUser_UserId(userId);
        return bookingMapper.toBookingDTOList(bookings);
    }
}
