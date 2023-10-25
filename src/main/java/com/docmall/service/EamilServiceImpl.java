package com.docmall.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.docmall.dto.EmailDTO;

import lombok.RequiredArgsConstructor;

// 현재는 mapper인터페이스를 참조 안함 
@RequiredArgsConstructor
@Service
public class EamilServiceImpl implements EmailService {

	//주입. email-config.xml파일의 bean으로 주입.
	private final JavaMailSender mailSender;

	@Override
	public void sendMail(EmailDTO dto, String message) {
		//메일 구성 정보를 담담하는 객체(받는 사람, 보내는 사람, 받는사람의 메일 주소, 본문내용)
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
		    // 받는 사람의 메일 주소
	         mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiverMail()));
	         // 보내는 사람(메일, 이름)
	         mimeMessage.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
	         // 메일 제목
	         mimeMessage.setSubject(dto.getSubject(), "utf-8");
	         // 본문 내용
	         mimeMessage.setText(message, "utf-8");
	         
	         mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	      
	}
}
