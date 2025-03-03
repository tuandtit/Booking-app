package com.cinema.booking_app.web.rest.impl;

import com.cinema.booking_app.service.AuthenticationService;
import com.cinema.booking_app.service.dto.AccountDto;
import com.cinema.booking_app.service.dto.request.SignInRequest;
import com.cinema.booking_app.service.dto.request.SignUpRequest;
import com.cinema.booking_app.service.dto.response.Response;
import com.cinema.booking_app.web.rest.AuthenticationResource;
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
    public Response<AccountDto> signUp(SignUpRequest request) {
        return Response.ok(authenticationService.signUp(request));
    }

    @Override
    public Response<AccountDto> signIn(SignInRequest request,final HttpServletResponse response) {
        return Response.ok(authenticationService.signIn(request, response));
    }
}
