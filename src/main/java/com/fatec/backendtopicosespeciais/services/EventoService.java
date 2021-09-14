package com.fatec.backendtopicosespeciais.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.backendtopicosespeciais.domain.Evento;
import com.fatec.backendtopicosespeciais.dto.EventoDTO;
import com.fatec.backendtopicosespeciais.repositories.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private HistoricoCadastroEventoService historicoCadastroEventoService;
	
	public Evento buscarPorId(Long id) {
		Optional<Evento> evento = eventoRepository.findById(id);
		return evento.orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado!", null));
	}
	
	@Transactional
	public Evento inserir(EventoDTO eventoDTO) {

		Evento evento = eventoDTO.toEntityInsert();
		historicoCadastroEventoService.inserir(eventoDTO);
		eventoRepository.save(evento);
		return evento;
	}
	
	public Evento alterar(EventoDTO eventoDTO) throws Exception {
		if (eventoDTO.getId() == null) {
			throw new Exception("Id não encontrado");
		}

		Evento evento = eventoDTO.toEntityUpdate(buscarPorId(eventoDTO.getId()));
		eventoRepository.save(evento);
		return evento;
	}
	
	public void deletar(Long id) {
		eventoRepository.deleteById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<Evento> buscarTodos() {
		return eventoRepository.findAll();
	}
	
	public Evento buscarPorIdNome(Long id, String nome) {
		Optional<Evento> evento = eventoRepository.findByNomeAndId(nome, id);
		return evento.orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado!", null));
	}
	
	public List<Evento> buscarEventosPorNomeCategoria(String nomeCategoria) {
		Optional<List<Evento>> listaEvento = eventoRepository.buscarEventosPorNomeCategoria(nomeCategoria);
		return listaEvento.orElseThrow(() -> new ObjectNotFoundException("Eventos não encontrados!", null));
	}

}
