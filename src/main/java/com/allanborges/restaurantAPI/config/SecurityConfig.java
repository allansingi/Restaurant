package com.allanborges.restaurantAPI.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.allanborges.restaurantAPI.security.JWTAuthenticationFilter;
import com.allanborges.restaurantAPI.security.JWTAuthorizationFilter;
import com.allanborges.restaurantAPI.security.JWTUtil;

/*
 * Security Configuration Class
 * Allows access for H2 database, starts JWT processes, creates password encoder and CORS configuration
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService detailsService;


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authConfiguration)
            throws Exception {

		http.headers().frameOptions().disable();
		
		http.cors().and().csrf().disable();
		
		http.addFilter(new JWTAuthenticationFilter(authConfiguration.getAuthenticationManager(), jwtUtil));
		http.addFilter(
				new JWTAuthorizationFilter(authConfiguration.getAuthenticationManager(), jwtUtil, detailsService));
		
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		
		return http.build();
	}

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}