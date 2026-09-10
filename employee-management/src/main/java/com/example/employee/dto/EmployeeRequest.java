package com.example.employee.dto;
import jakarta.validation.constraints.*;
public record EmployeeRequest(
 @NotBlank String name,
 @NotBlank @Email String email,
 @NotBlank String department,
 @NotNull @Positive Double salary) {}