package com.thmocorrencias.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioRequest {

    @Schema(description="Nome do usuário", example = "JOÃO DA SILVA")
    private String nome;

    @Schema(description="Senha", example = "Jwt@123")
    private String senha;

    @Schema(description="Email do usuário", example = "joaosilva@thm.com")
    private String login;
}
