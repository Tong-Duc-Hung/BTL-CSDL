package com.example.demo.Exception;

public class AuthException extends RuntimeException {
    private final String type;

    public AuthException(String type, String message) {
        super(message);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static class AuthenticationException extends AuthException {
        public AuthenticationException(String message) {
            super("Authentication", message);
        }
    }

    public static class AuthorizationException extends AuthException {
        public AuthorizationException(String message) {
            super("Authorization", message);
        }
    }
}