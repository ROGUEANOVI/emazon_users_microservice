package com.pragma.emazon.users_microservice.application.handler;

import com.pragma.emazon.users_microservice.application.dto.request.CreateUserRequest;
import jakarta.validation.Valid;

public interface IUserHandler {

    void createWarehouseAssistantUser(@Valid CreateUserRequest createUserRequest);

    void createCustomerUser(@Valid CreateUserRequest createUserRequest);
}
