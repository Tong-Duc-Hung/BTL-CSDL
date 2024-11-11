package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.Entity.UserEntity;
import com.example.demo.Model.Entity.UserEntity.Role;
import com.example.demo.Repository.UserRepository;
import com.example.demo.DTO.UserDTO.*;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Exception.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private HttpSession session;

    // Phương thức đăng ký người dùng mới
    public UserRegistrationDTO register(UserRegistrationDTO registrationDTO) {
        UserEntity user = userMapper.toUserEntity(registrationDTO);

        // Kiểm tra xem email đã tồn tại hay chưa
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new ValidationException.DuplicateEmailException(registrationDTO.getEmail());
        }

        // Kiểm tra xem số điện thoại đã tồn tại hay chưa
        if (userRepository.existsByPhoneNumber(registrationDTO.getPhoneNumber())) {
            throw new ValidationException.DuplicatePhoneNumberException(registrationDTO.getPhoneNumber());
        }

        // Đặt vai trò mặc định nếu chưa có
        if (user.getRole() == null) {
            user.setRole(Role.customer);
        }

        // Đặt ảnh đại diện mặc định
        user.setAvatar("https://cdn.kona-blue.com/upload/kona-blue_com/post/images/2024/09/18/457/avatar-mac-dinh-12.jpg");

        // Lưu người dùng mới vào repository
        userRepository.save(user);

        // Trả về đối tượng UserRegistrationDTO đã đăng ký
        return userMapper.toUserRegistrationDTO(user);
    }

    // Phương thức đăng nhập người dùng
    public LoginDTO login(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByUserName(loginDTO.getUserName())
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Tên người dùng hoặc mật khẩu không đúng"));
    
        // Kiểm tra mật khẩu
        if (!loginDTO.getPassword().equals(user.getPassword())) {
            throw new ValidationException.BadRequestException("Tên người dùng hoặc mật khẩu không đúng");
        }
    
        // Chuyển đổi UserEntity sang LoginDTO
        return userMapper.toLoginDTO(user);
    }
    
    // Phương thức lấy thông tin người dùng theo ID
    public UserProfileDTO getUserProfile(int userId) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Người dùng không tồn tại"));

        // Chuyển đổi UserEntity sang UserProfileDTO
        return userMapper.toUserProfileDTO(user);
    }

    // Phương thức lấy vai trò của người dùng
    public UserRoleDTO getUserRole(int userId) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Người dùng không tồn tại"));

        // Chuyển đổi UserEntity sang UserRoleDTO
        return userMapper.toUserRoleDTO(user);
    }

    // Phương thức cập nhật thông tin người dùng
    public UserProfileDTO updateUser(UpdateUserDTO updateUserDTO) {
        String currentUsername = (String) session.getAttribute("user");
        UserEntity user = userRepository.findByUserName(currentUsername)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Người dùng không tồn tại"));

        // Cập nhật email nếu đã thay đổi và chưa tồn tại
        if (updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(updateUserDTO.getEmail())) {
                throw new IllegalArgumentException("Email đã tồn tại");
            }
            user.setEmail(updateUserDTO.getEmail());
        }

        // Cập nhật số điện thoại nếu đã thay đổi và chưa tồn tại
        if (updateUserDTO.getPhoneNumber() != null && !updateUserDTO.getPhoneNumber().equals(user.getPhoneNumber())) {
            if (userRepository.existsByPhoneNumber(updateUserDTO.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number đã tồn tại");
            }
            user.setPhoneNumber(updateUserDTO.getPhoneNumber());
        }

        // Cập nhật ảnh đại diện nếu có
        if (updateUserDTO.getAvatar() != null) {
            user.setAvatar(updateUserDTO.getAvatar());
        }

        // Lưu người dùng đã cập nhật vào repository
        user = userRepository.save(user);

        // Chuyển đổi UserEntity sang UserProfileDTO
        return userMapper.toUserProfileDTO(user);
    }

    // Phương thức thay đổi mật khẩu
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        String currentUsername = (String) session.getAttribute("user"); // Lấy tên người dùng từ session
        UserEntity user = userRepository.findByUserName(currentUsername)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Người dùng không tồn tại"));
    
        // Kiểm tra mật khẩu cũ
        if (!changePasswordDTO.getOldPassword().equals(user.getPassword())) {
            throw new ValidationException.BadRequestException("Mật khẩu cũ không đúng");
        }
    
        // Kiểm tra xem mật khẩu mới và xác nhận mật khẩu có khớp không
        if (!changePasswordDTO.isPasswordMatch()) {
            throw new ValidationException.BadRequestException("Mật khẩu mới và xác nhận mật khẩu không khớp");
        }
    
        // Kiểm tra xem mật khẩu mới có khác mật khẩu cũ không
        if (!changePasswordDTO.isNewPasswordDifferent()) {
            throw new ValidationException.BadRequestException("Mật khẩu mới và mật khẩu cũ giống nhau");
        }
    
        // Đặt mật khẩu mới
        user.setPassword(changePasswordDTO.getNewPassword());

        // Lưu mật khẩu đã cập nhật vào repository
        userRepository.save(user);
    }

    // Phương thức lấy tất cả người dùng
    public List<UserProfileDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(userMapper::toUserProfileDTO)
            .collect(Collectors.toList());
    }

    // Phương thức xóa người dùng theo ID
    public void deleteUser(int userId) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Người dùng không tồn tại"));
        
        // Xóa người dùng khỏi repository
        userRepository.delete(user);
    }

    // Phương thức tìm người dùng theo tên đăng nhập
    public UserProfileDTO findUserByUserName(String userName) {
        UserEntity user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Người dùng không tồn tại"));
        
        // Chuyển đổi UserEntity sang UserProfileDTO
        return userMapper.toUserProfileDTO(user);
    }
}
