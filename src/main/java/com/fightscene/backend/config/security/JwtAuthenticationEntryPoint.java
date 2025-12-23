package com.fightscene.backend.config.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		
		Map<String, Object> body = Map.of(
				"timestamp", Instant.now().toString(),
				"status", 401,
				"error","UNAUTHORIZED",
				"message", "Your session has expired or is invalid. Please log in again.",
				"path", request.getRequestURI()
				);
		
		objectMapper.writeValue(response.getOutputStream(), body);
	}

}
