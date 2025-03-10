package com.cinema.authentication.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GetAccessTokenByRefreshTokenRequest(@NotBlank String refreshToken) {
}
