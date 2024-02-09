package com.peakyblinders.peakyblindersfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {



    INVALID_DATA("/dados-invalidos", "Dados inválidos"),
    SYSTEM_ERROR("/erro-de-sistema", "Erro de sistema"),
    INVALID_PARAMETER("/parametro-invalido","Parametro na url está invalido"),
    MESAGE_INCOMPREHENSIBLE("/menssagem-incompreensivel","Mensagem incompreensível"),

    RESOURCE_NOT_FOUND("/recurso-nao-encontrado","Recurso não encontrado"),
    ENTITY_IN_USER("/entidade-em-uso","Entidade em uso"),
    ERROR_BUSINESS("/erro-negocio" ,"Violação de regra de negócio");
    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://peakyblindersfoodapi.com.br" + path;
        this.title = title;
    }
}
