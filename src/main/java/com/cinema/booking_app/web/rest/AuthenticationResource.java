package com.cinema.booking_app.web.rest;

import com.cinema.booking_app.service.dto.AccountDto;
import com.cinema.booking_app.service.dto.request.SignInRequest;
import com.cinema.booking_app.service.dto.request.SignUpRequest;
import com.cinema.booking_app.service.dto.response.Response;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface AuthenticationResource {
    @PostMapping("/sign-up")
    Response<AccountDto> signUp(@Valid @RequestBody SignUpRequest request);

    @PostMapping("/sign-in")
    Response<AccountDto> signIn(@Valid @RequestBody SignInRequest request,final HttpServletResponse response);
}
