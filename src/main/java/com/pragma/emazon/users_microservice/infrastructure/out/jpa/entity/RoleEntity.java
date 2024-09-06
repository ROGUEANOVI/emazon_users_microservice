package com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity;

import com.pragma.emazon.users_microservice.infrastructure.constant.RoleTableFields;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = RoleTableFields.TABLE_NAME)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = RoleTableFields.NAME, nullable = false, unique = true, length = RoleTableFields.MAXIMUM_ROLE_NAME_SIZE)
    private String name;
}
