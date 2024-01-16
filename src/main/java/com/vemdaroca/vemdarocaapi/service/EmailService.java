package com.vemdaroca.vemdarocaapi.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.model.Pedido;

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
	
	public String formatedPedidoEmail(Pedido pedido,List<ItemPedido> itemPedido) {
		StringBuilder returnText = new StringBuilder();

		returnText.append("Agradecemos seu pedido!! =D \n");
		returnText.append("Seu pedido foi solicitado com sucesso, abaixo está melhor detalhado: \n");
		returnText.append("Pedido: " + pedido.getId() + "\n");
		returnText.append("Data do pedido: " + pedido.getDataFormatada() + "\n");
		itemPedido.forEach(item -> {
			returnText.append("QTD: " + item.getQtd() + " \t\t Produto: " + item.getProduto().getNome()
					+ " \t\t\t\t\t Valor Unitário: R$" + String.format("%.2f", item.getValor())
					+ " \t\t\t\t\t SubTotal: R$" + String.format("%.2f", item.getSubTotal()) + "\n");
		});
		returnText.append("Valor Médio Total: R$" + String.format("%.2f", pedido.getTotal()));
		return returnText.toString();
	}
}
