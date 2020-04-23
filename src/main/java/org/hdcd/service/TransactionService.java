package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Member;

public interface TransactionService {

	public void register(Member member) throws Exception;
	public Member read(int userNo) throws Exception;
	public void modify(Member member) throws Exception;
	public List<Member> list() throws Exception;
	public void remove(int userNo) throws Exception;
}
