package br.com.senac.services.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.services.dao.UsuarioDAO;
import br.com.senac.services.i18n.Messages;
import br.com.senac.services.i18n.MessagesProperties;
import br.com.senac.services.model.Usuario;
import br.com.senac.services.rest.ApiResponse;
import br.com.senac.services.rest.ResponseEntityUtil;

@RestController
@CrossOrigin
public class UsuarioController {

	@Autowired
	Messages message;

	@Autowired
	UsuarioDAO dao;

	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	@PostMapping(value = "/saveUser", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Usuario usuario) {
		Usuario novoUsuario = null;
		try {
			Usuario usuarioNovo = usuario;
			if (usuario == null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND),
						usuario);
			} else {
				if (usuarioNovo.getNome().equalsIgnoreCase("") || usuarioNovo.getSenha().equals("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FIELDS),
							usuario);
				} else {
					novoUsuario = dao.saveUser(usuario);
					if (novoUsuario != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_SUCESS),
								novoUsuario);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(
								message.get(MessagesProperties.API_UNKNOWN_FIELDS), usuario);
					}
				}
			}
		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND),
					usuario);
		}

	}
	
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	@PatchMapping(value = "/updateUser", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody @Validated Usuario usuario) {
		try {
			Usuario updateUsuario = usuario;
			if (usuario == null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND),
						usuario);
			} else {
				if (updateUsuario.getNome().equalsIgnoreCase("") || updateUsuario.getSenha().equals("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FIELDS),
							usuario);
				} else {
					updateUsuario = dao.saveUser(usuario);
					if (updateUsuario != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_UPDATED));
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(
								message.get(MessagesProperties.API_UNKNOWN_FIELDS), usuario);
					}
				}
			}
		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND),
					usuario);
		}

	}

	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	@GetMapping(value = "/lista_usuarios", produces = "application/json")
	public ResponseEntity<ApiResponse> listUsers() {
		java.util.List<Usuario> usuarios = null;
		try {

			usuarios = dao.listAll();

			if (usuarios != null) {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.API_SUCESS), usuarios);
			} else {
				return ResponseEntityUtil
						.unprocessableResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), usuarios);
			}

		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_ERROR), usuarios);
		}

	}
	
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	@GetMapping(value = "/findById", produces = "application/json")
	public ResponseEntity<ApiResponse> findById(@RequestParam("id") Long id) {
		Usuario usuario = null;
		try {

			usuario = dao.findById(id);

			if (usuario != null) {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.API_SUCESS), usuario);
			} else {
				return ResponseEntityUtil
						.unprocessableResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), usuario);
			}

		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_ERROR), usuario);
		}

	}
	
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<ApiResponse> delete(@RequestParam("id") Long id) {
		boolean resultado = false;
		try {

			if (resultado = dao.delete(id) == true) {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.API_SUCESS));
			} else {
				return ResponseEntityUtil
						.unprocessableResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), null);
			}

		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_ERROR), null);
		}

	}
	
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	@GetMapping(value = "/findByName", produces = "application/json")
	public ResponseEntity<ApiResponse> findByName(@RequestParam("nome") String nome) {
		List<Usuario> usuario = null;
		try {

			usuario = dao.findByName(nome);

			if (usuario != null) {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.API_SUCESS), usuario);
			} else {
				return ResponseEntityUtil
						.unprocessableResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), usuario);
			}

		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_ERROR), usuario);
		}

	}

}
