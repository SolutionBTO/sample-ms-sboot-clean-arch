package br.com.sample.solutionbto.common;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class CacheManagerConstants {

    public static final String CACHE_MANAGER_CEP = "CACHE_MANAGER_CEP";
    public static final String CACHE_NAME_CONSULTA_CEP = "CACHE_CONSULTA_CEP";
    public static final String CACHE_NAME_PESQUISA_CEP = "CACHE_PESQUISA_CEP";
}
