package com.cinema.booking_app.service;

import com.cinema.booking_app.service.dto.AccountDto;
import com.cinema.booking_app.service.dto.request.SignInGoogleRequest;
import com.cinema.booking_app.service.dto.request.SignInRequest;
import com.cinema.booking_app.service.dto.request.SignUpRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface AuthenticationService {
    AccountDto signUp(SignUpRequest request);

    AccountDto signIn(SignInRequest request, HttpServletResponse response);

    AccountDto accessTokenByRefreshToken(HttpServletRequest req);

    AccountDto signInGoogle(SignInGoogleRequest request, HttpServletResponse response);
}
