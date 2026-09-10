package com.example.employee.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(EmployeeNotFoundException.class)
 ResponseEntity<?> notFound(EmployeeNotFoundException e){return ResponseEntity.status(404).body(Map.of("timestamp",Instant.now(),"status",404,"error","Not Found","message",e.getMessage()));}
 @ExceptionHandler(EmailAlreadyExistsException.class)
 ResponseEntity<?> conflict(EmailAlreadyExistsException e){return ResponseEntity.status(409).body(Map.of("timestamp",Instant.now(),"status",409,"error","Conflict","message",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class)
 ResponseEntity<?> validation(MethodArgumentNotValidException e){return ResponseEntity.badRequest().body(Map.of("status",400,"error","Validation Failed","message",e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).toList()));}
}