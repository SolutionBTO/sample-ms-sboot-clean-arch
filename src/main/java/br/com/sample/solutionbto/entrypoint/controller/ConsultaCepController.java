package br.com.sample.solutionbto.entrypoint.controller;

import br.com.sample.solutionbto.core.usecase.ConsultaCepUsecase;
import br.com.sample.solutionbto.entrypoint.controller.mapper.EnderecoCompletoDtoMapper;
import br.com.sample.solutionbto.openapi.api.ConsultaCepApi;
import br.com.sample.solutionbto.openapi.model.EnderecoCompletoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class ConsultaCepController implements ConsultaCepApi {

    private final ConsultaCepUsecase usecase;
    private final EnderecoCompletoDtoMapper mapper;

    @Override
    public ResponseEntity<EnderecoCompletoDto> consultaCep(String cep) {
        var endereco = usecase.consultaCep(cep);

        return Optional.ofNullable(endereco)
                            .filter(e -> e.getErro() == null || e.getErro() == Boolean.FALSE)
                            .map(mapper::map)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<List<EnderecoCompletoDto>> pesquisaCepPorEndereco(String uf, String localidade, String logradouro) {
        var enderecos = usecase.pesquisaCepPorEndereco(uf, localidade, logradouro);
        return enderecos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(mapper.map(enderecos));
    }
}
