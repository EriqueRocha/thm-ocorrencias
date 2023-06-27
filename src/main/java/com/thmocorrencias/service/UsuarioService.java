package com.thmocorrencias.service;

import com.thmocorrencias.infra.handler.exception.CampoObrigatorioException;
import com.thmocorrencias.infra.handler.exception.RegistroNaoLocalizadoException;
import com.thmocorrencias.model.ocorrencia.OcorrenciaEntity;
import com.thmocorrencias.model.usuario.*;
import com.thmocorrencias.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //passwordEncoder().encode funcão para criptografar
    }

    public UsuarioEntity findByLogin(String login) {
        Optional<UsuarioEntity> optionalUsuario = repository.findByLoginOptional(login);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        } else {
            throw new RegistroNaoLocalizadoException("Usuário", "Login");
        }
    }

    public Integer findIdByLogin(String login) {
        UsuarioEntity usuario = findByLogin(login);
        return usuario.getId();
    }

    public String findNomeByLogin(String login) {
        UsuarioEntity usuario = findByLogin(login);
        return usuario.getNome();
    }

    public Object save(UsuarioRequest request) {

        if (repository.findByLoginOptional(request.getLogin()).isPresent()) {
            throw new RuntimeException("O email já está sendo utilizado.");
        }

        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setNome(request.getNome());
        usuario.setLogin(request.getLogin());
        usuario.setSenha(passwordEncoder().encode(request.getSenha()));
        usuario.setRole("USER");

        repository.save(usuario);
        return null;
    }

    public Object update(UsuarioUpdate request) {

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(Math.toIntExact(request.getId()));
        usuario.setNome(request.getNome());
        usuario.setLogin(request.getLogin());
        usuario.setSenha(passwordEncoder().encode(request.getSenha()));
        usuario.setRole(request.getRole());

        repository.save(usuario);

        return null;
    }

    private UsuarioEntity findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException("Usuário", "ID"));
    }


    public boolean delete(Integer id){
        UsuarioEntity entity = findById(id);
        repository.delete(entity);
        return true;

    }

    public List<UsuarioEntity> findAllWithNumOcorrencias(){
        List<UsuarioEntity> users = repository.findAll();
        List<UsuarioEntity> dtos = new ArrayList<>();

        for(UsuarioEntity user: users){
            UsuarioEntity dto = new UsuarioEntity();
            dto.setId(user.getId());
            dto.setNome(user.getNome());
            dto.setLogin(user.getLogin());
            dto.setRole(user.getRole());

            List<OcorrenciaEntity> ocorrencias = user.getOcorrencia();
            int numOcorrencias = ocorrencias.size();

            dto.setNumOcorrencias(numOcorrencias);
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public Integer persist(Integer id, UsuarioRequest request){

        if(request.getNome()==null || request.getNome().isEmpty())
            throw new CampoObrigatorioException("Nome");

        if(request.getLogin()==null || request.getLogin().isEmpty())
            throw new CampoObrigatorioException("Login");

        if(request.getSenha()==null || request.getSenha().isEmpty())
            throw new CampoObrigatorioException("senha");

        UsuarioEntity entity = null;
        if(id!=null){
            entity = this.findById(id);
        }else
            entity = new UsuarioEntity();

        BeanUtils.copyProperties(request,entity);

        return repository.save(entity).getId();
    }
}
