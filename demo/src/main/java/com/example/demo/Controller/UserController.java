package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.UserDTO.*;
import com.example.demo.Service.UserService;
import com.example.demo.Model.Entity.UserEntity.Role;
import org.springframework.http.HttpStatus;

import java.util.List;
import jakarta.servlet.http.HttpSession;
import com.example.demo.Config.RequiresRole;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Đăng ký người dùng mới.
     * 
     * @param userRegistrationDTO Thông tin đăng ký người dùng.
     * @return Đăng ký thành công trả về thông tin người dùng, nếu không trả về mã lỗi 400.
     */
    @PostMapping("/register")
    public ResponseEntity<UserRegistrationDTO> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        
        UserRegistrationDTO registeredUser = userService.register(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    /**
     * Đăng nhập người dùng.
     * 
     * @param loginDTO Thông tin đăng nhập.
     * @param session HttpSession hiện tại.
     * @return Đăng nhập thành công trả về thông tin đăng nhập, nếu không trả về mã lỗi 401.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        if (loginDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        LoginDTO loggedInUser = userService.login(loginDTO);
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Lưu thông tin người dùng vào session
        session.setAttribute("user", loggedInUser.getUserName());
        session.setAttribute("role", loggedInUser.getRole());
        return ResponseEntity.ok(loggedInUser);
    }

    /**
     * Đăng xuất người dùng.
     * 
     * @param session HttpSession hiện tại.
     * @return Trả về mã trạng thái 200 khi đăng xuất thành công.
     */
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate(); // Huỷ session
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy thông tin cá nhân của người dùng.
     * 
     * @param userId ID của người dùng.
     * @return Thông tin cá nhân của người dùng nếu tồn tại, nếu không trả về mã lỗi 404.
     */
    @RequiresRole({Role.admin, Role.staff, Role.customer})
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable int userId) {
        UserProfileDTO userProfile = userService.getUserProfile(userId);
        if (userProfile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userProfile);
    }

    /**
     * Lấy vai trò của người dùng.
     * 
     * @param userId ID của người dùng.
     * @return Vai trò của người dùng nếu tồn tại, nếu không trả về mã lỗi 404.
     */
    @RequiresRole({Role.admin, Role.staff})
    @GetMapping("/{userId}/role")
    public ResponseEntity<UserRoleDTO> getUserRole(@PathVariable int userId) {
        UserRoleDTO userRole = userService.getUserRole(userId);
        if (userRole == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRole);
    }

    /**
     * Cập nhật thông tin cá nhân của người dùng.
     * 
     * @param updates Thông tin cần cập nhật.
     * @return Thông tin người dùng sau khi cập nhật.
     */
    @RequiresRole({Role.admin, Role.staff, Role.customer})
    @PatchMapping("/me")
    public ResponseEntity<UserProfileDTO> updateUser(@RequestBody UpdateUserDTO updates) {
        if (updates == null) {
            return ResponseEntity.badRequest().build();
        }

        UserProfileDTO updatedUser = userService.updateUser(updates);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Đổi mật khẩu người dùng.
     * 
     * @param changePasswordDTO Thông tin đổi mật khẩu.
     * @return Trả về mã trạng thái 200 khi đổi mật khẩu thành công.
     */
    @RequiresRole({Role.admin, Role.staff, Role.customer})
    @PutMapping("/me/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        if (changePasswordDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        userService.changePassword(changePasswordDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy danh sách tất cả người dùng.
     * 
     * @return Danh sách tất cả người dùng.
     */
    @RequiresRole({Role.admin, Role.staff})
    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getAllUsers() {
        List<UserProfileDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Xóa người dùng theo ID.
     * 
     * @param userId ID của người dùng.
     * @return Trả về mã trạng thái 204 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Tìm kiếm người dùng theo tên.
     * 
     * @param userName Tên người dùng.
     * @return Thông tin người dùng nếu tìm thấy, nếu không trả về mã lỗi 404.
     */
    @GetMapping("/find")
    public ResponseEntity<UserProfileDTO> findUserByUserName(@RequestParam String userName) {
        if (userName == null || userName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        UserProfileDTO user = userService.findUserByUserName(userName);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
