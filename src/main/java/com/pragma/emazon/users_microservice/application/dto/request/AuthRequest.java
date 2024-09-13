package com.pragma.emazon.users_microservice.application.dto.request;

import com.pragma.emazon.users_microservice.application.constant.AuthSchemaDescriptions;
import com.pragma.emazon.users_microservice.domain.constant.AuthValidationMessages;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = AuthSchemaDescriptions.AUTH_REQUEST_DESCRIPTION)
public class AuthRequest {

    @Schema(description = AuthSchemaDescriptions.EMAIL_DESCRIPTION, example = AuthSchemaDescriptions.EMAIL_EXAMPLE)
    @NotBlank(message = AuthValidationMessages.INVALID_AUTH_EMAIL_NULL_EMPTY_BLANK)
    private String email;

    @Schema(description = AuthSchemaDescriptions.PASSWORD_DESCRIPTION, example = AuthSchemaDescriptions.PASSWORD_EXAMPLE)
    @NotBlank(message = AuthValidationMessages.INVALID_AUTH_PASSWORD_NULL_EMPTY_BLANK)
    private String password;
}
