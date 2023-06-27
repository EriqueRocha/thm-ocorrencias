package com.thmocorrencias.repository;

import com.thmocorrencias.model.ocorrencia.OcorrenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<OcorrenciaEntity, Integer> {
    List<OcorrenciaEntity> findAllByUserId(Integer userId);
}
