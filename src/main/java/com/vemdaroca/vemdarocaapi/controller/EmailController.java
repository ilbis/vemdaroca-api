package com.vemdaroca.vemdarocaapi.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vemdaroca.vemdarocaapi.config.ConfigConstants;
import com.vemdaroca.vemdarocaapi.dto.ClienteResponseDTO;
import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;
import com.vemdaroca.vemdarocaapi.service.ClienteService;
import com.vemdaroca.vemdarocaapi.service.EmailService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	@PostMapping
	@ApiOperation(value = "Recuperar cadastro")
	public ResponseEntity<ClienteResponseDTO> create(@RequestBody String email) throws Exception {
		Cliente cliente = clienteService.getByEmail(email);

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		String passwordNew = RandomStringUtils.random(15, characters);

		cliente.setPassword(PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT));

		ClienteResponseDTO clienteResponse = clienteService.update(cliente.getId(), cliente);
		
		try {
			emailService.sendMail(email, "Recuperação de Senha",
					"Sua nova senha é:\n\n" + passwordNew + "\n\nAltere o mais rapido possivel");
		} catch (Exception e) {
			System.out.println("Erro ao enviar email: " + e);
			throw new Exception(e);
		}
		return ResponseEntity.ok().body(clienteResponse);
	}

}
