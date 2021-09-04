package com.fatec.backendtopicosespeciais.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.backendtopicosespeciais.dto.UsuarioDTO;
import com.fatec.backendtopicosespeciais.services.impl.UsuarioServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@RequestMapping("usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@ApiOperation(value="Realiza o cadastro de um usuário")
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		usuarioServiceImpl.inserir(usuarioDTO);
		//Retornar outro DTO sem senha
		return ResponseEntity.ok(usuarioDTO);
	}

}
