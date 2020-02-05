package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class DemoApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.authorizeRequests(a -> a
						.antMatchers("/sheets").authenticated()
						.anyRequest().permitAll()
				)
//				.exceptionHandling(e -> e.defaultAuthenticationEntryPointFor(
//						loginUrlauthenticationEntryPoint(),
//						new AntPathRequestMatcher("/sheets"))
//				)
				.oauth2Login();
		// @formatter:on
	}

	@Bean
	public AuthenticationEntryPoint loginUrlauthenticationEntryPoint()
	{
		return new LoginUrlAuthenticationEntryPoint("/login/oauth2/code/google");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
