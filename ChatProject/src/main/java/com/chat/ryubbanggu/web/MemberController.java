package com.chat.ryubbanggu.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.chat.ryubbanggu.service.IChatService;
import com.chat.ryubbanggu.service.IMemberService;
import com.chat.ryubbanggu.vo.Chat;
import com.chat.ryubbanggu.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	@Resource(name="uploadPath")
	private String resourcesPath;
	
	@Inject
	private IMemberService memberService;
	
	@Inject
	private IChatService chatService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		log.info("main() 실행");
		return "chat/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			HttpServletRequest request,
			String userId, 
			Model model
			) {
		String goPage = "";
		Member member = memberService.selectUser(userId);
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			
			List<Chat> chatList = chatService.list(userId);
			model.addAttribute("chatList", chatList);
			goPage = "chat/ChatTemplate";
		} else {
			model.addAttribute("msg", "존재하지 않는 회원 입니다.");
			goPage = "chat/success";
		}
		return goPage;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm() {
		return "chat/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Member member, Model model) throws IOException {
		MultipartFile file = member.getPicture();
		
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		member.setPictureUrl(createdFileName);
		memberService.register(member);
		model.addAttribute("msg", "회원가입이 완료되었습니다.");
		return "chat/success";
	}
	
	private String uploadFile(String originalName, byte[] fileData) throws IOException {
		UUID uuid = UUID.randomUUID();
		String createdFileName = uuid.toString() + "_" + originalName;
		
		File file = new File(resourcesPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		File target = new File(resourcesPath, createdFileName);
		FileCopyUtils.copy(fileData, target);
		return createdFileName;
	}
	
}
