package com.cinema.booking_app.service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @NotBlank(message = "Username không được để trống") String username,
        @NotBlank(message = "Password không được để trống") String password,
        boolean rememberMe,

        @JsonIgnore
        HttpServletResponse response
) {
}
