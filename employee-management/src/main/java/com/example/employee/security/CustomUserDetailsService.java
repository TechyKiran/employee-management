package com.example.employee.security;
import com.example.employee.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository repo;
 public CustomUserDetailsService(UserRepository repo){this.repo=repo;}
 public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
  var u=repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
  return User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().name()).build();
 }
}