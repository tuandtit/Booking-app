package com.cinema.booking_app.entity;

import com.cinema.booking_app.common.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
public class RoleEntity extends AbstractAuditingEntity<Long> implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20, nullable = false)
    ERole name = ERole.USER;
}
