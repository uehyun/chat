package com.chat.ryubbanggu.service;

import java.util.List;

import com.chat.ryubbanggu.vo.Chat;

public interface IChatService {

	public List<Chat> list(String userId);
	public Chat chatRoom(String chatId);
	public String read(String userId);
	public int create(String userId, String hostId);

}
