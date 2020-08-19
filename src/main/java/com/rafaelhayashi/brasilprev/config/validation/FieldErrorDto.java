package com.rafaelhayashi.brasilprev.config.validation;

public class FieldErrorDto {

    private final String field;
    private final String reference;
    private final String error;

    public FieldErrorDto(String field, String reference, String error) {
        this.field = field;
        this.reference = reference;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getReference() {
        return reference;
    }

    public String getError() {
        return error;
    }
}
