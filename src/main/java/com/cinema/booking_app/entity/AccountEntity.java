package com.cinema.booking_app.entity;

import com.cinema.booking_app.entity.enums.AuthProvider;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(
        name = "accounts",
        indexes = {
                @Index(
                        columnList = "username",
                        unique = true
                )
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity extends AbstractAuditingEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", unique = true, nullable = false, updatable = false)
    String username;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @Column(name = "avatar", columnDefinition = "text")
    String avatar;

    @Column(name = "auth_provider")
    @Enumerated(EnumType.STRING)
    AuthProvider authProvider = AuthProvider.EMAIL_AND_PASSWORD;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<RoleEntity> roles = new ArrayList<>();

    public void addRole(RoleEntity role) {
        if (ObjectUtils.isEmpty(roles)) {
            this.roles = new ArrayList<>();
        }
        roles.add(role);
    }
}