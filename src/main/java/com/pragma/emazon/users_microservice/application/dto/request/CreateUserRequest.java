package com.pragma.emazon.users_microservice.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import static com.pragma.emazon.users_microservice.domain.constant.UserValidationMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @Size(max = MAXIMUM_FIRST_NAME_SIZE, message = INVALID_FIRST_NAME_SIZE)
    @NotBlank(message = INVALID_FIRST_NAME_NULL_EMPTY_BLANK)
    private String firstName;

    @Size(max = MAXIMUM_LAST_NAME_SIZE, message = INVALID_LAST_NAME_SIZE)
    @NotBlank(message = INVALID_LAST_NAME_NULL_EMPTY_BLANK)
    private String lastName;

    @Size(min = MINIMUM_DOCUMENT_ID_SIZE, max = MAXIMUM_DOCUMENT_ID_SIZE, message = INVALID_DOCUMENT_ID_SIZE)
    @NotBlank(message = INVALID_DOCUMENT_ID_NULL_EMPTY_BLANK)
    @Pattern(regexp = REGEX_DOCUMENT_ID, message = INVALID_DOCUMENT_ID_PATTERN)
    private String documentId;

    @Size(min = MINIMUM_PHONE_NUMBER_SIZE, max = MAXIMUM_PHONE_NUMBER_SIZE, message = INVALID_PHONE_NUMBER_SIZE)
    @NotBlank(message = INVALID_PHONE_NUMBER_NULL_EMPTY_BLANK)
    @Pattern(regexp = REGEX_PHONE_NUMBER, message = INVALID_PHONE_NUMBER_PATTERN)
    private String phoneNumber;

    @NotBlank(message = INVALID_BIRTH_DATE_NULL_EMPTY_BLANK)
    @Pattern( regexp = REGEX_BIRTH_DATE, message = INVALID_BIRTH_DATE_FORMAT)
    private String birthDate;

    @NotBlank(message = INVALID_EMAIL_NULL_EMPTY_BLANK)
    @Email(message = INVALID_EMAIL_FORMAT)
    @Pattern(regexp = REGEX_EMAIL, message = INVALID_EMAIL_FORMAT)
    private String email;

    @Size(min = MINIMUM_PASSWORD_SIZE, max = MAXIMUM_PASSWORD_SIZE, message = INVALID_PASSWORD_SIZE)
    @NotBlank(message = INVALID_PASSWORD_NULL_EMPTY_BLANK)
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_PASSWORD_FORMAT)
    private String password;
}
