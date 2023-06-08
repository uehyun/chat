package com.chat.ryubbanggu.vo;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class ChatSession {
	private WebSocketSession session;
	private String id;

}
