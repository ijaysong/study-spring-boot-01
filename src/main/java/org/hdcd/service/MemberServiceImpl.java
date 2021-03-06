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

	// 	수정
	@Override
	public void modify(Member member) throws Exception {
		mapper.updateMember(member);
		
		int userNo = member.getUserNo();
		
		mapper.deleteAuth(userNo);
		
		List<MemberAuth> authList = member.getAuthList();
		
		for(int i = 0; i < authList.size(); i++) {
			MemberAuth memberAuth = authList.get(i);
			String auth = memberAuth.getAuth();
			
			if(auth == null) {
				continue;
			}
			
			if(auth.trim().length() == 0) {
				continue;
			}
			
			memberAuth.setUserNo(userNo);
			
			mapper.createAuth(memberAuth);
		}
	}

	// 삭제
	@Override
	public void remove(int userNo) throws Exception {
		mapper.deleteAuth(userNo);
		mapper.deleteMember(userNo);
	}
	
}
