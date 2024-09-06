package com.pragma.emazon.users_microservice.domain.constant;

public class UserValidationMessages {

    public static final int MAXIMUM_FIRST_NAME_SIZE = 100;

    public static final int MAXIMUM_LAST_NAME_SIZE = 100;

    public static final int MAXIMUM_PHONE_NUMBER_SIZE = 13;

    public static final int MAXIMUM_DOCUMENT_ID_SIZE = 20;

    public static final int MAXIMUM_PASSWORD_SIZE = 128;

    public static final int MINIMUM_PHONE_NUMBER_SIZE = 7;

    public static final int MINIMUM_DOCUMENT_ID_SIZE = 9;

    public static final int MINIMUM_PASSWORD_SIZE = 8;

    public static final int MINIMUM_AGE_FOR_ADULT = 18;

    public static final String INVALID_FIRST_NAME_NULL_EMPTY_BLANK = "First name cannot be null, empty or blank characters";

    public static final String INVALID_FIRST_NAME_SIZE = "First name size cannot be more than 100 characters";

    public static final String INVALID_LAST_NAME_NULL_EMPTY_BLANK = "Last name cannot be null, empty or blank characters";

    public static final String INVALID_LAST_NAME_SIZE = "Last name size cannot be more than 100 characters";

    public static final String INVALID_DOCUMENT_ID_NULL_EMPTY_BLANK = "Document id cannot be null, empty or blank characters";

    public static final String INVALID_DOCUMENT_ID_SIZE = "The document id between 9 and 20 characters long";

    public static final String INVALID_DOCUMENT_ID_PATTERN = "The document id must be numeric";

    public static final String INVALID_PHONE_NUMBER_NULL_EMPTY_BLANK = "Phone number cannot be null, empty or blank characters";

    public static final String INVALID_PHONE_NUMBER_SIZE = "The phone number must be between 7 and 13 characters long";

    public static final String INVALID_PHONE_NUMBER_PATTERN = "The phone number must be numeric";

    public static final String INVALID_BIRTH_DATE_NULL_EMPTY_BLANK = "Birth date cannot be null, empty or blank characters";

    public static final String INVALID_BIRTH_DATE_FORMAT = "The birth date must have a valid date with the format yyyy-MM-dd";

    public static final String INVALID_EMAIL_NULL_EMPTY_BLANK = "Email cannot be null, empty or blank characters";

    public static final String INVALID_EMAIL_FORMAT = "The email address is not valid";

    public static final String INVALID_PASSWORD_NULL_EMPTY_BLANK = "Password cannot be null, empty or blank characters";

    public static final String INVALID_PASSWORD_SIZE = "The password must be between 8 and 128 characters long";

    public static final String INVALID_PASSWORD_FORMAT = "The password must not contain whitespaces";

    public static final String REGEX_DOCUMENT_ID =  "\\d+";

    public static final String REGEX_PHONE_NUMBER = "^\\+?\\d+$";

    public static final String REGEX_BIRTH_DATE = "^((?:19|20)\\d{2})-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2\\d|3[01])$";

    public static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static final String REGEX_PASSWORD = "^\\S+$";

    public static final String BIRTH_DATE_FORMATTING = "yyyy-MM-dd";

    private UserValidationMessages() {}
}
