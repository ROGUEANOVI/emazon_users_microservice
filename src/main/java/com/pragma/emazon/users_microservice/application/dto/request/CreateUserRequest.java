package com.pragma.emazon.users_microservice.application.dto.request;

import com.pragma.emazon.users_microservice.application.constant.UserSchemaDescriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import static com.pragma.emazon.users_microservice.domain.constant.UserValidationMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = UserSchemaDescriptions.CREATE_USER_REQUEST_DESCRIPTION)
public class CreateUserRequest {

    @Size(max = MAXIMUM_FIRST_NAME_SIZE, message = INVALID_FIRST_NAME_SIZE)
    @NotBlank(message = INVALID_FIRST_NAME_NULL_EMPTY_BLANK)
    @Schema(description = UserSchemaDescriptions.FIRST_NAME_DESCRIPTION, example = UserSchemaDescriptions.FIRST_NAME_EXAMPLE)
    private String firstName;

    @Schema(description = UserSchemaDescriptions.LAST_NAME_DESCRIPTION, example = UserSchemaDescriptions.LAST_NAME_EXAMPLE)
    @Size(max = MAXIMUM_LAST_NAME_SIZE, message = INVALID_LAST_NAME_SIZE)
    @NotBlank(message = INVALID_LAST_NAME_NULL_EMPTY_BLANK)
    private String lastName;

    @Schema(description = UserSchemaDescriptions.DOCUMENT_ID_DESCRIPTION, example = UserSchemaDescriptions.DOCUMENT_ID_EXAMPLE)
    @Size(min = MINIMUM_DOCUMENT_ID_SIZE, max = MAXIMUM_DOCUMENT_ID_SIZE, message = INVALID_DOCUMENT_ID_SIZE)
    @NotBlank(message = INVALID_DOCUMENT_ID_NULL_EMPTY_BLANK)
    @Pattern(regexp = REGEX_DOCUMENT_ID, message = INVALID_DOCUMENT_ID_PATTERN)
    private String documentId;

    @Schema(description = UserSchemaDescriptions.PHONE_NUMBER_DESCRIPTION, example = UserSchemaDescriptions.PHONE_NUMBER_EXAMPLE)
    @Size(min = MINIMUM_PHONE_NUMBER_SIZE, max = MAXIMUM_PHONE_NUMBER_SIZE, message = INVALID_PHONE_NUMBER_SIZE)
    @NotBlank(message = INVALID_PHONE_NUMBER_NULL_EMPTY_BLANK)
    @Pattern(regexp = REGEX_PHONE_NUMBER, message = INVALID_PHONE_NUMBER_PATTERN)
    private String phoneNumber;

    @Schema(description = UserSchemaDescriptions.BIRTH_DATE_DESCRIPTION, example = UserSchemaDescriptions.BIRTH_DATE_EXAMPLE)
    @NotBlank(message = INVALID_BIRTH_DATE_NULL_EMPTY_BLANK)
    @Pattern( regexp = REGEX_BIRTH_DATE, message = INVALID_BIRTH_DATE_FORMAT)
    private String birthDate;

    @Schema(description = UserSchemaDescriptions.EMAIL_DESCRIPTION, example = UserSchemaDescriptions.EMAIL_EXAMPLE)
    @NotBlank(message = INVALID_EMAIL_NULL_EMPTY_BLANK)
    @Email(message = INVALID_EMAIL_FORMAT)
    @Pattern(regexp = REGEX_EMAIL, message = INVALID_EMAIL_FORMAT)
    private String email;

    @Schema(description = UserSchemaDescriptions.PASSWORD_DESCRIPTION, example = UserSchemaDescriptions.PASSWORD_EXAMPLE)
    @Size(min = MINIMUM_PASSWORD_SIZE, max = MAXIMUM_PASSWORD_SIZE, message = INVALID_PASSWORD_SIZE)
    @NotBlank(message = INVALID_PASSWORD_NULL_EMPTY_BLANK)
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_PASSWORD_FORMAT)
    private String password;
}
