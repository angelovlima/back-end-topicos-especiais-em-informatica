package com.fatec.backendtopicosespeciais.dto;

import java.util.List;

import com.fatec.backendtopicosespeciais.domain.Autorizacao;
import com.fatec.backendtopicosespeciais.domain.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoUsuarioDTO {
	
	private Long id;
	private String nome;
	private List<Autorizacao> autorizacoes;

	public TipoUsuario toEntityInsert(TipoUsuarioDTO tipoUsuarioDTO) {
		return new TipoUsuario(null, nome, autorizacoes);
	}
	
	public TipoUsuario toEntityUpdate(TipoUsuario tipoUsuario) {
		tipoUsuario.setNome(this.nome);
		tipoUsuario.setAutorizacoes(this.autorizacoes);
		return tipoUsuario;
	}
}
