package com.pragma.emazon.users_microservice.application.dto.request;

import com.pragma.emazon.users_microservice.domain.constant.AuthValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotBlank(message = AuthValidationMessages.INVALID_AUTH_EMAIL_NULL_EMPTY_BLANK)
    private String email;


    @NotBlank(message = AuthValidationMessages.INVALID_AUTH_PASSWORD_NULL_EMPTY_BLANK)
    private String password;
}
