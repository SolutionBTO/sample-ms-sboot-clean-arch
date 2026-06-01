package br.com.sample.solutionbto.dataprovider.mapper;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.openapi.integration.model.EnderecoCompletoClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoCompletoDomainMapper {

    EnderecoCompletoDomain mapClientDtoToDomain(EnderecoCompletoClientDto dto);

    List<EnderecoCompletoDomain> mapListClientDtoToDomain(List<EnderecoCompletoClientDto> dtos);
}
