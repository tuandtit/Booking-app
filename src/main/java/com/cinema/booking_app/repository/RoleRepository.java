package com.cinema.booking_app.repository;

import com.cinema.booking_app.entity.RoleEntity;
import com.cinema.booking_app.common.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole eRole);
}