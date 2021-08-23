package com.fatec.backendtopicosespeciais.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="evento")
public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	private String nome;
	private String descricao;
	private String autor;
	private String endereco;
	private boolean ativo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_evento_categoria",
		joinColumns = { @JoinColumn(name = "id_evento") },
		inverseJoinColumns = { @JoinColumn(name = "id_categoria") })
	private List<Categoria> categorias;
	
}
