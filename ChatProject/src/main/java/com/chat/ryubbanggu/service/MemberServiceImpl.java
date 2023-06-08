package com.chat.ryubbanggu.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chat.ryubbanggu.mapper.MemberMapper;
import com.chat.ryubbanggu.vo.Member;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	private MemberMapper mapper;

	@Override
	public void register(Member member) {
		mapper.register(member);
	}

	@Override
	public Member selectUser(String userId) {
		return mapper.selectUser(userId);
	}

}
