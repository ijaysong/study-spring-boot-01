package org.hdcd.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hdcd.common.exception.ApiErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		//logger.info("handle");
		
		//response.sendRedirect("/accessError");
		
		ApiErrorInfo apiErrorInfo = new ApiErrorInfo();
		apiErrorInfo.setMessage("Access denied");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonString = mapper.writeValueAsString(apiErrorInfo);
		
		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(403);
		res.getWriter().write(jsonString);
	}

}
