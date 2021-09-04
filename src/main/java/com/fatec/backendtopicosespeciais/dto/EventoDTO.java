package com.fatec.backendtopicosespeciais.dto;

import java.util.List;

import com.fatec.backendtopicosespeciais.domain.Categoria;
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
	private String descricao;
	private String autor;
	private String endereco;
	private boolean ativo;
	private List<Categoria> categorias;

	public Evento toEntityInsert() {
		return new Evento(null, this.nome, this.descricao, this.autor, this.endereco, this.ativo, this.categorias);
	}
	
	public Evento toEntityUpdate(Evento evento) {
		evento.setNome(this.nome);
		evento.setCategorias(this.categorias);
		evento.setDescricao(this.descricao);
		evento.setAutor(this.autor);
		evento.setEndereco(this.endereco);
		evento.setAtivo(this.ativo);
		return evento;
	}

}
