package com.cinema.booking_app.web.rest;

import com.cinema.booking_app.service.dto.AccountDto;
import com.cinema.booking_app.service.dto.request.SignInGoogleRequest;
import com.cinema.booking_app.service.dto.request.SignInRequest;
import com.cinema.booking_app.service.dto.request.SignUpRequest;
import com.cinema.booking_app.service.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface AuthenticationResource {
    @PostMapping(value = "/sign-up", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response<AccountDto> signUp(@Valid SignUpRequest request);

    @PostMapping("/sign-in")
    Response<AccountDto> signIn(@Valid @RequestBody SignInRequest request,final HttpServletResponse response);

    @PreAuthorize("hasAnyAuthority('SCOPE_REFRESH_TOKEN')")
    @PostMapping("/refresh-token")
    Response<AccountDto> getAccessTokenByRefreshToken(HttpServletRequest req);

    @PostMapping("/google/sign-in")
    Response<AccountDto> signInByGoogle(@Valid @RequestBody SignInGoogleRequest request, HttpServletResponse response);
}
