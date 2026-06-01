package br.com.sample.solutionbto.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.Normalizer;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class StringUtils {

    public static String sanetizarTexto(String texto){
        return sanetizarTexto(texto, false);
    }

    public static String sanetizarTexto(String texto, boolean isUrl) {
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

        if(isUrl)
            return semAcentos.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s", "%20");

        return semAcentos.replaceAll("[^a-zA-Z0-9 ]", "");
    }
}
