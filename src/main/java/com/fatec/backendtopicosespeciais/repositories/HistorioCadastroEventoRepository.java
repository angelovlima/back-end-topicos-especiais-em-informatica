package com.fatec.backendtopicosespeciais.repositories;

import com.fatec.backendtopicosespeciais.domain.HistoricoCadastroEvento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorioCadastroEventoRepository extends JpaRepository<HistoricoCadastroEvento, Long>{
    
}
