package com.rafaelhayashi.brasilprev.config.validation;

import java.util.List;

public class MessageErrorDto {

    private final String message;
    private final List<FieldErrorDto> fieldsErrors;

    public MessageErrorDto(String message, List<FieldErrorDto> fieldErrorDtoList) {
        this.message = message;
        this.fieldsErrors = fieldErrorDtoList;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldErrorDto> getFieldsErrors() {
        return fieldsErrors;
    }
}
