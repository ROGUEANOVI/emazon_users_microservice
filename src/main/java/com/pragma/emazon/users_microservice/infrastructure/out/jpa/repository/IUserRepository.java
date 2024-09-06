package com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository;

import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByDocumentId(String documentId);

    Optional<UserEntity> findByEmail(String documentId);
}
