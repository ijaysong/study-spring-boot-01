package org.hdcd.common.security.jwt.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hdcd.common.security.jwt.constants.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

// JWT 인가 필터 정의
// 모든 HTTP 요청을 처리하면서 토큰이 만기되지 않았거나 서명 키가 올바른 토큰이 있는 Authorization 헤더가 있는지 확인한다
// 토큰이 유효하면 필터는 인증 데이터를 Spring의 보안 컨텍스트에 추가한다
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) { 
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = getAuthentication(request);
		String header = request.getHeader(SecurityConstants.TOKEN_HEADER);
		
		if(StringUtils.isEmpty(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
		
		if(!StringUtils.isEmpty(token)) {
			try {
				byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
				Jws<Claims> parsedToken = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.replace("Bearer", ""));
				
				String username = parsedToken.getBody().getSubject();
				
				List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody().get("rol"))
					.stream().map(authority -> new SimpleGrantedAuthority((String)authority)).collect(Collectors.toList());
				
				if(!StringUtils.isEmpty(username)) {
					return new UsernamePasswordAuthenticationToken(username, null, authorities);
				}
 			} catch(ExpiredJwtException ex) {
				logger.warn("Request to parse expired JWT : {} failed : {}", token, ex.getMessage());
			} catch(UnsupportedJwtException ex) {
				logger.warn("Request to parse unsupported JWT : {} failed : {}", token, ex.getMessage());
			} catch(MalformedJwtException ex) {
				logger.warn("Request to parse invalid JWT : {} failed : {}", token, ex.getMessage());
			} catch(SignatureException ex) {
				logger.warn("Request to parse JWT with invalid signature JWT : {} failed : {}", token, ex.getMessage());
			} catch(IllegalArgumentException ex) {
				logger.warn("Request to parse empty or null JWT : {} failed : {}", token, ex.getMessage());
			}
		}
		return null;
	}
	
}
