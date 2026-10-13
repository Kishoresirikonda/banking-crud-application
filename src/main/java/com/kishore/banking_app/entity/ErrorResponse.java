package com.kishore.banking_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
