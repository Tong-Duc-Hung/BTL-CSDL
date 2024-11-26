package com.example.demo.Config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpSession;
import com.example.demo.Exception.AccessDeniedException;
import com.example.demo.Model.Entity.UserEntity.Role;
import java.util.Arrays;

@Aspect
@Component
public class AuthorizationAspect {

    @Autowired
    private HttpSession session;

    /**
     * Phương thức này được thực thi trước khi bất kỳ phương thức nào
     * được chú thích bằng @RequiresRole.
     *
     * @param requiresRole Annotation chứa các vai trò được yêu cầu.
     */
    @Before("@annotation(requiresRole)")
    public void checkRole(RequiresRole requiresRole) {
        // Lấy giá trị vai trò từ session dưới dạng chuỗi
        String roleString = (String) session.getAttribute("role");

        // Nếu không tìm thấy vai trò trong session, ném ra ngoại lệ AccessDeniedException
        if (roleString == null) {
            throw new AccessDeniedException("Truy cập bị từ chối: Quyền hạn không đủ - Không tìm thấy vai trò trong phiên");
        }

        // Chuyển đổi chuỗi sang enum Role
        Role role;
        try {
            role = Role.valueOf(roleString);
        } catch (IllegalArgumentException e) {
            throw new AccessDeniedException("Truy cập bị từ chối: Vai trò không hợp lệ trong phiên");
        }

        // Kiểm tra xem vai trò của người dùng có thuộc danh sách các vai trò được yêu cầu không
        if (!Arrays.asList(requiresRole.value()).contains(role)) {
            throw new AccessDeniedException("Truy cập bị từ chối: Quyền hạn không đủ");
        }
    }
}