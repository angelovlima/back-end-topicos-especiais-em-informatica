package com.fatec.backendtopicosespeciais.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.backendtopicosespeciais.domain.Usuario;
import com.fatec.backendtopicosespeciais.resources.Views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
	
	@JsonView(Views.Public.class)
	private Long id;
	@JsonView(Views.Public.class)
	private String nome;
	@JsonView(Views.Internal.class)
	private String senha;
	@JsonView(Views.Public.class)
	private Boolean admin;

	public Usuario toEntityInsert() {
		return new Usuario(null, this.nome, this.senha, this.admin);
	}
	
	public Usuario toEntityUpdate(Usuario usuario) {
		usuario.setNome(this.nome);
		usuario.setSenha(this.senha);
		usuario.setAdmin(this.admin);
		return usuario;
	}
}
