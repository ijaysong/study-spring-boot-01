package org.hdcd.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomNoOpPasswordEncoder.class);
	
	public String encode(CharSequence rawPassword) {
		logger.warn("before encode :" + rawPassword);
		
		return rawPassword.toString();
	}
	
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		logger.warn("matches : " + rawPassword + ":" + encodedPassword);
		
		return rawPassword.toString().equals(encodedPassword);
	}
}
