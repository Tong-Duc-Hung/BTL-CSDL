package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý ValidationException
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Xử lý AuthenticationException
    @ExceptionHandler(AuthException.AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthException.AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // Xử lý AuthorizationException
    @ExceptionHandler(AuthException.AuthorizationException.class)
    public ResponseEntity<String> handleAuthorizationException(AuthException.AuthorizationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    // Xử lý AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        // Thêm log chi tiết
        StringBuilder errorMessage = new StringBuilder("Access Denied: " + ex.getMessage());
        errorMessage.append("\nDetails: ");
        errorMessage.append(ex.getClass().getName());
        errorMessage.append("\nCause: ");
        if (ex.getCause() != null) {
            errorMessage.append(ex.getCause().getMessage());
        } else {
            errorMessage.append("N/A");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage.toString());
    }

    // Xử lý các ngoại lệ khác
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + ex.getMessage());
    }
}