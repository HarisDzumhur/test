package com.example.app.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsServiceImp userDetailsServiceImp;
	private final JwtAuthenticationFilter jwtauthenticationFilter;
	public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp, JwtAuthenticationFilter jwtauthenticationFilter) {
		this.userDetailsServiceImp = userDetailsServiceImp;
		this.jwtauthenticationFilter = jwtauthenticationFilter;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(AbstractHttpConfigurer::disable).
				authorizeHttpRequests(req->req.requestMatchers("/login/**","/register/**","/admin/**","/group/all",
						"/client/block","/client/unblock","/client/delete","/client/all","/tag/**","/reply/all","/reply/reported","/reply/delete",
						"/client/exists","/client/codeforgotten","/client/codeverify","/client/validateforgotten","/client/validateverify",
						"/client/update/newpassword","/marker/type/**").
						permitAll().anyRequest().authenticated()
				).userDetailsService(userDetailsServiceImp).
				sessionManagement(session->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterBefore(jwtauthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
	{
		try {
		return configuration.getAuthenticationManager();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
