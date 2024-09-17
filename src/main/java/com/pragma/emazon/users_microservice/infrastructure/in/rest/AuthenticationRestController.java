package com.pragma.emazon.users_microservice.infrastructure.in.rest;

import com.pragma.emazon.users_microservice.application.dto.request.AuthRequest;
import com.pragma.emazon.users_microservice.application.dto.response.TokenResponse;
import com.pragma.emazon.users_microservice.application.handler.IAuthenticationHandler;
import com.pragma.emazon.users_microservice.infrastructure.constant.OpenApiMessages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pragma.emazon.users_microservice.infrastructure.constant.AuthApiMessages.*;
import static com.pragma.emazon.users_microservice.infrastructure.constant.AuthApiMessages.TAG_DESCRIPTION;
import static com.pragma.emazon.users_microservice.infrastructure.constant.AuthApiMessages.TAG_NAME;
import static com.pragma.emazon.users_microservice.infrastructure.constant.PreAuthorizeMessages.PERMIT_ALL;

@RestController
@RequestMapping(ROUTE_AUTH)
@Tag(name = TAG_NAME, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
@PreAuthorize(PERMIT_ALL)
public class AuthenticationRestController {

    private final IAuthenticationHandler authHandler;

    @Operation(summary = SUMMARY_LOGIN, description = DESCRIPTION_LOGIN)
    @ApiResponses(value = {
        @ApiResponse(responseCode = OpenApiMessages.CODE_200, description = DESCRIPTION_200, content = @Content),
        @ApiResponse(responseCode = OpenApiMessages.CODE_400, description = DESCRIPTION_400, content = @Content),
        @ApiResponse(responseCode = OpenApiMessages.CODE_401, description = DESCRIPTION_401, content = @Content)
    })
    @PostMapping(ROUTE_LOGIN)
    public ResponseEntity<TokenResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {

        return ResponseEntity.ok(authHandler.authenticate(authRequest));
    }
}
