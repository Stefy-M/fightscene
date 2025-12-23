package com.fightscene.backend.domain.user.service;

import com.fightscene.backend.config.security.JwtService;
import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.domain.user.UserRole;
import com.fightscene.backend.domain.user.repository.UserRepository;
import com.fightscene.backend.exception.EmailAlreadyExistsException;
import com.fightscene.backend.exception.InvalidCredentialsException;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public User signup(String email, String rawPassword) {
		if (userRepository.existsByEmail(email)) {
			throw new EmailAlreadyExistsException();
		}
		String hashedPassword = passwordEncoder.encode(rawPassword);

		User user = User.builder().email(email).passwordHash(hashedPassword).role(UserRole.USER).build();

		return userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public String login(String email, String rawPassword) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, rawPassword));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toList();
		
		return jwtService.generateToken(email, roles);

//		User user = userRepository.findByEmail(email).orElseThrow(() -> new InvalidCredentialsException());
//
//		if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
//			throw new InvalidCredentialsException();
//		}
//
//		return user;
	}
	
	
}
