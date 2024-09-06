package com.pragma.emazon.users_microservice.domain.port.api;

import com.pragma.emazon.users_microservice.domain.model.User;

public interface IUserServicePort {

    void createWarehouseAssistantUser(User user);
}
