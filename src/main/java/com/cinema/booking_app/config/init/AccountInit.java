package com.cinema.booking_app.config.init;

import com.cinema.booking_app.common.enums.AccountStatus;
import com.cinema.booking_app.entity.AccountEntity;
import com.cinema.booking_app.entity.RoleEntity;
import com.cinema.booking_app.common.enums.ERole;
import com.cinema.booking_app.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountInit implements CommandLineRunner {
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (accountRepository.count() > 0) {
            return;
        }


        String adminUsername = "admin";
        String adminPassword = "admin";
        String displayName = "owner";
        final var account = AccountEntity.builder()
                .username(adminUsername)
                .passwordHash(this.passwordEncoder.encode(adminPassword))
                .displayName(displayName)
                .status(AccountStatus.ACTIVE)
                .roles(List.of(
                        RoleEntity.builder()
                                .name(ERole.ADMIN)
                                .build(),
                        RoleEntity.builder()
                                .name(ERole.USER)
                                .build()
                ))
                .build();
        this.accountRepository.save(account);
    }
}
