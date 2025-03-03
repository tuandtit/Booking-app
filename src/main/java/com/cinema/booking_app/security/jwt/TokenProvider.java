package com.cinema.booking_app.security.jwt;

import com.cinema.booking_app.config.properties.RsaKeyProperties;
import com.cinema.booking_app.security.SecurityUtils;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final RsaKeyProperties rsaKeyProperties;

    private static final String AUTHORITIES_KEY = "scope";
    private static final String INVALID_JWT_TOKEN = "Invalid JWT Token";

    public String createToken(Authentication authentication) {
        log.info("[TokenProvider:createToken] Token Creation Started for:{}", authentication.getName());

        final var authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        final var now = Instant.now();

        final var claims = JwtClaimsSet.builder()
                .issuer("davIssuer")
                .issuedAt(now)
                .expiresAt(now.plus(rsaKeyProperties.expired(), ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String resolveToken(final HttpServletRequest request) {
        final var bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && SecurityUtils.isBearerToken(bearerToken)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(final String token) {
        try {
            JWTParser.parse(token);
            return getIfTokenIsExpired(jwtDecoder.decode(token));
        } catch (ParseException e) {
            log.error(INVALID_JWT_TOKEN, e);
            return false;
        }
    }

    private boolean getIfTokenIsExpired(Jwt jwt) {
        return Objects.requireNonNull(jwt.getExpiresAt()).isBefore(Instant.now());
    }

    public Authentication getAuthentication(final String token) {
        try {
            final var claims = JWTParser.parse(token).getJWTClaimsSet();

            Collection<? extends GrantedAuthority> authorities = Arrays
                    .stream(claims.getClaim(AUTHORITIES_KEY).toString().split(","))
                    .filter(auth -> !auth.trim().isEmpty())
                    .map("ROLE_"::concat)
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            final var principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, token, authorities);
        } catch (ParseException e) {
            log.error(INVALID_JWT_TOKEN, e);
            throw new RuntimeException(INVALID_JWT_TOKEN);
        }
    }
}
