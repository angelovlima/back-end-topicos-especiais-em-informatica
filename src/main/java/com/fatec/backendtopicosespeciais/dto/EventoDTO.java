package com.fatec.backendtopicosespeciais.dto;

import com.fatec.backendtopicosespeciais.domain.Evento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventoDTO {
	
	private Long id;
	private String nome;

	public Evento toEntityInsert(EventoDTO eventoDTO) {
		return new Evento(null, nome);
	}
	
	public Evento toEntityUpdate(Evento evento) {
		evento.setNome(this.nome);
		return evento;
	}
}
