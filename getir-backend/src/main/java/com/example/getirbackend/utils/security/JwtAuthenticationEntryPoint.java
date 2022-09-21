package com.example.getirbackend.utils.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.getirbackend.exceptions.AuthError;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ObjectMapper objectMapper = new ObjectMapper();
		if (authException.getMessage().equals("Full authentication is required to access this resource")) {
			response.getOutputStream().println(objectMapper.writeValueAsString(new AuthError(HttpStatus.FORBIDDEN,
					"example.com/probs/unauthorized", "Unauthorized", authException.getMessage())));

		} else if( authException.getMessage().equals("Bad credentials")) {
			response.getOutputStream().println(objectMapper.writeValueAsString(new AuthError(HttpStatus.UNAUTHORIZED,
					"example.com/probs/unauthorized", "You must login", authException.getMessage())));	
		}
		
		else {
			response.getOutputStream().println(objectMapper.writeValueAsString(new AuthError(HttpStatus.UNAUTHORIZED,
					"example.com/probs/auth", "Authorization exception", authException.getMessage())));
		}

	}

}
