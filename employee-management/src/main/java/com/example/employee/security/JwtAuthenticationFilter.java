package com.example.employee.security;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 private final JwtService jwt; private final CustomUserDetailsService uds;
 public JwtAuthenticationFilter(JwtService j,CustomUserDetailsService u){jwt=j;uds=u;}
 protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String h=req.getHeader("Authorization");
  if(h!=null&&h.startsWith("Bearer ")){String token=h.substring(7);if(jwt.valid(token)){
   String username=jwt.extract(token);UserDetails ud=uds.loadUserByUsername(username);
   SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities()));
  }}
  chain.doFilter(req,res);
 }
}