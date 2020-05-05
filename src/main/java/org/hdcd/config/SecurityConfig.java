package org.hdcd.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 스프링 시큐리티 설정
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("security config");
		
		// URL 패턴으로 접근 제한을 설정한다
		http.authorizeRequests().antMatchers("/security/board/list").permitAll();
		http.authorizeRequests().antMatchers("/security/board/register").hasRole("MEMBER");
		http.authorizeRequests().antMatchers("/security/notice/list").permitAll();
		http.authorizeRequests().antMatchers("/security/notice/register").hasRole("ADMIN");
		
		// 폼 기반 인증 기능을 사용한다
		http.formLogin();
	}
}