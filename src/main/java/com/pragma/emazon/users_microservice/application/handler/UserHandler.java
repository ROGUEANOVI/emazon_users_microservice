package com.pragma.emazon.users_microservice.application.handler;

import com.pragma.emazon.users_microservice.application.dto.request.CreateUserRequest;
import com.pragma.emazon.users_microservice.application.mapper.UserRequestMapper;
import com.pragma.emazon.users_microservice.domain.port.api.IUserServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;

    private final UserRequestMapper userRequestMapper;

    @Override
    public void createWarehouseAssistantUser(@Valid CreateUserRequest createUserRequest) {

        userServicePort.createWarehouseAssistantUser(userRequestMapper.toUser(createUserRequest));
    }
}
