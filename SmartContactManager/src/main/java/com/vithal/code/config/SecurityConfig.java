package com.vithal.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public UserDetailsService getUserdetailsService(){
		
		return new CustomeUserDetailsService();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserdetailsService());
		daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}

	
	
	 //filer chain
	 @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
             http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
             .requestMatchers("/user/**").hasRole("USER")
             .requestMatchers("/**").permitAll()
             .and().formLogin()
             .and().csrf().disable();
             return http.build();
     }
	  
	  
	  
}
