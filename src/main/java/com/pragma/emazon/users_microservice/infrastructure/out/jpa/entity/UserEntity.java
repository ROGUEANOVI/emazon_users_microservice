package com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity;

import com.pragma.emazon.users_microservice.domain.constant.UserValidationMessages;
import com.pragma.emazon.users_microservice.infrastructure.constant.UserTableFields;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = UserTableFields.TABLE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = UserTableFields.FIRST_NAME, nullable = false, length = UserValidationMessages.MAXIMUM_FIRST_NAME_SIZE)
    private String firstName;

    @Column(name = UserTableFields.LAST_NAME, nullable = false, length = UserValidationMessages.MAXIMUM_LAST_NAME_SIZE)
    private String lastName;

    @Column(name = UserTableFields.DOCUMENT_ID, nullable = false, unique = true,length = UserValidationMessages.MAXIMUM_DOCUMENT_ID_SIZE)
    private String documentId;

    @Column(name = UserTableFields.PHONE_NUMBER, nullable = false, length = UserValidationMessages.MAXIMUM_PHONE_NUMBER_SIZE)
    private String phoneNumber;

    @Column(name = UserTableFields.BIRTH_DATE, nullable = false)
    private LocalDate birthDate;

    @Column(name = UserTableFields.EMAIL, nullable = false, unique = true)
    private String email;

    @Column(name = UserTableFields.PASSWORD, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = UserTableFields.ROLE_ID, nullable = false)
    private RoleEntity role;
}
