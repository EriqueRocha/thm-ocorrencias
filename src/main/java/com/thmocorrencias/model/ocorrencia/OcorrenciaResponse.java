package com.thmocorrencias.model.ocorrencia;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OcorrenciaResponse {

    @Schema(description="ID gerado pelo banco de dados", example = "1")
    private Integer id;
}
