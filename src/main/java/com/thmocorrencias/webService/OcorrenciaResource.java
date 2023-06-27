package com.thmocorrencias.webService;

import com.thmocorrencias.infra.handler.Response;
import com.thmocorrencias.infra.handler.ResponseFactory;
import com.thmocorrencias.model.ocorrencia.OcorrenciaEntity;
import com.thmocorrencias.model.ocorrencia.OcorrenciaRequest;
import com.thmocorrencias.model.ocorrencia.OcorrenciaUpdate;
import com.thmocorrencias.repository.OcorrenciaRepository;
import com.thmocorrencias.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaResource {

    @Autowired
    private OcorrenciaService service;

    @Autowired
    private OcorrenciaRepository repository;

    @PostMapping
    public Response post (@RequestBody OcorrenciaRequest request){
        return ResponseFactory.ok(service.save(request), "Ocorrencia salva com sucesso");
    }

    @PutMapping("/id")
    public Response put (@RequestBody OcorrenciaUpdate request){
        return ResponseFactory.ok(service.update(request), "Ocorrencia alterada com sucesso");
    }

    @GetMapping("/list")
    public List<OcorrenciaEntity> listAll(){
        return repository.findAll();
    }

    @GetMapping("/userId/{id}")
    public List<OcorrenciaEntity> listAll(@PathVariable("id") Integer id){
        return repository.findAllByUserId(id);
    }


    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") Integer id){
        Object response = service.getOne(id);
        return ResponseFactory.okOrNotFound(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Integer id){
        return ResponseFactory.ok(service.delete(id),"Ocorrencia excluída com sucesso");
    }
}
