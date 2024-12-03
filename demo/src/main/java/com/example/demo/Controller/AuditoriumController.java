package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.AuditoriumDTO.AuditoriumDTO;
import com.example.demo.Service.AuditoriumService;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;
import java.util.List;

@RestController
@RequestMapping("/auditoriums")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    /**
     * Tạo phòng chiếu mới - chỉ cho phép vai trò ADMIN và STAFF.
     * 
     * @param auditoriumDTO Thông tin phòng chiếu cần tạo.
     * @return Thông tin phòng chiếu vừa được tạo.
     */
    @RequiresRole({Role.admin, Role.staff})
    @PostMapping("/create")
    public ResponseEntity<AuditoriumDTO> createAuditorium(@RequestBody AuditoriumDTO auditoriumDTO) {
        AuditoriumDTO createdAuditorium = auditoriumService.createAuditorium(auditoriumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuditorium);
    }

    /**
     * Xem thông tin phòng chiếu theo ID.
     * 
     * @param auditoriumId ID của phòng chiếu.
     * @return Thông tin phòng chiếu tương ứng với ID.
     */
    @GetMapping("/{auditoriumId}")
    public ResponseEntity<AuditoriumDTO> getAuditoriumById(@PathVariable int auditoriumId) {
        AuditoriumDTO auditorium = auditoriumService.getAuditoriumById(auditoriumId);
        return auditorium != null ? ResponseEntity.ok(auditorium) : ResponseEntity.notFound().build();
    }

    /**
     * Xóa phòng chiếu - chỉ cho phép vai trò ADMIN.
     * 
     * @param auditoriumId ID của phòng chiếu cần xóa.
     * @return Trả về mã trạng thái 204 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/{auditoriumId}")
    public ResponseEntity<Void> deleteAuditorium(@PathVariable int auditoriumId) {
        auditoriumService.deleteAuditorium(auditoriumId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lấy danh sách tất cả phòng chiếu.
     * 
     * @return Danh sách tất cả các phòng chiếu.
     */
    @GetMapping
    public ResponseEntity<List<AuditoriumDTO>> getAllAuditoriums() {
        List<AuditoriumDTO> auditoriums = auditoriumService.getAllAuditoriums();
        return ResponseEntity.ok(auditoriums);
    }
}
