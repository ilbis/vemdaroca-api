package com.vemdaroca.vemdarocaapi.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMailWithFile(String toEmail, String subject, String message, String pathFile) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(toEmail);
			helper.setText(message, false);
			helper.setSubject(subject);
			File attach = new File(pathFile);
			helper.addAttachment("Relatorio.xlsx", attach);

			javaMailSender.send(mimeMessage);
			System.out.println("Envio com Sucesso!");
		} catch (MailException e) {
			System.out.println("Email não pode ser eviado!\n" + e.getMessage());
		}
	}
	
	public void sendMail(String toEmail, String subject, String message) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(toEmail);
			helper.setText(message, false);
			helper.setSubject(subject);

			javaMailSender.send(mimeMessage);
			System.out.println("Envio com Sucesso!");
		} catch (MailException e) {
			System.out.println("Email não pode ser eviado!\n" + e.getMessage());
		}
	}
}
