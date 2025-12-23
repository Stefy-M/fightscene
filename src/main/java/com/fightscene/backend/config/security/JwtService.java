package com.fightscene.backend.config.security;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private final Key signingKey;
	private final long expirationMs;

	public JwtService(@Value("${security.jwt.secret}") String secret,
			@Value("${security.jwt.expiration-ms}") long expirationMs) {

		this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
		this.expirationMs = expirationMs;
	}

	public String generateToken(String email, List<String> roles) {

		return Jwts.builder()
				.setSubject(email)
				.claim("roles", roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(signingKey, SignatureAlgorithm.HS256).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public String extractEmail(String token) {
		return parseClaims(token).getSubject();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> extractRoles(String token){
		return parseClaims(token).get("roles", List.class);
	}
	
	

	private Claims parseClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
	}
}
