package com.fatec.backendtopicosespeciais.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.backendtopicosespeciais.domain.TipoUsuario;
import com.fatec.backendtopicosespeciais.dto.TipoUsuarioDTO;
import com.fatec.backendtopicosespeciais.repositories.TipoUsuarioRepository;

@Service
public class TipoUsuarioService {
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	public TipoUsuario buscarPorId(Long id) {
		Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(id);
		return tipoUsuario.orElseThrow(() -> new ObjectNotFoundException("tipo usuário não encontrada!", null));
	}
	
	public TipoUsuario inserir(TipoUsuarioDTO tipoUsuarioDTO) {

		TipoUsuario tipoUsuario = tipoUsuarioDTO.toEntityInsert(tipoUsuarioDTO);
		
		tipoUsuarioRepository.save(tipoUsuario);
		return tipoUsuario;
	}
	
	public TipoUsuario alterar(TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		if (tipoUsuarioDTO.getId() == null) {
			throw new Exception("Id não encontrado");
		}

		TipoUsuario tipoUsuario = tipoUsuarioDTO.toEntityUpdate(buscarPorId(tipoUsuarioDTO.getId()));
		tipoUsuarioRepository.save(tipoUsuario);
		return tipoUsuario;
	}
	
	public void deletar(Long id) {
		tipoUsuarioRepository.deleteById(id);
	}
	
	public List<TipoUsuario> buscarTodos() {
		return tipoUsuarioRepository.findAll();
	}
	
}
