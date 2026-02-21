package com.cfs.Security_P02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/","/about", "/contactUs").permitAll()
				.requestMatchers("/balance", "/admin", "/transfer").authenticated()
		).formLogin(Customizer.withDefaults());
		return http.build();
	}

}
