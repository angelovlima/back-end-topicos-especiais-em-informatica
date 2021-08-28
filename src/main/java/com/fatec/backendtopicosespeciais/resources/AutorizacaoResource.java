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

import com.fatec.backendtopicosespeciais.domain.Autorizacao;
import com.fatec.backendtopicosespeciais.dto.AutorizacaoDTO;
import com.fatec.backendtopicosespeciais.services.AutorizacaoService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@RequestMapping("autorizacao")
public class AutorizacaoResource {

	@Autowired
	private AutorizacaoService autorizacaoService;

	@ApiOperation(value="Realiza a busca de uma autorizacao por seu ID")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<Autorizacao> buscarPorId(@PathVariable Long id) throws Exception {
		Autorizacao autorizacao = autorizacaoService.buscarPorId(id);
		return ResponseEntity.ok().body(autorizacao);
	}
	
	@ApiOperation(value="Realiza o cadastro de uma autorizacao")
	@PostMapping("/cadastrar")
	public ResponseEntity<AutorizacaoDTO> cadastrar(@RequestBody AutorizacaoDTO autorizacaoDTO) throws Exception {
		autorizacaoService.inserir(autorizacaoDTO);
		return ResponseEntity.ok(autorizacaoDTO);
	}
	
	@ApiOperation(value="Realiza a edição de uma autorizacao")
	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<AutorizacaoDTO> alterar(@RequestBody AutorizacaoDTO autorizacaoDTO) throws Exception {
		autorizacaoService.alterar(autorizacaoDTO);
		return ResponseEntity.ok(autorizacaoDTO);
	}
	
	@ApiOperation(value="Realiza a deleção uma autorizacao")
	@DeleteMapping("excluir/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) throws Exception {
		autorizacaoService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Realiza a busca de todos as autorizacoes")
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<Autorizacao>> buscarTodos() throws Exception {
		List<Autorizacao> autorizacoes = autorizacaoService.buscarTodos();
		return ResponseEntity.ok().body(autorizacoes);
	}
}
