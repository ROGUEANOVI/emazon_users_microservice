package com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper;

import com.pragma.emazon.users_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.users_microservice.domain.model.User;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = GlobalMessages.SPRING)
public interface UserEntityMapper {

    UserEntity toUserEntity(User user);
}
