package de.acando;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Configuration
	@Order(1)
	public static class SimpleConfigurerAdapter extends WebSecurityConfigurerAdapter
			implements ApplicationContextAware {

		// @Bean
		// CorsFilter corsFilter() {
		// CorsFilter filter = new CorsFilter();
		// return filter;
		// }

		private static ApplicationContext ac;

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.authorizeRequests().antMatchers("/account/**").anonymous().and().formLogin().loginPage("/login")
					.defaultSuccessUrl("/home", true);
		}
	}
}
