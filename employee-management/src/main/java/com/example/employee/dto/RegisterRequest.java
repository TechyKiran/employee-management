package com.example.employee.dto;
import jakarta.validation.constraints.*;
public record RegisterRequest(@NotBlank String username,@NotBlank @Size(min=6) String password) {}