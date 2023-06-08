package com.chat.ryubbanggu.vo;

import lombok.Data;

@Data
public class ChatMessage {
	
	private String chatId;
	private String userId;
	private String hostId;
	private String pictureUrl;
	private int state;
	private String value;

}
