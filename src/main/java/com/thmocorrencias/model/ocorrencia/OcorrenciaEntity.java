package com.thmocorrencias.model.ocorrencia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thmocorrencias.enums.Classificacao;
import com.thmocorrencias.enums.Intensidade;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_ocorrencia")
@Data
public class OcorrenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Setter(AccessLevel.NONE)
    private Integer id;


    @JoinColumn(name = "user_id")
    private Integer userId;

    private LocalDateTime data;

    private String endereco;

    @Enumerated(EnumType.STRING)
    private Intensidade intensidade;
    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

}
