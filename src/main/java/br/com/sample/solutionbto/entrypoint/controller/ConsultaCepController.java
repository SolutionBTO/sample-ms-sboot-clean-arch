package br.com.sample.solutionbto.entrypoint.controller;

import br.com.sample.solutionbto.core.usecase.ConsultaCepUsecase;
import br.com.sample.solutionbto.entrypoint.controller.mapper.EnderecoCompletoDtoMapper;
import br.com.sample.solutionbto.openapi.api.ConsultaCepApi;
import br.com.sample.solutionbto.openapi.model.EnderecoCompleto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ConsultaCepController implements ConsultaCepApi {

    private final ConsultaCepUsecase usecase;
    private final EnderecoCompletoDtoMapper mapper;

    public ConsultaCepController(ConsultaCepUsecase usecase, EnderecoCompletoDtoMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<EnderecoCompleto> consultaCep(String cep) {
        var endereco = usecase.consultaCep(cep);

        return endereco.getErro() != null && endereco.getErro() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(mapper.map(endereco));
    }

    @Override
    public ResponseEntity<List<EnderecoCompleto>> pesquisaCepPorEndereco(String uf, String localidade, String logradouro) {
        var enderecos = usecase.pesquisaCepPorEndereco(uf, localidade, logradouro);
        return enderecos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(mapper.map(enderecos));
    }
}
