package org.hdcd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hdcd.common.security.jwt.constants.SecurityConstants;
import org.hdcd.domain.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
public class JwtLoginController {

	private static final Logger logger = LoggerFactory.getLogger(JwtLoginController.class);
	
	// 암호화
	@RequestMapping(value="/jwt/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) {
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		
		logger.info("username = " + username + ", password = " + password);
		
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_MEMBER");
		
		byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
		
		String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), 
				SignatureAlgorithm.HS512).setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
				.setIssuer(SecurityConstants.TOKEN_ISSUER).setAudience(SecurityConstants.TOKEN_AUDIENCE)
				.setSubject(username).setExpiration(new Date(System.currentTimeMillis() + 864000000))
				.claim("rol", roles).compact();
		
		logger.info("token : " + token);
		
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	
	// 복호화
	@RequestMapping("/jwt/read")
	public ResponseEntity<String> read(@RequestHeader(name="Authorization") String header) {
		logger.info("read : header" + header);
		
		String token = header.substring(7);
		
		logger.info("read : token " + token);
		
		byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
		
		Jws<Claims> parsedToken = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
		
		logger.info("parsedToken : " + parsedToken);
		
		String username = parsedToken.getBody().getSubject();
		
		logger.info("username : " + username);
		
		List<String> roles = (List<String>) parsedToken.getBody().get("rol");
	
		logger.info("roles : " + roles);
		
		return new ResponseEntity<String>(parsedToken.toString(), HttpStatus.OK);
	}
}
