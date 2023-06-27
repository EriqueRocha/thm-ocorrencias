package com.thmocorrencias.webService;

import com.thmocorrencias.infra.handler.Response;
import com.thmocorrencias.infra.handler.ResponseFactory;
import com.thmocorrencias.model.usuario.UsuarioRequest;
import com.thmocorrencias.service.UsuarioServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuariosManager")
public class UsuarioResourceManager {

    @Autowired
    private UsuarioServiceManager service;

    @PostMapping("/cadastro")
    public Response post (@RequestBody UsuarioRequest request){
        return ResponseFactory.create(service.save(request), "Usuario salvo com sucesso");
    }

}
