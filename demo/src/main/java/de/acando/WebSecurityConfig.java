package de.acando;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/js/**", "/css/**", "/images/**", "/home/**", "/new-account/**", "/save-profile/**").permitAll()
				.antMatchers("/snippet/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/", true)
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/") ;
		}
}
