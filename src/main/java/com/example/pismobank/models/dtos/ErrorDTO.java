package com.example.pismobank.models.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String path;
    private LocalDateTime atTime;
    private int status;
    private String message;

}
