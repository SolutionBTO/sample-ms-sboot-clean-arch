package br.com.sample.solutionbto.core.usecase.exception;

public class PesquisaPorEnderecoIlegalArgsException extends IllegalArgumentException {

    public PesquisaPorEnderecoIlegalArgsException() {
        super("Necessário todos parâmetros: uf, localidade e logradouro.");
    }
}
