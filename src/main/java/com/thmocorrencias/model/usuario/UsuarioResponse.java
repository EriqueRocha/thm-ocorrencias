package com.thmocorrencias.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioResponse {

    @Schema(description="ID gerado pelo banco de dados", example = "1")
    private Integer id;
}
