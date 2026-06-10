package br.com.sample.solutionbto.core.usecase.exception;

import br.com.sample.solutionbto.common.enums.StatusErroCepEnum;
import lombok.Getter;

@Getter
public class CepInvalidoException extends RuntimeException {

    public CepInvalidoException(String cep){
        super(String.format(StatusErroCepEnum.CEP_INVALIDO.getDescricao(), cep));
    }
}
