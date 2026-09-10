package com.example.employee.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Service
public class JwtService {
 private final SecretKey key; private final long expiration;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration}") long exp){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));expiration=exp;}
 public String generate(String username){return Jwts.builder().subject(username).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+expiration)).signWith(key).compact();}
 public String extract(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
 public boolean valid(String token){try{Jwts.parser().verifyWith(key).build().parseSignedClaims(token);return true;}catch(Exception e){return false;}}
}