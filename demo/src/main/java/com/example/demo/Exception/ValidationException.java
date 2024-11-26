package com.example.demo.Exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public static class InvalidInputException extends ValidationException {
        public InvalidInputException(String field) {
            super(field + " không hợp lệ.");
        }
    }

    public static class InvalidLengthException extends ValidationException {
        public InvalidLengthException(String field, int maxLength) {
            super(field + " không được vượt quá " + maxLength + " ký tự.");
        }
    }

    public static class InvalidDataTypeException extends ValidationException {
        public InvalidDataTypeException(String field, String expectedType) {
            super(field + " phải là kiểu dữ liệu " + expectedType + ".");
        }
    }

    public static class DuplicateEmailException extends ValidationException {
        public DuplicateEmailException(String email) {
            super("Email đã tồn tại: " + email);
        }
    }

    public static class DuplicatePhoneNumberException extends ValidationException {
        public DuplicatePhoneNumberException(String phoneNumber) {
            super("Số điện thoại đã tồn tại: " + phoneNumber);
        }
    }

    public static class ResourceNotFoundException extends ValidationException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class UnauthorizedAccessException extends ValidationException {
        public UnauthorizedAccessException() {
            super("Bạn không có quyền truy cập tài nguyên này.");
        }
    }

    public static class BadRequestException extends ValidationException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    public static class ConflictException extends ValidationException {
        public ConflictException(String message) {
            super(message);
        }
    }

    public static class DataIntegrityViolationException extends ValidationException {
        public DataIntegrityViolationException(String message) {
            super(message);
        }
    }

    // Ngoại lệ Forbidden khi người dùng không có quyền truy cập
    public static class ForbiddenException extends ValidationException {
        public ForbiddenException(String message) {
            super(message);
        }
    }

    // Ngoại lệ InternalServerError khi xảy ra lỗi máy chủ
    public static class InternalServerErrorException extends ValidationException {
        public InternalServerErrorException(String message) {
            super("Lỗi máy chủ nội bộ: " + message);
        }
    }

    // Ngoại lệ MethodNotAllowed khi phương thức HTTP không được phép
    public static class MethodNotAllowedException extends ValidationException {
        public MethodNotAllowedException(String method) {
            super("Phương thức " + method + " không được phép.");
        }
    }

    // Ngoại lệ TooManyRequests khi người dùng gửi quá nhiều yêu cầu
    public static class TooManyRequestsException extends ValidationException {
        public TooManyRequestsException() {
            super("Quá nhiều yêu cầu. Vui lòng thử lại sau.");
        }
    }

    // Ngoại lệ ServiceUnavailable khi dịch vụ không khả dụng
    public static class ServiceUnavailableException extends ValidationException {
        public ServiceUnavailableException() {
            super("Dịch vụ hiện không khả dụng. Vui lòng thử lại sau.");
        }
    }
}