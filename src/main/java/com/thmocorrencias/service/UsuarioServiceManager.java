package com.thmocorrencias.service;

import com.thmocorrencias.model.usuario.UsuarioEntity;
import com.thmocorrencias.model.usuario.UsuarioRequest;
import com.thmocorrencias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceManager {

    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //passwordEncoder().encode funcão para criptografar
    }

    public Object save(UsuarioRequest request) {

        if (repository.findByLoginOptional(request.getLogin()).isPresent()) {
            throw new RuntimeException("O email já está sendo utilizado.");
        }

        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setNome(request.getNome());
        usuario.setLogin(request.getLogin());
        usuario.setSenha(passwordEncoder().encode(request.getSenha()));
        usuario.setRole("MANAGER");

        repository.save(usuario);
        return null;
    }

}
