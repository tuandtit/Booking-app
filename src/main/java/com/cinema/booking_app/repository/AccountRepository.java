package com.cinema.booking_app.repository;

import com.cinema.booking_app.entity.AccountEntity;
import com.cinema.booking_app.entity.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<AccountEntity> findByUsernameAndAuthProvider(String username, AuthProvider authProvider);
}