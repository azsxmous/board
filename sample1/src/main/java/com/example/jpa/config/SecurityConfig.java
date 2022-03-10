package com.example.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// ������̼� �߰�
@Configuration
@EnableWebSecurity
// HTTP Security ��ü ��� ����
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// ��� ��ο� ���� �׼����� ����ϰڴ�.
		http.authorizeRequests().anyRequest().permitAll();
	}
}
