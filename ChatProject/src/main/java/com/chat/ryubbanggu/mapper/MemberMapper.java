package com.chat.ryubbanggu.mapper;

import com.chat.ryubbanggu.vo.Member;

public interface MemberMapper {

	public void register(Member member);
	public Member selectUser(String userId);

}
