package com.project.shopapp.configurations.init;

import com.project.shopapp.common.enums.ERole;
import com.project.shopapp.models.Account;
import com.project.shopapp.models.Role;
import com.project.shopapp.repositories.AccountRepository;
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
        final var account = Account.builder()
                .username(adminUsername)
                .passwordHash(this.passwordEncoder.encode(adminPassword))
                .roles(List.of(
                        Role.builder()
                                .name(ERole.ADMIN)
                                .build(),
                        Role.builder()
                                .name(ERole.USER)
                                .build()
                ))
                .build();
        this.accountRepository.save(account);
    }
}
