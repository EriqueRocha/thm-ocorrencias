package com.thmocorrencias.infra.security;

import lombok.Data;

@Data
public class Session {

    private String login;
    private Integer id;
    private String nome;

    private String token;
}
