package com.cinema.booking_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record SignUpRequest(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String displayName,
        MultipartFile avatar
) {
}
