package com.thmocorrencias.webService;

import com.thmocorrencias.infra.handler.Response;
import com.thmocorrencias.infra.handler.ResponseFactory;
import com.thmocorrencias.model.ocorrencia.OcorrenciaDTO;
import com.thmocorrencias.model.ocorrencia.OcorrenciaEntity;
import com.thmocorrencias.model.usuario.UsuarioDTO;
import com.thmocorrencias.model.usuario.UsuarioEntity;
import com.thmocorrencias.model.usuario.UsuarioRequest;
import com.thmocorrencias.model.usuario.UsuarioUpdate;
import com.thmocorrencias.repository.UsuarioRepository;
import com.thmocorrencias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/cadastro")
    public Response post (@RequestBody UsuarioRequest request){
        return ResponseFactory.create(service.save(request), "Usuario salvo com sucesso");
    }

    @PutMapping("/id")
    public Response put (@RequestBody UsuarioUpdate request){
        return ResponseFactory.ok(service.update(request), "Usuário alterado com sucesso");
    }

    @GetMapping("/list")
    public List<UsuarioEntity> listAll(){
        return service.findAllWithNumOcorrencias();
    }


    @GetMapping("/{id}")
    public Response getOne(@PathVariable Integer id){
        UsuarioEntity user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioDTO userDTO = new UsuarioDTO();
        userDTO.setId(user.getId());
        userDTO.setNome(user.getNome());
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRole());
        List<OcorrenciaEntity> ocorrenciasN = user.getOcorrencia();
        int numOcorrencias = ocorrenciasN.size();
        userDTO.setNumOcorrencias(numOcorrencias);

        List<OcorrenciaDTO> ocorrenciasDTO = new ArrayList<>();
        List<OcorrenciaEntity> ocorrencias = user.getOcorrencia();
        for (OcorrenciaEntity ocorrencia : ocorrencias) {
            OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
            ocorrenciaDTO.setId(ocorrencia.getId());
            ocorrenciaDTO.setData(ocorrencia.getData());
            ocorrenciaDTO.setEndereco(ocorrencia.getEndereco());
            ocorrenciaDTO.setIntensidade(ocorrencia.getIntensidade());
            ocorrenciaDTO.setClassificacao(ocorrencia.getClassificacao());
            ocorrenciasDTO.add(ocorrenciaDTO);
        }
        userDTO.setOcorrencias(ocorrenciasDTO);

        return ResponseFactory.okOrNotFound(userDTO);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Integer id){
        return ResponseFactory.ok(service.delete(id),"Usuário excluído com sucesso");
    }
}
