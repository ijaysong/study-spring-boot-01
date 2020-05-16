package org.hdcd.common.security;

import org.hdcd.common.security.domain.CustomUser;
import org.hdcd.domain.Member;
import org.hdcd.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.warn("Load User By UserName : " + username);
			
		// userName은 사용자명이 아니라 사용자 아이디를 의미한다.
		Member member;
			try {
				member = memberMapper.read(username);
				
				logger.warn("queried by member mapper : " + member);
				
				return member == null ? null : new CustomUser(member);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
	}
	
	
}
