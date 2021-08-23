package com.fatec.backendtopicosespeciais.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="historico_cadastro_evento")
public class HistoricoCadastroEvento {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="autor_evento")
    private String autorEvento;
    @Column(name="nome_evento")
    private String nomeEvento;
    
}
