package com.thmocorrencias.infra.handler.exception;

public class CampoObrigatorioException extends BusinessException{
    public CampoObrigatorioException(String nome) {
        super("Campo obrigatório: %s","101","Preencha o campo obrigatório",nome);
    }
}
