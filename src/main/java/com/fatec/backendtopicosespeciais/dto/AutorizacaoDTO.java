package com.fatec.backendtopicosespeciais.dto;

import com.fatec.backendtopicosespeciais.domain.Autorizacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutorizacaoDTO {
	
	private Long id;
	private String nome;

	public Autorizacao toEntityInsert(AutorizacaoDTO categoriaDTO) {
		return new Autorizacao(null, nome);
	}
	
	public Autorizacao toEntityUpdate(Autorizacao autorizacao) {
		autorizacao.setNome(this.nome);
		return autorizacao;
	}
}
