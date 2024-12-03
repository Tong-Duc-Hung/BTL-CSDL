package com.example.demo.Controller;

import com.example.demo.DTO.SupportedBankDTO.SupportedBankDTO;
import com.example.demo.Service.SupportedBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;

import java.util.List;

@RestController
@RequestMapping("/supportedBanks")
public class SupportedBankController {

    @Autowired
    private SupportedBankService supportedBankService;

    /**
     * Lấy danh sách tất cả các ngân hàng hỗ trợ - cho phép tất cả mọi người.
     * 
     * @return Danh sách tất cả các ngân hàng hỗ trợ.
     */
    @GetMapping
    public ResponseEntity<List<SupportedBankDTO>> getAllSupportedBanks() {
        List<SupportedBankDTO> supportedBanks = supportedBankService.getAllSupportedBanks();
        return ResponseEntity.ok(supportedBanks);
    }

    /**
     * Thêm ngân hàng hỗ trợ mới - chỉ cho phép các vai trò ADMIN và STAFF.
     * 
     * @param supportedBankDTO Thông tin ngân hàng hỗ trợ cần thêm.
     * @return Thông tin ngân hàng hỗ trợ vừa được tạo.
     */
    @RequiresRole({Role.admin, Role.staff})
    @PostMapping("/create")
    public ResponseEntity<SupportedBankDTO> createSupportedBank(@RequestBody SupportedBankDTO supportedBankDTO) {
        SupportedBankDTO createdBank = supportedBankService.createSupportedBank(supportedBankDTO);
        return ResponseEntity.ok(createdBank);
    }

    /**
     * Xóa ngân hàng hỗ trợ theo ID - chỉ cho phép vai trò ADMIN.
     * 
     * @param bankId ID của ngân hàng hỗ trợ cần xóa.
     * @return Trả về mã trạng thái 204 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/{bankId}")
    public ResponseEntity<Void> deleteSupportedBank(@PathVariable int bankId) {
        supportedBankService.deleteSupportedBank(bankId);
        return ResponseEntity.noContent().build();
    }
}