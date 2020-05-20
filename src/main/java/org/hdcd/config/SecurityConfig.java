package org.hdcd.config;

import javax.sql.DataSource;

import org.hdcd.common.security.CustomAccessDeniedHandler;
import org.hdcd.common.security.CustomLoginSuccessHandler;
import org.hdcd.common.security.CustomNoOpPasswordEncoder;
import org.hdcd.common.security.CustomUserDetailsService;
import org.hdcd.common.security.jwt.filter.JwtAuthenticationFilter;
import org.hdcd.common.security.jwt.filter.JwtAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// 스프링 시큐리티 설정
@EnableWebSecurity
// 시큐리티 애너테이션 활성화를 위한 설정
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("security config");
		
		// URL 패턴으로 접근 제한을 설정한다
		// http.authorizeRequests().antMatchers("/security/board/list").permitAll();
		// http.authorizeRequests().antMatchers("/security/board/register").hasRole("MEMBER");
		// http.authorizeRequests().antMatchers("/security/notice/list").permitAll();
		// http.authorizeRequests().antMatchers("/security/notice/register").hasRole("ADMIN");
		
		// 접근 거부가 발생한 상황을 처리하는 접근 거부 처리자의 URI를 지정한다
		//http.exceptionHandling().accessDeniedPage("/accessError");
				
		// 폼 기반 인증 기능을 사용한다
		// 사용자가 정의한 로그인 페이지의 URI를 지정한다
		// 로그인 성공 후 처리를 담당하는 처리자로 지정한다
		// http.formLogin().loginPage("/login").successHandler(createAuthenticationSuccessHandler());
		
		// 로그아웃 처리를 위한 URI를 지정하고, 로그아웃한 후에 세션을 무효화 한다
		// http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
		// 등록한 CustomAccessDeniedHandler를 접근 거부 처리자로 지정한다
		// http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());
		
		//CORS 설정
		http.cors()
		.and()
		// CSRF 방지 지원 기능 비활성화
		.csrf().disable()
		// JWT 인증 필터 보안 컨텍스트에 추가
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))
		// JWT 인가 필터 보안 컨첵스트에 추가
		.addFilter(new JwtAuthorizationFilter(authenticationManager()))
		// 세션 관리 비활성화
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다
		// 스프링 시큐리티 5버전부터는 패스워드 암호화 처리기를 반드시 이용하도록 변경이 되었다
		// 암호화 처리기를 사용하지 않도록 "{noop}" 문자열을 비밀번호 앞에 사용한다
		// auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
		// auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
		
		// JDBC 인증 제공자
		//auth.jdbcAuthentication()
		// 데이터 소스를 저장
		//.dataSource(dataSource)
		// 사용자가 정의한 비밀번호 암호화 처리기를 지정한다.
		//.passwordEncoder(createPasswordEncoder());
		
		// 스프링 시큐리티가 원하는 결과를 반환하는 쿼리를 작성한다
		/*String query1 = "SELECT "
				+ "user_id, user_pw, enabled"
				+ "FROM dev_db.member"
				+ "WHERE"
				+ "user_id = ?";
		
		String query2 = "SELECT"
				+ "b.user_id,"
				+ "a.auth"
				+ "FROM member_auth a,"
				+ "member b"
				+ "WEHRE"
				+ "a.user_no = b.user_no"
				+ "AND"
				+ "b.user_id = ?";*/
		
		// 작서한 쿼리를 지정한다
		//auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1).authoritiesByUsernameQuery(query2)
		// BCryptPasswordEncoder 비밀번호 암호화 처리기를 지정한다
		//.passwordEncoder(createPasswordEncoder2());
		
		// CustomUserDetailsService 빈을 인증 제공자에 지정한다
		auth.userDetailsService(createUserDetailsService()).passwordEncoder(createPasswordEncoder());
	}
	
	// CustomAccessDeniedHandler를 빈으로 등록한다
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	//CustomLoginSuccessHandler를 빈으로 등록한다
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	// 사용자가 정의한 비밀번호 암호화 처리기를 빈으로 등록한다
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new CustomNoOpPasswordEncoder();
	}
	
	// 스프링 시큐리티에서 제공되는 BCryptPasswordEncoder 클래스를 빈으로 등록한다
	@Bean
	public PasswordEncoder createPasswordEncoder2() {
		return new BCryptPasswordEncoder();
	}
	
	// 스프링 시큐리티의 UserDetailService를 구현한 클래스를 빈으로 등록한다
	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}

	// CORS 설정
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		
		return source;
	}
}
