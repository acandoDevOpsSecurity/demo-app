package de.secdevops;

import javax.inject.Inject;

import org.owasp.appsensor.integration.springsecurity.context.AppSensorSecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.secdevops.user.AppUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Inject 
	private SecurityContextRepository securityContextRepository;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/js/**","/css/**", "/images/**", "/ext/**", "/home/**", "/new-account/**", "/save-new-user/**").permitAll()
				.antMatchers("/snippet/**", "edit-account/**", "/upload/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			.and()
            .formLogin()
                .loginPage("/login")                
                .permitAll()
                .defaultSuccessUrl("/", true)
                .and()
            .securityContext()
            	.securityContextRepository(securityContextRepository)
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/")
            	.invalidateHttpSession(true)
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authenticationProvider());
    	auth.userDetailsService(userDetailsService);
    }
    
    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new AppSensorSecurityContextRepository();
    }
    
    @Autowired
    private AppUserDetailsService userDetailsService;
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
          = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
     
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
    
}