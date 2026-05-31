package br.com.sample.solutionbto.dataprovider.impl;

import br.com.sample.solutionbto.core.dataprovider.ConsultaViaCep;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.dataprovider.mapper.EnderecoCompletoDomainMapper;
import br.com.sample.solutionbto.dataprovider.integration.ViaCepFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ConsultaViaCepImpl implements ConsultaViaCep {

    private final ViaCepFeign viaCepFeign;
    private final EnderecoCompletoDomainMapper mapper;

    @Cacheable(value = "consultaCepCache", key = "#cep")
    @Override
    public EnderecoCompletoDomain consultaCep(String cep) {
        var enderecoCompletoDto = viaCepFeign.consultaViaCep(cep);
        return mapper.map(enderecoCompletoDto.getBody());
    }

    @Cacheable(value = "pesquisaCepPorEnderecoCache", key = "{#uf, #localidade, #logradouro}")
    @Override
    public List<EnderecoCompletoDomain> pesquisaCepPorEndereco(String uf, String localidade, String logradouro) {
        var enderecoCompletosDto = viaCepFeign.pesquisaViaCepPorEndereco(
                uf,
                sanetizarTexto(localidade),
                sanetizarTexto(logradouro));

        return mapper.map(enderecoCompletosDto.getBody());
    }

    public static String sanetizarTexto(String texto) {
        if (texto == null) {
            return null;
        }
        // 1. Normaliza o texto para decompor os caracteres e seus acentos (NFD)
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // 2. Remove todos os caracteres que não estão na faixa ASCII (remove os acentos)
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String semAcentos = pattern.matcher(normalizado).replaceAll("");
        // 3. Substitui qualquer caractere especial restante (e.g., pontos, traços, @) por vazio
        // Apenas letras (a-z, A-Z), números (0-9) e espaços são permitidos.
        return semAcentos.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s", "%20");
    }
}
