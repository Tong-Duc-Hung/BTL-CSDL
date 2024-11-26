package com.example.demo.Config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.example.demo.Model.Entity.UserEntity.Role;

/**
 * Định nghĩa annotation RequiresRole.
 * Annotation này được sử dụng để chỉ định yêu cầu về vai trò của người dùng
 * khi truy cập vào các phương thức cụ thể.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    /**
     * Các vai trò được yêu cầu.
     * Người dùng phải có ít nhất một trong các vai trò này để truy cập phương thức.
     */
    Role[] value();
}