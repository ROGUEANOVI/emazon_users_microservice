package com.pragma.emazon.users_microservice.application.mapper;

import com.pragma.emazon.users_microservice.application.dto.request.AuthRequest;
import com.pragma.emazon.users_microservice.application.dto.response.TokenResponse;
import com.pragma.emazon.users_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = GlobalMessages.SPRING)
public interface AuthRequestMapper {

    Auth toAuth(AuthRequest authRequest);

    TokenResponse toTokenResponse(AuthToken authToken);
}
