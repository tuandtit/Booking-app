package com.cinema.booking_app.web.rest.impl;

import com.cinema.booking_app.service.AuthenticationService;
import com.cinema.booking_app.dto.AccountDto;
import com.cinema.booking_app.dto.request.SignInGoogleRequest;
import com.cinema.booking_app.dto.request.SignInRequest;
import com.cinema.booking_app.dto.request.SignUpRequest;
import com.cinema.booking_app.dto.response.Response;
import com.cinema.booking_app.web.rest.AuthenticationResource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationResourceImpl implements AuthenticationResource {

    private final AuthenticationService authenticationService;

    @Override
    public Response<AccountDto> signUp(final SignUpRequest request) {
        return Response.ok(authenticationService.signUp(request));
    }

    @Override
    public Response<AccountDto> signIn(final SignInRequest request,final HttpServletResponse response) {
        return Response.ok(authenticationService.signIn(request, response));
    }

    @Override
    public Response<AccountDto> getAccessTokenByRefreshToken(final HttpServletRequest request) {
        return Response.ok(authenticationService.accessTokenByRefreshToken(request));
    }

    @Override
    public Response<AccountDto> signInByGoogle(final SignInGoogleRequest request,final HttpServletResponse response) {
        return Response.ok(authenticationService.signInGoogle(request, response));
    }
}
