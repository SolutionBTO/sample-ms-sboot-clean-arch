package br.com.sample.solutionbto.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoCompletoDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cep;

    private String logradouro;

    private String complemento;

    private String unidade;

    private String bairro;

    private String localidade;

    private String uf;

    private String estado;

    private String regiao;

    private String ibge;

    private String gia;

    private String ddd;

    private String siafi;

    private Boolean erro;

}
