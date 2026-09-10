package com.example.employee.service;
import com.example.employee.dto.*;
import com.example.employee.entity.*;
import com.example.employee.repository.UserRepository;
import com.example.employee.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder; private final AuthenticationManager auth; private final JwtService jwt;
 public AuthService(UserRepository u,PasswordEncoder e,AuthenticationManager a,JwtService j){users=u;encoder=e;auth=a;jwt=j;}
 public void register(RegisterRequest r){
  if(users.existsByUsername(r.username())) throw new IllegalArgumentException("Username already exists");
  AppUser u=new AppUser();u.setUsername(r.username());u.setPassword(encoder.encode(r.password()));u.setRole(Role.USER);users.save(u);
 }
 public LoginResponse login(LoginRequest r){auth.authenticate(new UsernamePasswordAuthenticationToken(r.username(),r.password()));return new LoginResponse(jwt.generate(r.username()));}
}