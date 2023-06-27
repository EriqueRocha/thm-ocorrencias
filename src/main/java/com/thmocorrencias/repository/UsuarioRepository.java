package com.thmocorrencias.repository;

import com.thmocorrencias.model.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findByLogin(String login);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.login = :login")
    Optional<UsuarioEntity> findByLoginOptional(@Param("login") String login);
}
