package com.chat.ryubbanggu.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Member {
	private int userNo;
	private String userId;
	private String userName;
	private MultipartFile picture;
	private String pictureUrl;
}
