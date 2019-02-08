package de.secdevops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// instead of @SpringBootApplication, using 3 separate annotations 
// so exclusions for scanning can be controlled - need to ignore  
// appsensor's NoopUserManager so it doesn't get loaded

// Appsensor's SpringSecurityEventListener class has a bug... so fixed it within own impl.

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"de.secdevops", "org.owasp.appsensor"}, excludeFilters={
		@ComponentScan.Filter(type=FilterType.REGEX, pattern="org.owasp.appsensor.core.response.NoopUserManager"),
		@ComponentScan.Filter(type=FilterType.REGEX, pattern="org.owasp.appsensor.local.response.LocalResponseHandler"),
		@ComponentScan.Filter(type=FilterType.REGEX, pattern="org.owasp.appsensor.integration.springsecurity.event.SpringSecurityEventListener")})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
//		int i = 0;
//		while (i < 10) {
//			String password = "password";
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String hashedPassword = passwordEncoder.encode(password);
//
//			System.out.println(hashedPassword);
//			i++;
//		}
	}
	
}
