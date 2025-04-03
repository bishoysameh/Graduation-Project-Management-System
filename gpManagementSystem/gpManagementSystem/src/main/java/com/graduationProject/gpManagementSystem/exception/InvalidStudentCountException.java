package com.graduationProject.gpManagementSystem.exception;

public class InvalidStudentCountException extends RuntimeException {
    public InvalidStudentCountException(String message) {
        super(message);
    }
}