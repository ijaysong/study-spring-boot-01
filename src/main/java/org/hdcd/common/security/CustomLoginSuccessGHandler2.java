package org.hdcd.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

// 인증 전에 접근을 시도한 URL로 리다이렉트 하는 기능을 구현한다
public class CustomLoginSuccessGHandler2 implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomLoginSuccessGHandler2.class);

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
		logger.warn("Login Success");
		
		User customUser = (User)auth.getPrincipal();
		
		logger.info("username = " + customUser.getUsername());
		logger.info("password = " + customUser.getPassword());
		
		clearAuthenticationAttribute(request);
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = savedRequest.getRedirectUrl();
		
		logger.warn("Login Success targetUrl = " + targetUrl);
		
		response.sendRedirect(targetUrl);
	}

	private void clearAuthenticationAttribute(HttpServletRequest request ) {
		HttpSession session = request.getSession(false);
		
		if(session == null) return;
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
