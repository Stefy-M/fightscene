package com.fightscene.backend.config.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAccessDenied implements AccessDeniedHandler {
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json");
		
		Map<String, Object> body = Map.of(
				"timestamp", Instant.now().toString(),
				"status", 403,
				"error", "FORBIDDEN",
				"message", "You do not have permission to access this resource.",
				"path", request.getRequestURI());
		
		objectMapper.writeValue(response.getOutputStream(), body);
	}

}
