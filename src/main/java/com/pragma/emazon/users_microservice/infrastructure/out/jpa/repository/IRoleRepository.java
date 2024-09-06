package com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository;

import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
}
