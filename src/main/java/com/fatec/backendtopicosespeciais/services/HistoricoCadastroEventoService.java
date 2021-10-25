package com.fatec.backendtopicosespeciais.services;

import com.fatec.backendtopicosespeciais.domain.HistoricoCadastroEvento;
import com.fatec.backendtopicosespeciais.dto.EventoDTO;
import com.fatec.backendtopicosespeciais.repositories.HistorioCadastroEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class HistoricoCadastroEventoService {

    @Autowired
    HistorioCadastroEventoRepository historioCadastroEventoRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public HistoricoCadastroEvento inserir(EventoDTO eventoDTO) {

		HistoricoCadastroEvento historicoCadastroEvento = new HistoricoCadastroEvento();
		historicoCadastroEvento.setAutorEvento(eventoDTO.getAutor());
        historicoCadastroEvento.setNomeEvento(eventoDTO.getNome());
		historioCadastroEventoRepository.save(historicoCadastroEvento);
		return historicoCadastroEvento;
	}
    
}
