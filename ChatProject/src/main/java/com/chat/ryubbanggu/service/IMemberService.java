package com.chat.ryubbanggu.service;

import com.chat.ryubbanggu.vo.Member;

public interface IMemberService {

	public void register(Member member);
	public Member selectUser(String userId);

}
