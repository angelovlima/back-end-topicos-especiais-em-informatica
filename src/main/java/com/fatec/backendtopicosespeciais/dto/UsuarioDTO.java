package com.fatec.backendtopicosespeciais.dto;

import com.fatec.backendtopicosespeciais.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String senha;
	private Boolean admin;
	//private TipoUsuario tipoUsuario;

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
