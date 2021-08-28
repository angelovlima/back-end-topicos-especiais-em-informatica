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

import com.fatec.backendtopicosespeciais.domain.TipoUsuario;
import com.fatec.backendtopicosespeciais.dto.TipoUsuarioDTO;
import com.fatec.backendtopicosespeciais.services.TipoUsuarioService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@RequestMapping("tipo-usuario")
public class TipoUsuarioResource {

	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	
	@ApiOperation(value="Realiza a busca de um tipo de usuário por seu ID")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<TipoUsuario> buscarPorId(@PathVariable Long id) throws Exception {
		TipoUsuario tipoUsuario = tipoUsuarioService.buscarPorId(id);
		return ResponseEntity.ok().body(tipoUsuario);
	}
	
	@ApiOperation(value="Realiza o cadastro de um tipo de usuário")
	@PostMapping("/cadastrar")
	public ResponseEntity<TipoUsuarioDTO> cadastrar(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		tipoUsuarioService.inserir(tipoUsuarioDTO);
		return ResponseEntity.ok(tipoUsuarioDTO);
	}

	@ApiOperation(value="Realiza a edição de um tipo de usuário")
	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<TipoUsuarioDTO> alterar(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		tipoUsuarioService.alterar(tipoUsuarioDTO);
		return ResponseEntity.ok(tipoUsuarioDTO);
	}
	
	@ApiOperation(value="Realiza a deleção um tipo de usuário")
	@DeleteMapping("excluir/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) throws Exception {
		tipoUsuarioService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Realiza a busca de todos os tipos de usuários")
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<TipoUsuario>> buscarTodos() throws Exception {
		List<TipoUsuario> tiposUsuario = tipoUsuarioService.buscarTodos();
		return ResponseEntity.ok().body(tiposUsuario);
	}
}
