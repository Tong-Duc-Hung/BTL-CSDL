package com.example.demo.Mapper;

import com.example.demo.DTO.UserDTO.*;
import com.example.demo.Model.Entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    // Ánh xạ từ UserRegistrationDTO sang UserEntity khi người dùng đăng ký
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    UserEntity toUserEntity(UserRegistrationDTO user);

    // Ánh xạ từ UserEntity sang UserRegistrationDTO (bỏ password để bảo mật)
    @Mapping(target = "password", ignore = true)
    UserRegistrationDTO toUserRegistrationDTO(UserEntity user);

    // Ánh xạ từ LoginDTO sang UserEntity khi người dùng đăng nhập
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    UserEntity toUserEntity(LoginDTO login);

    // Ánh xạ từ UserEntity sang LoginDTO khi người dùng đăng nhập
    LoginDTO toLoginDTO(UserEntity user);

    // Ánh xạ từ UserEntity sang UserProfileDTO để lấy thông tin người dùng
    UserProfileDTO toUserProfileDTO(UserEntity user);

    // Ánh xạ từ UserEntity sang UserRoleDTO để lấy thông tin vai trò người dùng
    @Mapping(source = "role", target = "role")
    UserRoleDTO toUserRoleDTO(UserEntity user);

    // Ánh xạ từ UserEntity sang UpdateUserDTO
    UpdateUserDTO toUpdateUserDTO(UserEntity user);
}