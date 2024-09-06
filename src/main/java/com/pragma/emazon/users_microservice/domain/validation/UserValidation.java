package com.pragma.emazon.users_microservice.domain.validation;

import com.pragma.emazon.users_microservice.domain.constant.UserExceptionMessages;
import com.pragma.emazon.users_microservice.domain.constant.UserValidationMessages;
import com.pragma.emazon.users_microservice.domain.exception.InvalidUserBirthDateException;
import com.pragma.emazon.users_microservice.domain.exception.UserAlreadyExistsException;
import com.pragma.emazon.users_microservice.domain.exception.UserIsNotAdultException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserValidation {

    private UserValidation() {}

    public static void validateBirthDate(String birthDate) {

        if (!birthDate.matches(UserValidationMessages.REGEX_BIRTH_DATE)){
            throw new InvalidUserBirthDateException(UserExceptionMessages.INVALID_USER_BIRTH_DATE_FORMAT, birthDate);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UserValidationMessages.BIRTH_DATE_FORMATTING);
            LocalDate birthDateValidated = LocalDate.parse(birthDate, formatter);

            String parsedDateString = birthDateValidated.format(formatter);
            if (!birthDate.equals(parsedDateString)) {
                throw new InvalidUserBirthDateException(UserExceptionMessages.INVALID_USER_BIRTH_DATE_FORMAT, birthDate);
            }

            if (birthDateValidated.isAfter(LocalDate.now())) {
                throw new InvalidUserBirthDateException(UserExceptionMessages.INVALID_USER_BIRTH_DATE_IS_IN_THE_FUTURE, birthDate);
            }

            if (Period.between(birthDateValidated, LocalDate.now()).getYears() < UserValidationMessages.MINIMUM_AGE_FOR_ADULT) {
                throw new UserIsNotAdultException(UserExceptionMessages.USER_IS_NOT_ADULT, birthDate);
            }
        }
        catch (DateTimeParseException e) {
            throw new InvalidUserBirthDateException(UserExceptionMessages.INVALID_USER_BIRTH_DATE_FORMAT, birthDate);
        }
    }

    public static void validateDocumentIdAlreadyExists(Boolean existsDocumentId, String documentId) {

        if (Boolean.TRUE.equals(existsDocumentId)) {
            throw new UserAlreadyExistsException(UserExceptionMessages.USER_WITH_DOCUMENT_ID_ALREADY_EXISTS, documentId);
        }
    }

    public static void validateEmailAlreadyExists(Boolean existsEmail, String email) {

        if (Boolean.TRUE.equals(existsEmail)){
            throw new UserAlreadyExistsException(UserExceptionMessages.USER_WITH_EMAIL_ALREADY_EXISTS, email);
        }
    }
}
