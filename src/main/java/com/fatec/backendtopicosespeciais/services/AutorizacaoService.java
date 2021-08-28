package com.fatec.backendtopicosespeciais.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.backendtopicosespeciais.domain.Autorizacao;
import com.fatec.backendtopicosespeciais.dto.AutorizacaoDTO;
import com.fatec.backendtopicosespeciais.repositories.AutorizacaoRepository;

@Service
public class AutorizacaoService {
	
	@Autowired
	private AutorizacaoRepository autorizacaoRepository;

	public Autorizacao buscarPorId(Long id) {
		Optional<Autorizacao> autorizacao = autorizacaoRepository.findById(id);
		return autorizacao.orElseThrow(() -> new ObjectNotFoundException("autorizacao não encontrada!", null));
	}
	
	public Autorizacao inserir(AutorizacaoDTO autorizacaoDTO) {

		Autorizacao autorizacao = autorizacaoDTO.toEntityInsert(autorizacaoDTO);
		
		autorizacaoRepository.save(autorizacao);
		return autorizacao;
	}
	
	public Autorizacao alterar(AutorizacaoDTO autorizacaoDTO) throws Exception {
		if (autorizacaoDTO.getId() == null) {
			throw new Exception("Id não encontrado");
		}

		Autorizacao autorizacao = autorizacaoDTO.toEntityUpdate(buscarPorId(autorizacaoDTO.getId()));
		autorizacaoRepository.save(autorizacao);
		return autorizacao;
	}
	
	public void deletar(Long id) {
		autorizacaoRepository.deleteById(id);
	}
	
	public List<Autorizacao> buscarTodos() {
		return autorizacaoRepository.findAll();
	}
}
