package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.TicketDTO.TicketDTO;
import com.example.demo.DTO.TicketDTO.CancelTicketDTO;
import com.example.demo.Service.TicketService;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Tạo vé xem phim.
     * 
     * @param ticketDTO Thông tin vé cần tạo.
     * @return Thông tin vé vừa được tạo.
     */
    @RequiresRole(Role.customer)
    @PostMapping("/create")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(createdTicket);
    }

    /**
     * Lấy vé xem phim theo ID.
     * 
     * @param ticketId ID của vé.
     * @return Thông tin vé tương ứng với ID.
     */
    @RequiresRole({Role.customer, Role.admin})
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable int ticketId) {
        TicketDTO ticket = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    /**
     * Hủy vé cho khách hàng.
     * 
     * @param cancelTicketDTO Thông tin hủy vé.
     * @return Trả về mã trạng thái 200 khi hủy vé thành công.
     */
    @RequiresRole(Role.customer)
    @PutMapping("/cancel")
    public ResponseEntity<Void> cancelTicketForCustomer(@RequestBody CancelTicketDTO cancelTicketDTO) {
        ticketService.cancelTicket(cancelTicketDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Hủy vé - dành cho admin.
     * 
     * @param ticketId ID của vé cần hủy.
     * @return Trả về mã trạng thái 200 khi hủy vé thành công.
     */
    @RequiresRole(Role.admin)
    @PutMapping("/cancel/admin/{ticketId}")
    public ResponseEntity<Void> cancelTicketAdmin(@PathVariable int ticketId) {
        ticketService.cancelTicketAdmin(ticketId);
        return ResponseEntity.ok().build();
    }

    /**
     * Xóa vé hoàn toàn - chỉ cho phép ADMIN.
     * 
     * @param ticketId ID của vé cần xóa.
     * @return Trả về mã trạng thái 200 khi xóa vé thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy vé theo bookingId.
     * 
     * @param bookingId ID của booking.
     * @return Danh sách các vé tương ứng với bookingId.
     */
    @RequiresRole({Role.customer, Role.admin})
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByBookingId(@PathVariable int bookingId) {
        List<TicketDTO> tickets = ticketService.getTicketsByBookingId(bookingId);
        if (tickets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(tickets);
    }

    /**
     * Lấy danh sách tất cả vé xem phim.
     * 
     * @return Danh sách tất cả các vé.
     */
    @RequiresRole({Role.admin, Role.staff})
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        if (tickets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(tickets);
    }

}
