package com.hexaware.ccozyhaven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.hexaware.ccozyhaven.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	JwtAuthFilter authFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsersInfoUserDetailsService();
	}

	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

		return http.cors().and().csrf().disable().authorizeHttpRequests()
				.requestMatchers("/api/user/login", "/api/user/register", "/api/hotelowner/login",
						"/api/hotelowner/register","/api/admin/login", "/api/hotel/getall",
						"/api/hotel/available-rooms/{hotelId}", "/api/hotel/get/{hotelId}",
						"/api/hotel/get/location/{location}", "/api/hotel/get-by-name/{hotelName}",
						"/api/review/getall", "api/review/getall-by-hotel-id/{hotelId}", "/api/room/search",
						"/api/room/availability/{roomId}", "/api/room/calculate-fare-room/{roomId}","api/password/update","/api/reservation/reservation-fare","/swagger-ui/", "/v3/api-docs/**")
				.permitAll().and().authorizeHttpRequests().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();

	}
	
	@Bean
	 public CorsFilter corsFilter() {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("http://localhost:4200");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
      
      return new CorsFilter(source);
  }

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}