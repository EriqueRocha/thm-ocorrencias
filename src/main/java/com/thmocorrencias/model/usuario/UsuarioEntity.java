package com.thmocorrencias.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thmocorrencias.model.ocorrencia.OcorrenciaEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "tab_usuario")
@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Setter(AccessLevel.NONE)
    private Integer id;

    private String nome;

    @JsonIgnore
    private String senha;

    private String login; //email

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private List<OcorrenciaEntity> ocorrencia;

    private String role;

    private int numOcorrencias;
}
