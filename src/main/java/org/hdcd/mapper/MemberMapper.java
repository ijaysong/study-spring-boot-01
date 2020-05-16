package org.hdcd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;

@Mapper
public interface MemberMapper {
	public void createMember(Member member) throws Exception;
	public Member readMember(int userNo) throws Exception;
	public void updateMember(Member member) throws Exception;
	public void deleteMember(int userNo) throws Exception;
	public List<Member> listMember() throws Exception;
	public void createAuth(MemberAuth memberAuth) throws Exception;
	public void deleteAuth(int userNo) throws Exception;
	public Member read(String userId) throws Exception;
}
