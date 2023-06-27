package com.thmocorrencias.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioUpdate {

    @Schema(description="Id do usuário", example = "1")
    private Integer id;

    @Schema(description="Nome do usuário", example = "JOÃO DA SILVA")
    private String nome;

    @Schema(description="Senha", example = "Jwt@123")
    private String senha;

    @Schema(description="Email do usuário", example = "joaosilva@thm.com")
    private String login;

    @Schema(description="Role do usuário", example = "USER")
    private String role;
}
