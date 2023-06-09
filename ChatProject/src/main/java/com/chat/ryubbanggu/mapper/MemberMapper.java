package com.chat.ryubbanggu.mapper;

import java.util.List;

import com.chat.ryubbanggu.vo.Member;

public interface MemberMapper {

	public void register(Member member);
	public Member selectUser(String userId);
	public List<Member> list();

}
