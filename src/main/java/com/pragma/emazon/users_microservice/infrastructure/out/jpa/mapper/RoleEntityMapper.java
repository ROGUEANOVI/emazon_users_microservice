package com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper;

import com.pragma.emazon.users_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.users_microservice.domain.model.Role;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = GlobalMessages.SPRING)
public interface RoleEntityMapper {

    Role toRole(RoleEntity roleEntity);
}
