package com.example.employee.config;
import com.example.employee.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
public class SecurityConfig {
 private final JwtAuthenticationFilter jwtFilter;
 public SecurityConfig(JwtAuthenticationFilter f){jwtFilter=f;}
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
  http.csrf(c->c.disable()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(a->a.requestMatchers("/api/auth/**").permitAll()
   .requestMatchers(org.springframework.http.HttpMethod.GET,"/api/employees/**").hasAnyRole("USER","ADMIN")
   .requestMatchers("/api/employees/**").hasRole("ADMIN").anyRequest().authenticated())
   .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }
}