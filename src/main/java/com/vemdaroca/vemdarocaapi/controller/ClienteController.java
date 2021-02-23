package com.vemdaroca.vemdarocaapi.controller;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vemdaroca.vemdarocaapi.config.ConfigConstants;
import com.vemdaroca.vemdarocaapi.dto.ClienteDTO;
import com.vemdaroca.vemdarocaapi.dto.ClienteResponseDTO;
import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.Role;
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;
import com.vemdaroca.vemdarocaapi.service.ClienteService;
import com.vemdaroca.vemdarocaapi.service.EmailService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Value("${environment.url}")
	private String url;

	private static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#%^&*()-_=+<>?";

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

//	@GetMapping(value = "/allActive")
//	@ApiOperation(value = "Retorna todos clientes ativos")
//	public ResponseEntity<List<ClienteResponseDTO>> getAllActive() {
//		return ResponseEntity.ok().body(clienteService.getAllActive());
//	}
//
//	@GetMapping(value = "/all")
//	@ApiOperation(value = "Retorna todos clientes")
//	public ResponseEntity<List<ClienteResponseDTO>> getAll() {
//		return ResponseEntity.ok().body(clienteService.getAll());
//	}
//
//	@GetMapping(value = "/{id}")
//	@ApiOperation(value = "Retorna cliente por Id")
//	public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id) {
//		return ResponseEntity.ok().body(ClienteResponseDTO.toDTO(clienteService.getById(id)));
//	}

	@GetMapping(value = "/userOnSession")
	@ApiOperation(value = "Retorna cliente da sessao")
	public ResponseEntity<ClienteResponseDTO> getByIdOnSession() {
		Authentication x = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok()
				.body(ClienteResponseDTO.toDTO(clienteService.getById(Long.parseLong(x.getPrincipal().toString()))));
	}

//	@GetMapping(value = "/getCliente")
//	@ApiOperation(value = "Retorna cliente por nome")
//	public ResponseEntity<List<ClienteResponseDTO>> getByName(
//			@RequestParam(value = "nome", required = false) String nome) {
//		return ResponseEntity.ok().body(clienteService.getByName(nome));
//	}
//
//	@GetMapping(value = "/getByUserName")
//	@ApiOperation(value = "Retorna cliente por username")
//	public ResponseEntity<ClienteResponseDTO> getByUserName(
//			@RequestParam(value = "username", required = false) String username) {
//		return ResponseEntity.ok().body(clienteService.getByUserName(username));
//	}

	@PostMapping
	@ApiOperation(value = "Criar um cliente")
	public ResponseEntity<ClienteResponseDTO> create(@RequestBody ClienteDTO dto) throws Exception {
		String validatorCode = RandomStringUtils.random(15, CHARACTERS);

		Cliente cliente = dto.toObject();
		cliente.setStatus('P');
		cliente.setRole(Role.ROLE_USER);
		cliente.setValidatorCode(validatorCode);
		ClienteResponseDTO clienteResponse = clienteService.create(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();

		try {
			emailService.sendMail(cliente.getEmail(), "Confirmação de Cadastro",
					"Obrigado por cadastrar no Vem da Roça\n" + "Clique no link abaixo para concluir seu cadastro\n\n"
							+ url + "/cliente/confirmaCadastro/" + clienteResponse.getId() + "/" + validatorCode);
		} catch (Exception e) {
			System.out.println("Erro ao enviar email: " + e);
			throw new Exception(e);
		}

		return ResponseEntity.created(uri).body(clienteResponse);
	}

//	@DeleteMapping(value = "/{id}")
//	@ApiOperation(value = "Apaga um cliente")
//	public ResponseEntity<ClienteResponseDTO> delete(@PathVariable Long id) {
//		clienteService.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	@PutMapping(value = "/{id}")
//	@ApiOperation(value = "Atualiza um cliente")
//	public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
//		return ResponseEntity.ok().body(clienteService.update(id, dto.toObject()));
//	}

	@PutMapping(value = "/userOnSession")
	@ApiOperation(value = "Atualiza cliente da sessao")
	public ResponseEntity<ClienteResponseDTO> updateUserSession(@RequestBody ClienteDTO dto) {
		Authentication x = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok()
				.body(clienteService.update(Long.parseLong(x.getPrincipal().toString()), dto.toObject()));
	}

	@PostMapping(value = "/recuperaCadastro")
	@ApiOperation(value = "Recuperar cadastro")
	public ResponseEntity<ClienteResponseDTO> create(@RequestBody String email) throws Exception {
		Cliente cliente = clienteService.getByEmail(email);
		String passwordNew = RandomStringUtils.random(15, CHARACTERS);

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

	@GetMapping(value = "/confirmaCadastro/{id}/{validatorCode}")
	@ApiOperation(value = "Confirma Cadastro")
	public ResponseEntity<String> confirmaCadastro(@PathVariable Long id, @PathVariable String validatorCode) {
		Cliente cliente = clienteService.getById(id);

		if (validatorCode.equals(cliente.getValidatorCode()) && cliente.getStatus() == 'P') {
			cliente.setStatus('A');
			clienteService.update(cliente.getId(), cliente);
			return ResponseEntity.ok().body("Cadastro Confirmado");
		}
		return ResponseEntity.ok().body("Erro ao Confirmar Cadastro");

	}

}
