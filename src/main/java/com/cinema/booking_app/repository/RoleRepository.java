package com.cinema.booking_app.repository;

import com.cinema.booking_app.entity.RoleEntity;
import com.cinema.booking_app.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(ERole eRole);
}