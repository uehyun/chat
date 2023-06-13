package com.chat.ryubbanggu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chat.ryubbanggu.mapper.ChatMapper;
import com.chat.ryubbanggu.vo.Chat;

@Service
public class ChatServiceImpl implements IChatService {
	
	@Inject
	private ChatMapper mapper;

	@Override
	public List<Chat> list(String userId) {
		return mapper.list(userId);
	}

	@Override
	public Chat chatRoom(String chatId) {
		return mapper.chatRoom(chatId);
	}

	@Override
	public int create(String userId, String hostId) {
		return mapper.create(userId, hostId);
	}

	@Override
	public String read(String userId) {
		return mapper.read(userId);
	}

}
