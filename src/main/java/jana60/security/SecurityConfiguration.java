package jana60.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {
	
	//poi creo obj
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;

	}
	
	
	
	//do autorizzazioni
		@Bean
		SecurityFilterChain filterCahin(HttpSecurity http) throws Exception {
			//serve a dividere i ruoli di admin e di user
			http.authorizeRequests()
				.antMatchers("/admin").hasAuthority("ADMIN")
				.antMatchers("/user").hasAuthority("USER")
				.antMatchers("/").permitAll()
				.and().formLogin()
				.and().logout();
	
			return http.build();
		}
}

