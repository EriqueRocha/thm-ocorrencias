package com.thmocorrencias.service;

import com.thmocorrencias.infra.handler.exception.CampoObrigatorioException;
import com.thmocorrencias.infra.handler.exception.RegistroNaoLocalizadoException;
import com.thmocorrencias.model.ocorrencia.OcorrenciaEntity;
import com.thmocorrencias.model.ocorrencia.OcorrenciaRequest;
import com.thmocorrencias.model.ocorrencia.OcorrenciaResponse;
import com.thmocorrencias.model.ocorrencia.OcorrenciaUpdate;
import com.thmocorrencias.repository.OcorrenciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository repository;


    public Object save(OcorrenciaRequest request) {

        OcorrenciaEntity ocorrencia = new OcorrenciaEntity();

        ocorrencia.setUserId(request.getUserId());
        ocorrencia.setData(LocalDateTime.now());
        ocorrencia.setEndereco(request.getEndereco());
        ocorrencia.setIntensidade(request.getIntensidade());
        ocorrencia.setClassificacao(request.getClassificacao());

        repository.save(ocorrencia);

        return ocorrencia;
    }

    public Object update(OcorrenciaUpdate request) {

        OcorrenciaEntity ocorrencia = new OcorrenciaEntity();

        ocorrencia.setId(request.getId());
        ocorrencia.setUserId(request.getUserId());
        ocorrencia.setData(LocalDateTime.now());
        ocorrencia.setEndereco(request.getEndereco());
        ocorrencia.setIntensidade(request.getIntensidade());
        ocorrencia.setClassificacao(request.getClassificacao());

        repository.save(ocorrencia);
        return ocorrencia;
    }

    public List<OcorrenciaResponse> listAll() {
        List<OcorrenciaEntity> entitys = repository.findAll();
        List<OcorrenciaResponse> list = new ArrayList<>();

        for(OcorrenciaEntity entity:entitys){
            OcorrenciaResponse response = new OcorrenciaResponse();
            BeanUtils.copyProperties(entity,response);
            list.add(response);
        }

        return list;
    }

    public Object getOne(Integer id) {
        OcorrenciaEntity entity = findById(id);
        OcorrenciaResponse response = new OcorrenciaResponse();
        //este método copia todos os atributos da entity para o response;
        BeanUtils.copyProperties(entity,response);
        return response;
    }

    public Object delete(Integer id) {
        OcorrenciaEntity entity = findById(id);
        repository.delete(entity);
        return true;
    }

    private OcorrenciaEntity findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException("Ocorrência", "ID"));
    }

    @Transactional
    public Integer persist(Integer id, OcorrenciaRequest request){

        if(request.getUserId()==null || request.getUserId().describeConstable().isEmpty())
            throw new CampoObrigatorioException("Id");

        if(request.getEndereco()==null || request.getEndereco().isEmpty())
            throw new CampoObrigatorioException("Local");

        if(request.getIntensidade()==null || request.getIntensidade().describeConstable().isEmpty())
            throw new CampoObrigatorioException("Intensidade");

        if(request.getClassificacao()==null || request.getClassificacao().describeConstable().isEmpty())
            throw new CampoObrigatorioException("Classificação");

        OcorrenciaEntity entity = null;
        if(id!=null){
            entity = this.findById(id);
        }else
            entity = new OcorrenciaEntity();

        BeanUtils.copyProperties(request,entity);

        return repository.save(entity).getId();
    }
}
