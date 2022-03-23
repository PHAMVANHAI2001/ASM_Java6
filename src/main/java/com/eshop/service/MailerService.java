package com.eshop.service;

import com.eshop.entities.MailInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface MailerService {
	/**
	 * Gửi email
	 * @param mail thông tin email
	 * @throws MessagingException lỗi gửi email
	 */
	void send(MailInfo mail) throws MessagingException;
	/**
	 * Gửi email đơn giản
	 * @param to email người nhận
	 * @param subject tiêu đề email
	 * @param body nội dung email
	 * @throws MessagingException lỗi gửi email
	 */
	void send(String to, String subject, String body) throws MessagingException;
	
	@SpringBootApplication
	@EnableScheduling
	public class J5DemoApplication {
		public static void main(String[] args) {
			SpringApplication.run(J5DemoApplication.class, args);
		}
	}

	/**
	 * Xếp mail vào hàng đợi
	 * @param mail thông tin email
	 */	
	void queue(MailInfo mail);
	/**
	 * Tạo MailInfo và xếp vào hàng đợi
	 * @param to email người nhận
	 * @param subject tiêu đề email
	 * @param body nội dung email
	 */	
	void queue(String to, String subject, String body);

}
