package com.fatec.backendtopicosespeciais.dto;

import com.fatec.backendtopicosespeciais.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioSemSenhaDTO {
	private Long id;

	private String nome;

	private Boolean admin;

	public UsuarioSemSenhaDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.admin = usuario.getAdmin();
	}
	
	
}
