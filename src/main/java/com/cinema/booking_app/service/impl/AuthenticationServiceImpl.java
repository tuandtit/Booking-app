package com.cinema.booking_app.service.impl;

import com.cinema.booking_app.repository.AccountRepository;
import com.cinema.booking_app.security.jwt.TokenProvider;
import com.cinema.booking_app.service.AuthenticationService;
import com.cinema.booking_app.service.dto.AccountDto;
import com.cinema.booking_app.service.dto.request.SignInRequest;
import com.cinema.booking_app.service.dto.request.SignUpRequest;
import com.cinema.booking_app.service.mapper.AccountMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    //Other
    PasswordEncoder passwordEncoder;
    AuthenticationManagerBuilder authenticationManagerBuilder;
    TokenProvider tokenProvider;

    //Repository
    AccountRepository accountRepository;

    //Mapper
    AccountMapper accountMapper;

    @Override
    public AccountDto signUp(SignUpRequest request) {
        final var entity = accountMapper.toEntity(request);
        entity.setPasswordHash(passwordEncoder.encode(request.password()));
        return accountMapper.toDto(accountRepository.save(entity));
    }

    @Override
    public AccountDto signIn(SignInRequest request, HttpServletResponse response) {
        final var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );

        final var authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final var dto = new AccountDto();
        dto.setUsername(request.username());
        dto.setToken(tokenProvider.createToken(authentication));
        return null;
    }
}
