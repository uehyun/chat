package com.chat.ryubbanggu.chat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@RequestMapping(value =  "/chatMessage", method = RequestMethod.GET)
	public String chatMessage() {
		return "chat/ChatTemplateDetail";
	}

}
