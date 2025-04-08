package com.cinema.booking_app.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record SignUpRequest(
        @NotBlank
        String username,
        @NotBlank
        String password,
        MultipartFile avatar
) {
}
