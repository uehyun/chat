package com.chat.ryubbanggu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chat.ryubbanggu.vo.Chat;

public interface ChatMapper {

	public List<Chat> list(String userId);
	public Chat chatRoom(String chatId);
	public String read(String userId);
	public int create(@Param("userId") String userId,@Param("hostId") String hostId);

}
