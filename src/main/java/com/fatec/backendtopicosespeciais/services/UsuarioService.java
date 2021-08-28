package com.fatec.backendtopicosespeciais.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.backendtopicosespeciais.domain.Usuario;
import com.fatec.backendtopicosespeciais.dto.UsuarioDTO;
import com.fatec.backendtopicosespeciais.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrada!", null));
	}
	
	public Usuario inserir(UsuarioDTO usuarioDTO) {

		Usuario usuario = usuarioDTO.toEntityInsert(usuarioDTO);
		
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario alterar(UsuarioDTO usuarioDTO) throws Exception {
		if (usuarioDTO.getId() == null) {
			throw new Exception("Id não encontrado");
		}

		Usuario usuario = usuarioDTO.toEntityUpdate(buscarPorId(usuarioDTO.getId()));
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

}
