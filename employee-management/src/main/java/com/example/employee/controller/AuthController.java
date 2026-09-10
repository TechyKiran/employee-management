package com.example.employee.controller;
import com.example.employee.dto.*;
import com.example.employee.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService service;
 public AuthController(AuthService s){service=s;}
 @PostMapping("/register") ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r){service.register(r);return ResponseEntity.status(HttpStatus.CREATED).body("User registered");}
 @PostMapping("/login") LoginResponse login(@RequestBody LoginRequest r){return service.login(r);}
}