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

import com.fatec.backendtopicosespeciais.domain.Categoria;
import com.fatec.backendtopicosespeciais.dto.CategoriaDTO;
import com.fatec.backendtopicosespeciais.services.CategoriaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@RequestMapping("categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value="Realiza a busca de uma categoria por seu ID")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) throws Exception {
		Categoria categoria = categoriaService.buscarPorId(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@ApiOperation(value="Realiza o cadastro de uma categoria")
	@PostMapping("/cadastrar")
	public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
		categoriaService.inserir(categoriaDTO);
		return ResponseEntity.ok(categoriaDTO);
	}
	
	@ApiOperation(value="Realiza a edição de uma categoria")
	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<CategoriaDTO> alterar(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
		categoriaService.alterar(categoriaDTO);
		return ResponseEntity.ok(categoriaDTO);
	}
	
	@ApiOperation(value="Realiza a deleção uma categoria")
	@DeleteMapping("excluir/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) throws Exception {
		categoriaService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Realiza a busca de todos as categorias")
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<Categoria>> buscarTodos() throws Exception {
		List<Categoria> categorias = categoriaService.buscarTodos();
		return ResponseEntity.ok().body(categorias);
	}
}
