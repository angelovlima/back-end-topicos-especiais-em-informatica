package com.fatec.backendtopicosespeciais.dto;

import java.util.List;

import com.fatec.backendtopicosespeciais.domain.Evento;
import com.fatec.backendtopicosespeciais.domain.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventoDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private List<Categoria> categorias;

	public Evento toEntityInsert(EventoDTO eventoDTO) {
		return new Evento(null, nome, descricao, categorias);
	}
	
	public Evento toEntityUpdate(Evento evento) {
		evento.setNome(this.nome);
		evento.setCategorias(this.categorias);
		evento.setDescricao(this.descricao);
		return evento;
	}
}
