package com.thmocorrencias.infra.handler.exception;

public class BusinessException extends  RuntimeException{
    private String errorCode;
    private String suggestion;
    public BusinessException(String message, String errorCode, String suggestion,  Object ... params ){
        super(String.format(message, params));
        this.errorCode = errorCode;
        this.suggestion = suggestion;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public String getSuggestion() {
        return suggestion;
    }
}
