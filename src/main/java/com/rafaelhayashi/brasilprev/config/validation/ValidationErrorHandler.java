package com.rafaelhayashi.brasilprev.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
public class ValidationErrorHandler {


    private final MessageSource messageSource;

    @Autowired
    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Stream<FieldErrorDto> fieldErrorDtoStream = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            String message = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String reference = (fieldError.getRejectedValue() != null) ? fieldError.getRejectedValue().toString() : null;
            return new FieldErrorDto(fieldError.getField(), reference, message);
        });
        String message = messageSource
                .getMessage("MethodArgumentNotValidException.message", null, "Validation error", LocaleContextHolder.getLocale());
        return new MessageErrorDto(message, fieldErrorDtoStream.collect(Collectors.toList()));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public MessageErrorDto handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        String message = messageSource
                .getMessage("HttpMessageNotReadableException.message", null, "Required request body is missing", LocaleContextHolder.getLocale());
        return new MessageErrorDto(message, new ArrayList<>());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public MessageErrorDto handleInvalidArgumentException(IllegalArgumentException ex) {
        return new MessageErrorDto(ex.getLocalizedMessage(), new ArrayList<>());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public MessageErrorDto handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return new MessageErrorDto(ex.getLocalizedMessage(), new ArrayList<>());
    }
}
