package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Member;

public interface MemberService {
	public void register(Member member) throws Exception;
	public List<Member> list() throws Exception;
	public Member read(int userNo) throws Exception;
}
