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

import com.fatec.backendtopicosespeciais.domain.Evento;
import com.fatec.backendtopicosespeciais.dto.EventoDTO;
import com.fatec.backendtopicosespeciais.services.EventoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@Api(value="API REST EVENTOS")
@RequestMapping("eventos")
public class EventoResource {

	@Autowired
	private EventoService eventoService;
	
	@ApiOperation(value="Realiza a busca de um evento por seu ID")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) throws Exception {
		Evento evento = eventoService.buscarPorId(id);
		return ResponseEntity.ok().body(evento);
	}
	
	@ApiOperation(value="Realiza o cadastro de um evento")
	@PostMapping("/cadastrar")
	public ResponseEntity<EventoDTO> cadastrar(@RequestBody EventoDTO eventoDTO) throws Exception {
		eventoService.inserir(eventoDTO);
		return ResponseEntity.ok(eventoDTO);
	}
	
	@ApiOperation(value="Realiza a edição de um evento")
	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<EventoDTO> alterar(@RequestBody EventoDTO eventoDTO) throws Exception {
		eventoService.alterar(eventoDTO);
		return ResponseEntity.ok(eventoDTO);
	}
	
	@ApiOperation(value="Realiza a deleção um evento")
	@DeleteMapping("excluir/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) throws Exception {
		eventoService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Realiza a busca de todos os eventos")
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<Evento>> buscarTodos() throws Exception {
		List<Evento> eventos = eventoService.buscarTodos();
		return ResponseEntity.ok().body(eventos);
	}
	
	@ApiOperation(value="Realiza a busca de um evento por seu ID e nome")
	@GetMapping("/buscarPorIdNome/{id}/{nome}")
	public ResponseEntity<Evento> buscarPorIdNome(@PathVariable Long id, @PathVariable String nome) throws Exception {
		Evento evento = eventoService.buscarPorIdNome(id, nome);
		return ResponseEntity.ok().body(evento);
	}
}
