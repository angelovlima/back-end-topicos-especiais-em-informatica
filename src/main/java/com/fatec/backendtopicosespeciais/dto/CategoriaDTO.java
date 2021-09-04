package com.fatec.backendtopicosespeciais.dto;

import com.fatec.backendtopicosespeciais.domain.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaDTO {
	
	private Long id;
	private String nome;

	public Categoria toEntityInsert() {
		return new Categoria(null, this.nome);
	}
	
	public Categoria toEntityUpdate(Categoria categoria) {
		categoria.setNome(this.nome);
		return categoria;
	}
}
