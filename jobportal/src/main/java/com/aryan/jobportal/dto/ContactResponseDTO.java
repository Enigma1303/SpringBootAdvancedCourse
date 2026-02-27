package com.aryan.jobportal.dto;

import java.time.Instant;

public record ContactResponseDTO(
        Long id, String name, String email,
        String userType, String subject, String message,
        String status, Instant createdAt
) {
}