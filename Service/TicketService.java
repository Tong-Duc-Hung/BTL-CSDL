package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.TicketDTO.TicketDTO;
import com.example.demo.DTO.TicketDTO.CancelTicketDTO;
import com.example.demo.Mapper.TicketMapper;
import com.example.demo.Model.Entity.*;
import com.example.demo.Model.Entity.BookingEntity.BookingStatus;
import com.example.demo.Model.Entity.PaymentEntity.PaymentStatus;
import com.example.demo.Model.Entity.TicketEntity.TicketStatus;
import com.example.demo.Repository.*;
import com.example.demo.Exception.ValidationException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private PaymentRepository paymentRepository;

    // Phương thức tạo vé mới
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        // Lấy suất chiếu từ repository
        ShowtimeEntity showtime = showtimeRepository.findById(ticketDTO.getShowtimeId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Showtime không tồn tại"));

        // Lấy ghế từ repository
        SeatEntity seat = seatRepository.findById(ticketDTO.getSeatId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Seat không tồn tại"));

        // Kiểm tra xem ghế có thuộc về suất chiếu và không bị đặt trước không
        boolean isSeatBooked = ticketRepository.existsByShowtime_ShowtimeIdAndSeat_SeatIdAndTicketStatusNot(
                ticketDTO.getShowtimeId(), ticketDTO.getSeatId(), TicketStatus.canceled);
        if (isSeatBooked) {
            throw new ValidationException.ResourceNotFoundException("Ghế đã được đặt cho suất chiếu này");
        }

        // Lấy thông tin đặt vé từ repository
        BookingEntity booking = bookingRepository.findByBookingId(ticketDTO.getBookingId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Booking không tồn tại"));

        // Lấy thông tin phim từ suất chiếu
        MovieEntity movie = movieRepository.findById(showtime.getMovie().getMovieId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Movie không tồn tại"));

        // Tính toán giá vé tổng
        BigDecimal totalPrice = calculateTotalTicketPrice(seat, showtime, movie);
        ticketDTO.setTicketPrice(totalPrice);

        // Chuyển đổi DTO sang Entity và lưu vé
        TicketEntity ticketEntity = ticketMapper.toTicketEntity(ticketDTO);
        ticketEntity.setSeat(seat);
        ticketEntity.setShowtime(showtime);
        ticketEntity.setBooking(booking);
        ticketEntity.setTicketStatus(TicketStatus.confirmed);

        // Lưu vé vào repository và trả về DTO đã lưu
        TicketEntity savedTicket = ticketRepository.save(ticketEntity);
        return ticketMapper.toTicketDTO(savedTicket);
    }

    // Phương thức xem thông tin chi tiết vé theo ID
    public TicketDTO getTicketById(int ticketId) {
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Ticket không tồn tại"));
        return ticketMapper.toTicketDTO(ticket);
    }

    // Phương thức hủy vé (đổi trạng thái thành canceled) cho khách hàng sử dụng CancelTicketDTO
    public void cancelTicket(CancelTicketDTO cancelTicketDTO) {
        TicketEntity ticket = ticketRepository.findById(cancelTicketDTO.getTicketId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Ticket không tồn tại"));
        
        // Kiểm tra nếu bookingId của vé khớp với bookingId trong DTO
        if (ticket.getBooking().getBookingId() != cancelTicketDTO.getBookingId()) {
            throw new ValidationException.ResourceNotFoundException("Vé không thuộc về booking này");
        }
    
        // Hủy vé
        ticket.setTicketStatus(TicketStatus.canceled);
        ticketRepository.save(ticket);
    
        // Cập nhật tổng số tiền thanh toán sau khi hủy vé
        PaymentEntity payment = paymentRepository.findByBooking_BookingId(cancelTicketDTO.getBookingId())
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Payment không tồn tại cho booking id: " + cancelTicketDTO.getBookingId()));
    
        BigDecimal ticketPrice = ticket.getTicketPrice();
        payment.setTotalAmount(payment.getTotalAmount().subtract(ticketPrice));
        if(payment.getTotalAmount().compareTo(BigDecimal.ZERO) == 0) {
            payment.setPaymentStatus(PaymentStatus.canceled);
            BookingEntity booking = bookingRepository.findById(cancelTicketDTO.getBookingId())
             .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Booking không tồn tại"));
            booking.setBookingStatus(BookingStatus.canceled);
            bookingRepository.save(booking);
        }
    
        // Lưu thay đổi vào cơ sở dữ liệu
        paymentRepository.save(payment);
    }

    // Phương thức hủy vé cho quản trị viên (Admin)
    public void cancelTicketAdmin(int ticketId) {
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Ticket không tồn tại"));
        ticket.setTicketStatus(TicketStatus.canceled);
        ticketRepository.save(ticket);
    }

    // Phương thức xóa vé hoàn toàn khỏi cơ sở dữ liệu
    public void deleteTicket(int ticketId) {
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Ticket không tồn tại"));
        ticketRepository.delete(ticket);
    }

    // Phương thức tìm danh sách vé theo bookingId
    public List<TicketDTO> getTicketsByBookingId(int bookingId) {
        List<TicketEntity> tickets = ticketRepository.findByBooking_BookingId(bookingId);
        return ticketMapper.toTicketDTOList(tickets);
    }

    // Phương thức lấy tất cả vé
    public List<TicketDTO> getAllTickets() {
        List<TicketEntity> tickets = ticketRepository.findAll();
        if (tickets.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy vé nào.");
        }
        return ticketMapper.toTicketDTOList(tickets);
    }

    /**
     * Phương thức tính giá vé tổng
     * Tính giá vé dựa trên giá của phim, ghế và suất chiếu.
     */
    public static BigDecimal calculateTotalTicketPrice(SeatEntity seat, ShowtimeEntity showtime, MovieEntity movie) {
        BigDecimal seatPrice = seat.getSeatPrice();
        BigDecimal showtimePrice = showtime.getTimePrice();
        BigDecimal moviePrice = movie.getMoviePrice();
        return seatPrice.add(showtimePrice).add(moviePrice);
    }
}
