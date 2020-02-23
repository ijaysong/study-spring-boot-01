package org.hdcd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;

@Mapper
public interface MemberMapper {
	public void createMember(Member member) throws Exception;
	public void createAuth(MemberAuth memberAuth) throws Exception;
}
