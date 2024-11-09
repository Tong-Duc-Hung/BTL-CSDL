package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.SeatDTO.SeatDTO;
import com.example.demo.Service.SeatService;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;
import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    /**
     * Tạo ghế mới - chỉ cho phép các vai trò ADMIN và STAFF.
     * 
     * @param seatDTO Thông tin ghế cần tạo.
     * @return Thông tin ghế vừa được tạo.
     */
    @RequiresRole({Role.admin, Role.staff})
    @PostMapping("/create")
    public ResponseEntity<SeatDTO> createSeat(@RequestBody SeatDTO seatDTO) {
        SeatDTO createdSeat = seatService.createSeat(seatDTO);
        return ResponseEntity.ok(createdSeat);
    }

    /**
     * Lấy thông tin ghế theo ID.
     * 
     * @param seatId ID của ghế.
     * @return Thông tin ghế tương ứng với ID.
     */
    @GetMapping("/{seatId}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable int seatId) {
        SeatDTO seat = seatService.getSeatById(seatId);
        return ResponseEntity.ok(seat);
    }

    /**
     * Xóa ghế theo ID - chỉ cho phép vai trò ADMIN.
     * 
     * @param seatId ID của ghế cần xóa.
     * @return Trả về mã trạng thái 200 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deleteSeat(@PathVariable int seatId) {
        seatService.deleteSeat(seatId);
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy danh sách tất cả ghế.
     * 
     * @return Danh sách tất cả các ghế.
     */
    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        List<SeatDTO> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    /**
     * Lấy danh sách ghế theo ID phòng chiếu.
     * 
     * @param auditoriumId ID của phòng chiếu.
     * @return Danh sách các ghế tương ứng với ID phòng chiếu.
     */
    @GetMapping("/auditorium/{auditoriumId}")
    public ResponseEntity<List<SeatDTO>> getSeatsByAuditoriumId(@PathVariable int auditoriumId) {
        List<SeatDTO> seats = seatService.getSeatsByAuditoriumId(auditoriumId);
        return ResponseEntity.ok(seats);
    }
}