package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.Entity.UserEntity;
import java.util.Optional;

// Khai báo giao diện UserRepository kế thừa từ JpaRepository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    // Phương thức tìm kiếm người dùng theo tên người dùng (username)
    Optional<UserEntity> findByUserName(String userName);

    // Phương thức kiểm tra xem email có tồn tại trong cơ sở dữ liệu không
    boolean existsByEmail(String email);

    // Phương thức kiểm tra xem số điện thoại có tồn tại trong cơ sở dữ liệu không
    boolean existsByPhoneNumber(String phoneNumber);

    // Phương thức tìm người dùng theo ID
    Optional<UserEntity> findById(int userId);

    // Phương thức tìm người dùng theo email
    Optional<UserEntity> findByEmail(String email);

    // Phương thức tìm người dùng theo số điện thoại
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}