package com.blogboulder.BlogBoulderApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("SameParameterValue")
@Service
public class MailService {

	private static JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		MailService.javaMailSender = javaMailSender;
	}

	static void send(String to, String sub, String msg) {
		//compose message
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		try {
			mail.setFrom(String.valueOf(new InternetAddress("abhi30996@gmail.com", "Blog-Boulder")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mail.getFrom();
		mail.setSubject(sub);
		mail.setText(msg);

		javaMailSender.send(mail);
	}
}
