package com.thmocorrencias.infra.security.jwt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class JwtObject {
    private String subject; //login do usuario
    private LocalDateTime issuedAt; //data de criação do token
    private LocalDateTime expiration; // data de expiração do token
    private List<String> roles; //perfis de acesso

    private Integer id;

    private String nome;

    private static JwtObject build = null;

    public static JwtObject builder() {
        build = new JwtObject();
        return build;
    }

    public Integer getId() {
        return id;
    }

    public JwtObject setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public JwtObject setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public JwtObject subject(String subject) {
        this.subject = subject;
        return this;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public JwtObject issuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public JwtObject issuedAt() {
        this.issuedAt = LocalDateTime.now();
        return this;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public JwtObject expirationHours(long hours) {
        this.expiration = this.issuedAt.plusHours(hours);
        return this;
    }

    public JwtObject expiration(LocalDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public JwtObject roles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public JwtObject roles(String... roles) {
        this.roles = Arrays.asList(roles);
        return this;
    }
}
