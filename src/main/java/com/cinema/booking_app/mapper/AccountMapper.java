package com.cinema.booking_app.mapper;

import com.cinema.booking_app.entity.AccountEntity;
import com.cinema.booking_app.common.enums.TokenType;
import com.cinema.booking_app.dto.AccountDto;
import com.cinema.booking_app.dto.request.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.jwt.Jwt;

@Mapper(
        config = DefaultConfigMapper.class,
        imports = {TokenType.class}
)
public interface AccountMapper extends EntityMapper<AccountDto, AccountEntity> {
    @Mapping(target = "avatar", ignore = true)
    AccountEntity toEntity(SignUpRequest request);

    @Mapping(target = "username", source = "token.subject")
    @Mapping(target = "tokenType", expression = "java(TokenType.Bearer.name())")
    @Mapping(target = "token", source = "token.tokenValue")
    @Mapping(target = "refreshToken", source = "refreshToken.tokenValue")
    @Mapping(target = "tokenExpiry", source = "token.expiresAt")
    @Mapping(target = "refreshTokenExpiry", source = "refreshToken.expiresAt")
    AccountDto toDto(Jwt token, Jwt refreshToken);

    @Mapping(target = "username", source = "token.subject")
    @Mapping(target = "tokenType", expression = "java(TokenType.Bearer.name())")
    @Mapping(target = "token", source = "token.tokenValue")
    @Mapping(target = "tokenExpiry", source = "token.expiresAt")
    AccountDto toDto(Jwt token);
}
