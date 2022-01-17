package com.example.pismobank.controllers;

import com.example.pismobank.errors.AccountNotFoundException;
import com.example.pismobank.models.dtos.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.Clock;
import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorsController {
    private final Clock clock;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ErrorDTO resourceNotFoundException(RuntimeException error, WebRequest request) {
        String errorMessage = error.getMessage() != null ? error.getMessage() : "Resource not found";
        return new ErrorDTO(
                request.getContextPath(),
                LocalDateTime.now(clock),
                HttpStatus.NOT_FOUND.value(),
                errorMessage
        );
    }


}
