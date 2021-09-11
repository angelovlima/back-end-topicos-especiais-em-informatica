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

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.backendtopicosespeciais.resources.Views;

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
	@JsonView(Views.Public.class)
	private Long id;
	@JsonView(Views.Public.class)
	private String nome;
	@JsonView(Views.Public.class)
	private String descricao;
	@JsonView(Views.Internal.class)
	private String autor;
	@JsonView(Views.Internal.class)
	private String endereco;
	@JsonView(Views.Internal.class)
	private boolean ativo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_evento_categoria",
		joinColumns = { @JoinColumn(name = "id_evento") },
		inverseJoinColumns = { @JoinColumn(name = "id_categoria") })
	private List<Categoria> categorias;
	
}
