package com.graduationProject.gpManagementSystem.exception;

import lombok.Builder;

@Builder
public record ViolationErrors(String fieldName, String message) {
}
