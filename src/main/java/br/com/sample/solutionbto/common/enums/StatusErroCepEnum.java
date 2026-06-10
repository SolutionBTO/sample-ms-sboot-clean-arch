package br.com.sample.solutionbto.common.enums;

import lombok.Getter;

@Getter
public enum StatusErroCepEnum {

    CEP_NAO_ENCONTRADO(1 , "CEP não encontrado!"),
    CEP_INVALIDO(2, "CEP %s está inválido!");

    private Integer codigo;
    private String descricao;

    StatusErroCepEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
