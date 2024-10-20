package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import security.CustomAuthenticatedProvider;
import security.SuccessHandler;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity , CustomAuthenticatedProvider customAuthenticatedProvider , SuccessHandler  successHandler) throws Exception {

		httpSecurity.csrf(csrf -> csrf.disable());

		httpSecurity.authorizeHttpRequests(http -> 
		http
				.requestMatchers("/index").authenticated()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/loginRequest").permitAll()
				.requestMatchers("/register/customer").permitAll()
				.requestMatchers("/pre/register/customer/").permitAll()
				.requestMatchers("/error").permitAll()
				.requestMatchers("/pre/deposit/account").authenticated()
				.requestMatchers("/register/new/deposit").authenticated()
				.requestMatchers("/pre/sake/account").authenticated()
				.requestMatchers("/register/new/sake").authenticated()
				.requestMatchers("/access/operations/list").authenticated()
				.anyRequest().authenticated()
				
				);
		
		httpSecurity.authenticationProvider(customAuthenticatedProvider);
		
		httpSecurity.formLogin(form -> form.
				
				loginPage("/loginRequest").permitAll()
				.loginProcessingUrl("/login").permitAll()
				.successHandler(successHandler)
				
				);
		
		
		httpSecurity.httpBasic(Customizer.withDefaults());
		

		return httpSecurity.build();
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
		
	}
	
	
	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		
		return new GrantedAuthorityDefaults("");
		
	}
	
	

}
