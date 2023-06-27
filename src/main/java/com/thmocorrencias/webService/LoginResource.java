package com.thmocorrencias.webService;

import com.thmocorrencias.infra.handler.Response;
import com.thmocorrencias.infra.handler.ResponseFactory;
import com.thmocorrencias.infra.handler.exception.BusinessException;
import com.thmocorrencias.infra.security.Login;
import com.thmocorrencias.infra.security.Session;
import com.thmocorrencias.infra.security.jwt.JwtFactory;
import com.thmocorrencias.infra.security.jwt.JwtObject;
import com.thmocorrencias.infra.security.jwt.JwtProperties;
import com.thmocorrencias.model.usuario.UsuarioEntity;
import com.thmocorrencias.repository.UsuarioRepository;
import com.thmocorrencias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class LoginResource {
    public static final BusinessException USUARIO_INVALIDO_EXCEPTION = new BusinessException("Login Inválido","403","Confirme seu login e senha");
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("/login")
    public Response login(@RequestBody Login login){
        UsuarioEntity entity = repository.findByLogin(login.getLogin());
        if(entity!=null ){
            Session session = new Session();
            session.setLogin(login.getLogin());
            session.setId(service.findIdByLogin(login.getLogin()));
            session.setNome(service.findNomeByLogin(login.getLogin()));

            boolean senhaValida = encoder.matches(login.getPassword(), entity.getSenha());

            if(!senhaValida)
                throw USUARIO_INVALIDO_EXCEPTION;

            JwtObject jwtObject = JwtObject.builder()
                    .subject(login.getLogin())
                    .issuedAt()
                    .expirationHours(4)
                    .roles(entity.getRole()==null?"USER":entity.getRole());

            session.setToken(JwtFactory.create(JwtProperties.PREFIX, JwtProperties.KEY, jwtObject));
            return ResponseFactory.ok(session,"Login realizado com sucesso");
        }else{
            throw USUARIO_INVALIDO_EXCEPTION;
        }
    }
}
