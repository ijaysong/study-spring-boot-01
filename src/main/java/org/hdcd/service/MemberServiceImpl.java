package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;
import org.hdcd.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper mapper;

	// 등록
	@Transactional
	@Override
	public void register(Member member) throws Exception {
		mapper.createMember(member);
		
		MemberAuth memberAuth = new MemberAuth();
		
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_USER");
		
		mapper.createAuth(memberAuth);
	}

	// 목록조회
	@Override
	public List<Member> list() throws Exception {
		return mapper.listMember();
	}

	// 상세 조회
	@Override
	public Member read(int userNo) throws Exception {
		return mapper.readMember(userNo);
	}
	
}
