package com.thmocorrencias.model.usuario;

import com.thmocorrencias.model.ocorrencia.OcorrenciaDTO;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {
    private Integer id;

    private String nome;

    private String login;

    private String role;

    private int numOcorrencias;

    private List<OcorrenciaDTO> ocorrencias;



}
