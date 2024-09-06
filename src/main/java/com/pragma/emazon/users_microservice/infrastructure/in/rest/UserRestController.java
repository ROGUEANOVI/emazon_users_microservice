package com.pragma.emazon.users_microservice.infrastructure.in.rest;

import com.pragma.emazon.users_microservice.application.dto.request.CreateUserRequest;
import com.pragma.emazon.users_microservice.application.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.pragma.emazon.users_microservice.infrastructure.constant.PreAuthorizeMessages.*;
import static com.pragma.emazon.users_microservice.infrastructure.constant.UserApiDocumentation.*;

@RestController
@RequestMapping(GLOBAL_ROUTE)
@Tag(name = TAG_NAME, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
@PreAuthorize(DENY_ALL)
public class UserRestController {

    private final UserHandler userHandler;

    @Operation(summary = SUMMARY_CREATE_WAREHOUSE_ASSISTANT_USER, description = DESCRIPTION_CREATE_WAREHOUSE_ASSISTANT_USER)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_201, description = DESCRIPTION_201, content = @Content),
        @ApiResponse(responseCode = CODE_409, description = DESCRIPTION_409, content = @Content),
        @ApiResponse(responseCode = CODE_400, description = DESCRIPTION_400, content = @Content)
    })
    @PostMapping(ROUTE_WAREHOUSE_ASSISTANTS)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Void> createWarehouseAssistantUser(@RequestBody CreateUserRequest createUserRequest) {

        userHandler.createWarehouseAssistantUser(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
