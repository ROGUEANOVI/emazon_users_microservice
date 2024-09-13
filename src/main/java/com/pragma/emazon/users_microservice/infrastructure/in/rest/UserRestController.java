package com.pragma.emazon.users_microservice.infrastructure.in.rest;

import com.pragma.emazon.users_microservice.application.dto.request.CreateUserRequest;
import com.pragma.emazon.users_microservice.application.handler.IUserHandler;
import com.pragma.emazon.users_microservice.infrastructure.constant.OpenApiMessages;
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
import static com.pragma.emazon.users_microservice.infrastructure.constant.UserApiMessages.*;

@RestController
@RequestMapping(ROUTE_USERS)
@Tag(name = TAG_NAME, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
@PreAuthorize(DENY_ALL)
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = SUMMARY_CREATE_WAREHOUSE_ASSISTANT_USER, description = DESCRIPTION_CREATE_WAREHOUSE_ASSISTANT_USER)
    @ApiResponses(value = {
        @ApiResponse(responseCode = OpenApiMessages.CODE_201, description = DESCRIPTION_201, content = @Content),
        @ApiResponse(responseCode = OpenApiMessages.CODE_400, description = DESCRIPTION_400, content = @Content),
        @ApiResponse(responseCode = OpenApiMessages.CODE_409, description = DESCRIPTION_409, content = @Content),
    })
    @PostMapping(ROUTE_WAREHOUSE_ASSISTANTS)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Void> createWarehouseAssistantUser(@RequestBody CreateUserRequest createUserRequest) {

        userHandler.createWarehouseAssistantUser(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = SUMMARY_CREATE_CUSTOMER_USER, description = DESCRIPTION_CREATE_CUSTOMER_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiMessages.CODE_201, description = DESCRIPTION_201, content = @Content),
            @ApiResponse(responseCode = OpenApiMessages.CODE_400, description = DESCRIPTION_400, content = @Content),
            @ApiResponse(responseCode = OpenApiMessages.CODE_409, description = DESCRIPTION_409, content = @Content),
    })
    @PostMapping(ROUTE_CUSTOMERS)
    @PreAuthorize(PERMIT_ALL)
    public ResponseEntity<Void> createCustomerUser(@RequestBody CreateUserRequest createUserRequest) {

        userHandler.createCustomerUser(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
