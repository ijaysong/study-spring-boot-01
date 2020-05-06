package org.hdcd.config;

import org.hdcd.common.security.CustomAccessDeniedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

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
		
		// 접근 거부가 발생한 상황을 처리하는 접근 거부 처리자의 URI를 지정한다
		//http.exceptionHandling().accessDeniedPage("/accessError");
				
		// 폼 기반 인증 기능을 사용한다
		// 사용자가 정의한 로그인 페이지의 URI를 지정한다
		http.formLogin().loginPage("/login");
		
		// 등록한 CustomAccessDeniedHandler를 접근 거부 처리자로 지정한다
		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다
		// 스프링 시큐리티 5버전부터는 패스워드 암호화 처리기를 반드시 이용하도록 변경이 되었다
		// 암호화 처리기를 사용하지 않도록 "{noop}" 문자열을 비밀번호 앞에 사용한다
		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
	}
	
	// CustomAccessDeniedHandler를 빈으로 등록한다
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
}
