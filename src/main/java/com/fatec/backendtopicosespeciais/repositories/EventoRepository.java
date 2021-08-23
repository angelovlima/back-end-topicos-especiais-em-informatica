package com.fatec.backendtopicosespeciais.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.backendtopicosespeciais.domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

	@Query("select e from Evento e where e.nome like %?1% and e.id = ?2")
	public Optional<Evento> buscarPorIdNome(String nome, Long id);
	
	@Query("select e from Evento e join e.categorias as c where c.nome like %?1%")
	public Optional<List<Evento>> buscarEventosPorNomeCategoria(String nome);
}
