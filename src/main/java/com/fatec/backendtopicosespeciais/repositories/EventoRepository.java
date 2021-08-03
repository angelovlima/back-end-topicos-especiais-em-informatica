package com.fatec.backendtopicosespeciais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.backendtopicosespeciais.domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
