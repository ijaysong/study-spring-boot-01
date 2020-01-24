package org.hdcd.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hdcd.domain.Address;
import org.hdcd.domain.Card;
import org.hdcd.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> printList() {
		logger.info("print JSON type value");
		
		List<Member> memberList = new ArrayList<>();
		Member member = new Member();
		member.setUserId("hongkd"); //유저ID
		member.setPassword("1234"); // 비밀번호
		member.setDateOfBirth(Date.valueOf(LocalDate.of(1995, 5, 20))); // 생년월일
		member.setCoin(0); // 코인
		
		Address address = new Address();
		address.setLocation("Seoul");
		address.setPostCode("010908");
		member.setAddress(address); // 주소
		
		Card card = new Card();
		card.setNo("23456");
		card.setValidMonth(Date.valueOf(LocalDate.of(2019, 9, 8)));
		
		Card card2 = new Card();
		card2.setNo("12342");
		card2.setValidMonth(Date.valueOf(LocalDate.of(2022, 05, 30)));
		
		List<Card> cardList = new ArrayList<>();
		cardList.add(card);
		cardList.add(card2);
		
		member.setCardList(cardList); // 카드 리스트
		
		memberList.add(member);
		
		ResponseEntity<List<Member>> entity = new ResponseEntity<>(memberList, HttpStatus.OK);
		
		return entity;
	}
}
