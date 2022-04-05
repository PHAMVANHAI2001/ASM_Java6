package com.eshop.entities;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo implements Serializable {
	private String from;
	private String to;
	private String[] cc;
	private String[] bcc;
	private String subject;
	private String body;
	private MultipartFile[] attachments;

	public MailInfo(String to, String subject, String body) {
		this.from = "Cửa hàng điện thoại hải apple <HaiApple@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
