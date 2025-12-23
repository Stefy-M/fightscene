package com.fightscene.backend.domain.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fightscene.backend.config.security.JwtService;
import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.domain.user.UserRole;
import com.fightscene.backend.domain.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Mock
	private JwtService jwtService;

	@InjectMocks
	private AuthService authService;

	@Test
	void shouldCreateUserWhenSignUpPassed() {
		// -----Arrange------
		UUID USER_ID = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
		String rawPassword = "1234567";
		String encoded = passwordEncoder.encode(rawPassword);
		String email = "testmail@gmail.com";

		
		// Email does not exist -> success path
		when(userRepository.existsByEmail("testmail@gmail.com")).thenReturn(false);
		
		when(passwordEncoder.encode(anyString()))
        .thenReturn("encoded-password");

		// When save is called return a User (fake DB behavior)
		when(userRepository.save(any(User.class)))
				.thenReturn(new User(USER_ID, email, "encoded-password", UserRole.USER, Instant.now(), Instant.now()));

		// -----Act------
		User result = authService.signup(email, rawPassword);

		// -----Assert------
		assertNotNull(result);
		assertEquals(email, result.getEmail());
		
		//Behavior verification (THIS is key part)
		verify(userRepository).existsByEmail(email);
		verify(userRepository, times(1)).save(any(User.class));
	}
	
	  @Test
	    void shouldReturnTokenWhenLoggingIn() {
	        // -------- Arrange --------
	        String email = "testmail@gmail.com";
	        String rawPassword = "1234567";
	        String token = "mock-jwt-token";

	        Authentication authentication = mock(Authentication.class);
	        UserDetails userDetails = mock(UserDetails.class);

	        // 1️ Authentication succeeds
	        when(authenticationManager.authenticate(any(Authentication.class)))
	                .thenReturn(authentication);

	        // 2️ Principal is UserDetails
	        when(authentication.getPrincipal())
	                .thenReturn(userDetails);

	        // 3️ Authorities → roles
	        doReturn(List.of(new SimpleGrantedAuthority("ROLE_USER")))
	        .when(userDetails)
	        .getAuthorities();
	        
	        // 4️ JWT generation
	        when(jwtService.generateToken(eq(email), eq(List.of("ROLE_USER"))))
	                .thenReturn(token);

	        // -------- Act --------
	        String result = authService.login(email, rawPassword);

	        // -------- Assert --------
	        assertEquals(token, result);

	        verify(authenticationManager).authenticate(any(Authentication.class));
	        verify(jwtService).generateToken(email, List.of("ROLE_USER"));
	    }

}
