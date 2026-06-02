package br.com.sample.solutionbto.core.usecase.exception;

import lombok.Getter;

@Getter
public class ConsultaSemResultadoException extends RuntimeException {

    public ConsultaSemResultadoException(){
        super("Consulta sem resultado");
    }
}
