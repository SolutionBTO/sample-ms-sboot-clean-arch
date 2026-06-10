package br.com.sample.solutionbto.entrypoint.controller.mapper;

import br.com.sample.solutionbto.common.enums.StatusErroCepEnum;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.openapi.model.DadosComplementaresDto;
import br.com.sample.solutionbto.openapi.model.EnderecoCompletoDto;
import br.com.sample.solutionbto.openapi.model.ErroCepNaoEncontradoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoCompletoDtoMapper {

    @Mapping(target = "dadoComplementares", source = ".", qualifiedByName = "mapDadosComplementares")
    @Mapping(target = "erroCepNaoEncontrado", source = ".", qualifiedByName = "mapErroCepNaoEncontrado")
    EnderecoCompletoDto map(EnderecoCompletoDomain enderecos);

    List<EnderecoCompletoDto> map(List<EnderecoCompletoDomain> listaEnderecos);

    @Named("mapDadosComplementares")
    default DadosComplementaresDto mapDadosComplementares(EnderecoCompletoDomain domain) {
        if (domain == null) {
            return null;
        }
        return new DadosComplementaresDto()
                .ibge(domain.getIbge())
                .ddd(domain.getDdd())
                .siafi(domain.getSiafi())
                .gia(domain.getGia());
    }

    @Named("mapErroCepNaoEncontrado")
    default ErroCepNaoEncontradoDto mapErroCepNaoEncontrado(EnderecoCompletoDomain domain) {
        if (domain == null || domain.getErro() == null) {
            return null;
        }
        return  domain.getErro() ? new ErroCepNaoEncontradoDto()
                .codigo(StatusErroCepEnum.CEP_NAO_ENCONTRADO.getCodigo())
                .descricao(StatusErroCepEnum.CEP_NAO_ENCONTRADO.getDescricao()) : null;
    }
}