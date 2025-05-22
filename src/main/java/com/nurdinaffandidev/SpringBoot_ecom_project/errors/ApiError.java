package com.nurdinaffandidev.SpringBoot_ecom_project.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    // Attributes
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
