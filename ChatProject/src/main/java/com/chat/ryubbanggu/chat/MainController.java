package com.chat.ryubbanggu.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	  @RequestMapping(value = "/")
	  public String goMain() {
		  return "chat/test";
	  }

}
