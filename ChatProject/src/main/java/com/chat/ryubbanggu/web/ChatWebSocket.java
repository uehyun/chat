package com.chat.ryubbanggu.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.chat.ryubbanggu.service.IChatService;
import com.chat.ryubbanggu.service.IMemberService;
import com.chat.ryubbanggu.vo.Chat;
import com.chat.ryubbanggu.vo.ChatMessage;
import com.chat.ryubbanggu.vo.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatWebSocket extends TextWebSocketHandler {
	
    private List<WebSocketSession> users = new LinkedList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Gson gson = new Gson();
    @Inject
    private IChatService chatService;
    
    @Inject
    private IMemberService memberService;
    
    // 메세지 보내기
    private void sendMessage(String id, String message, String pictureUrl) {
        String sendMessage = "<div class='message-bubble' data-userId='"+ id +"'>"
        					+ 	"<div class='message-avatar'>" + "<img src='../resources/upload/"+pictureUrl+ "'/></div>"
        					+ 	"<div class='message-text'><p>" + message + "</p></div>"
        					+ "</div>";
        for (WebSocketSession user : users) {
            try {
                user.sendMessage(new TextMessage(sendMessage));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    
    
    // 채팅내역 파일에 저장
    private void saveFile(String id,String message, String pictureUrl, String chatId) {
    	String filePath = "C:\\CHAT\\" + chatId + ".txt";
    	String msg = "<div class='message-bubble' data-userId='"+ id +"'>"
					+ 	"<div class='message-avatar'>" + "<img src='../resources/upload/"+pictureUrl+ "'/></div>"
					+ 	"<div class='message-text'><p>" + message + "</p></div>"
					+ "</div>";
    	log.info(chatId);
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath, true))) {
            outputStream.write(msg.getBytes("UTF-8"));
            outputStream.flush(); // 버퍼를 비워주기 위해 flush() 호출
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    // 채팅내역 읽어오기
    private String readFile(String chatId) throws IOException {
    	File file = new File(chatId);
    	
    	if(!file.exists()) {
    		file.createNewFile();
            return "";
    	}
    	
    	try(InputStream stream = new FileInputStream(file)) {
    		byte[] buffer = new byte[(int) file.length()];
    		stream.read(buffer);
    		return new String(buffer);
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
    	return "";
    }
    
    
    // 클라이언트와 연결 이후에 실행되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	users.add(session);
    }
   
    // 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	String msg = message.getPayload();
    	
    	ChatMessage chatMessage = objectMapper.readValue(msg, ChatMessage.class);
    	
    	Chat chat = new Chat();
    	chat.setChatId(chatMessage.getChatId());
    	
    	Member member = memberService.selectUser(chatMessage.getUserId());
    	
    	if(chatMessage.getChatId().equals("") || chatMessage.getChatId() == null) {
    		chat = chatService.create(chatMessage.getUserId(), chatMessage.getHostId());
    	}
    	
    	String chatRoom = "C:\\CHAT\\" + chat.getChatId() + ".txt";
    	
    	if(chatMessage.getState() == 0) {
    		try {
				session.sendMessage(new TextMessage(readFile(chatRoom)));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(chatMessage.getState() == 1) {
    		if(session != null) {
    			String msgval = chatMessage.getValue();
    			sendMessage(chatMessage.getUserId(), msgval, member.getPictureUrl());
    			saveFile(chatMessage.getUserId(), msgval, member.getPictureUrl(), chat.getChatId());
    		}
    	}
    }
   
    // 클라이언트와 연결을 끊었을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	users.remove(session);
    	log.info("----------------->socket 종료");
    }

}
