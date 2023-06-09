package com.chat.ryubbanggu.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chat.ryubbanggu.service.IChatService;
import com.chat.ryubbanggu.vo.Chat;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {
	
	@Inject
	private IChatService chatService;

	@RequestMapping(value = "/chatList", method = RequestMethod.GET)
	public String chatList() {
		return "chat/ChatTemplate";
	}
	
	@RequestMapping(value = "/chatMessage", method = RequestMethod.GET)
	public String chatDetail(String userId, Model model) {
		log.info("chatDetail() 실행");
		List<Chat> chatList = chatService.list(userId);
		model.addAttribute("chatList", chatList);
		
		return "chat/ChatTemplateDetail";
	}
	
}
