package com.thmocorrencias.model.ocorrencia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thmocorrencias.enums.Classificacao;
import com.thmocorrencias.enums.Intensidade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OcorrenciaRequest {



    @Schema(description="Id do usuário a quem pertence a ocorrência", example = "1")
    private Integer userId;

    @Schema(description="Data e hora")
    @JsonIgnore
    private LocalDateTime data;

    @Schema(description= "Endereço da ocorrênca", example = "AV. BARÃO DO RIO BRANCO")
    private String endereco;


    @Schema(description="Intensidade do alagamento", example = "LEVE")
    private Intensidade intensidade;


    @Schema(description="Classifique o alagamento", example = "LOCALIZADO")
    private Classificacao classificacao;



}
