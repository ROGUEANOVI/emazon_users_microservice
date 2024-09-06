package com.pragma.emazon.users_microservice.application.mapper;

import com.pragma.emazon.users_microservice.application.dto.request.CreateUserRequest;
import com.pragma.emazon.users_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.users_microservice.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = GlobalMessages.SPRING)
public interface UserRequestMapper {

    User toUser(CreateUserRequest createUserRequest);
}
