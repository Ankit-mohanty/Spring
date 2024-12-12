package com.edigest.journalApp.config;


import com.edigest.journalApp.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SpringSecurity {
	// Add custom security configurations here
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Bean
	public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
		return http.authorizeHttpRequests(request -> request
				           .requestMatchers("/public/**").permitAll()
				           .requestMatchers("/journal/**").authenticated()
				           .anyRequest().authenticated())
		           .httpBasic(Customizer.withDefaults())
		           .csrf(AbstractHttpConfigurer :: disable)
		           .build();
	}

	@Autowired
	public void configureGoal( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}