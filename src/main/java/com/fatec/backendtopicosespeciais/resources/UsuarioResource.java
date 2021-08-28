package com.fatec.backendtopicosespeciais.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.backendtopicosespeciais.domain.Usuario;
import com.fatec.backendtopicosespeciais.dto.UsuarioDTO;
import com.fatec.backendtopicosespeciais.services.UsuarioService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@RequestMapping("usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation(value="Realiza a busca de um usuário por seu ID")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) throws Exception {
		Usuario usuario = usuarioService.buscarPorId(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@ApiOperation(value="Realiza o cadastro de um usuário")
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		usuarioService.inserir(usuarioDTO);
		return ResponseEntity.ok(usuarioDTO);
	}
	
	@ApiOperation(value="Realiza a edição de um usuário")
	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<UsuarioDTO> alterar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		usuarioService.alterar(usuarioDTO);
		return ResponseEntity.ok(usuarioDTO);
	}
	
	@ApiOperation(value="Realiza a deleção um usuário")
	@DeleteMapping("excluir/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) throws Exception {
		usuarioService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Realiza a busca de todos os usuários")
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<Usuario>> buscarTodos() throws Exception {
		List<Usuario> usuarios = usuarioService.buscarTodos();
		return ResponseEntity.ok().body(usuarios);
	}
}
