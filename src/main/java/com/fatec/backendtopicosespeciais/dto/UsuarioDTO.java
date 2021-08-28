package com.fatec.backendtopicosespeciais.dto;

import com.fatec.backendtopicosespeciais.domain.TipoUsuario;
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
	private TipoUsuario tipoUsuario;

	public Usuario toEntityInsert(UsuarioDTO usuarioDTO) {
		return new Usuario(null, nome, senha, tipoUsuario);
	}
	
	public Usuario toEntityUpdate(Usuario usuario) {
		usuario.setNome(this.nome);
		usuario.setSenha(this.senha);
		usuario.setTipoUsuario(this.tipoUsuario);
		return usuario;
	}
}
