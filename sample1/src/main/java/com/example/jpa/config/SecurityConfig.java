package com.example.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 어노테이션 추가
@Configuration
@EnableWebSecurity
// HTTP Security 객체 사용 가능
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 모든 경로에 대해 액세스를 허용하겠다.
		http.authorizeRequests().anyRequest().permitAll();
	}
}
