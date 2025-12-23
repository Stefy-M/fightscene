package com.fightscene.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fightscene.backend.config.security.JwtAccessDenied;
import com.fightscene.backend.config.security.JwtAuthenticationEntryPoint;
import com.fightscene.backend.config.security.JwtAuthenticationFilter;
import com.fightscene.backend.domain.user.UserRole;
import com.fightscene.backend.domain.user.service.AuthService;
import com.fightscene.backend.domain.user.service.CustomUserDetailsService;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final UserDetailsService userDetailsService;
	private final JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	private final JwtAccessDenied jwtAccessDenied;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())

				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				
				.exceptionHandling(ex -> ex
						.authenticationEntryPoint(jwtAuthEntryPoint)
						.accessDeniedHandler(jwtAccessDenied))

				.authorizeHttpRequests(
						auth -> auth
						.requestMatchers("/api/auth/**", "/search/**", "/health", "/actuator/**").permitAll()
						.anyRequest().authenticated()
						
								)

				.authenticationProvider(authenticationProvider())

				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12); // strength 10–14 is normal
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

	/*
	 * Authentication manager bean Required for programmatic authentication (e.g.,
	 * in /generateToken)
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
