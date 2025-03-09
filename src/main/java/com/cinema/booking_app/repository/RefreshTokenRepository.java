package com.cinema.booking_app.repository;

import com.cinema.booking_app.entity.AccountEntity;
import com.cinema.booking_app.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    List<RefreshTokenEntity> findByAccountAndRevoked(AccountEntity account, boolean revoked);
}