package com.fatec.backendtopicosespeciais.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.backendtopicosespeciais.domain.Categoria;
import com.fatec.backendtopicosespeciais.dto.CategoriaDTO;
import com.fatec.backendtopicosespeciais.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscarPorId(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("categoria não encontrada!", null));
	}
	
	public Categoria inserir(CategoriaDTO categoriaDTO) {

		Categoria categoria = categoriaDTO.toEntityInsert();
		
		categoriaRepository.save(categoria);
		return categoria;
	}
	
	public Categoria alterar(CategoriaDTO categoriaDTO) throws Exception {
		if (categoriaDTO.getId() == null) {
			throw new Exception("Id não encontrado");
		}

		Categoria categoria = categoriaDTO.toEntityUpdate(buscarPorId(categoriaDTO.getId()));
		categoriaRepository.save(categoria);
		return categoria;
	}
	
	public void deletar(Long id) {
		categoriaRepository.deleteById(id);
	}
	
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}

}
