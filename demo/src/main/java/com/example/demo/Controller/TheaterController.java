package com.example.demo.Controller;

import com.example.demo.DTO.AuditoriumDTO.AuditoriumDTO;
import com.example.demo.DTO.TheaterDTO.TheaterDTO;
import com.example.demo.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    /**
     * Tạo rạp chiếu phim - chỉ cho phép ADMIN và STAFF.
     * 
     * @param theaterDTO Thông tin rạp chiếu phim cần tạo.
     * @return Thông tin rạp chiếu phim vừa được tạo.
     */
    @RequiresRole({Role.admin, Role.staff})
    @PostMapping("/create")
    public ResponseEntity<TheaterDTO> createTheater(@RequestBody TheaterDTO theaterDTO) {
        TheaterDTO theater = theaterService.createTheater(theaterDTO);
        return ResponseEntity.ok(theater);
    }

    /**
     * Xóa rạp chiếu phim - chỉ cho phép ADMIN.
     * 
     * @param theaterId ID của rạp chiếu phim cần xóa.
     * @return Trả về mã trạng thái 200 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/delete/{theaterId}")
    public ResponseEntity<Void> deleteTheater(@PathVariable int theaterId) {
        theaterService.deleteTheater(theaterId);
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy rạp chiếu phim theo ID.
     * 
     * @param theaterId ID của rạp chiếu phim.
     * @return Thông tin rạp chiếu phim tương ứng với ID.
     */
    @GetMapping("/{theaterId}")
    public ResponseEntity<TheaterDTO> getTheaterById(@PathVariable int theaterId) {
        Optional<TheaterDTO> theaterDTO = theaterService.getTheaterById(theaterId);
        return theaterDTO.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Lấy danh sách tất cả rạp chiếu phim.
     * 
     * @return Danh sách tất cả các rạp chiếu phim.
     */
    @GetMapping
    public ResponseEntity<List<TheaterDTO>> getAllTheaters() {
        List<TheaterDTO> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }

    /**
     * Tìm kiếm rạp chiếu phim theo tên.
     * 
     * @param theaterName Tên của rạp chiếu phim.
     * @return Danh sách các rạp chiếu phim tương ứng với tên tìm kiếm.
     */
    @GetMapping("/search")
    public ResponseEntity<List<TheaterDTO>> searchTheaterByName(@RequestParam String theaterName) {
        List<TheaterDTO> theaters = theaterService.searchTheaterByName(theaterName);
        return ResponseEntity.ok(theaters);
    }

    /**
     * Lấy danh sách các phòng chiếu theo ID rạp.
     * 
     * @param theaterId ID của rạp chiếu phim.
     * @return Danh sách các phòng chiếu tương ứng với ID rạp.
     */
    @GetMapping("/auditoriums/{theaterId}")
    public ResponseEntity<List<AuditoriumDTO>> getAuditoriumsByTheaterId(@PathVariable int theaterId) {
        List<AuditoriumDTO> auditoriums = theaterService.getAuditoriumsByTheaterId(theaterId);
        return ResponseEntity.ok(auditoriums);
    }
}
