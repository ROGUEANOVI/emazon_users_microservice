package com.pragma.emazon.users_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.users_microservice.domain.model.Role;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RoleJpaAdapterTest {

    private IRoleRepository roleRepository;
    private RoleEntityMapper roleEntityMapper;
    private RoleJpaAdapter roleJpaAdapter;

    @BeforeEach
    void setUp() {
        roleRepository = mock(IRoleRepository.class);
        roleEntityMapper = mock(RoleEntityMapper.class);
        roleJpaAdapter = new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Test
    void findRoleByNameShouldReturnRoleWhenFound() {
        // Arrange
        String roleName = "ROLE_WAREHOUSE_ASSISTANT";

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName(roleName);

        Role role = new Role();
        role.setId(1L);
        role.setName(roleName);

        when(roleRepository.findByName(roleName)).thenReturn(Optional.of(roleEntity));
        when(roleEntityMapper.toRole(roleEntity)).thenReturn(role);

        // Act
        Optional<Role> result = roleJpaAdapter.findRoleByName(roleName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(role, result.get());
        verify(roleRepository, times(1)).findByName(roleName);
        verify(roleEntityMapper, times(1)).toRole(roleEntity);
    }

    @Test
    void findRoleByNameShouldReturnEmptyWhenNotFound() {
        // Arrange
        String roleName = "ROLE_DEVELOPER";

        when(roleRepository.findByName(roleName)).thenReturn(Optional.empty());

        // Act
        Optional<Role> result = roleJpaAdapter.findRoleByName(roleName);

        // Assert
        assertTrue(result.isEmpty());
        verify(roleRepository, times(1)).findByName(roleName);
        verify(roleEntityMapper, never()).toRole(any(RoleEntity.class));
    }
}
