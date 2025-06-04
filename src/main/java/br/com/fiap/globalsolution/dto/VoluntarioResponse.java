package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Funcao;

public record VoluntarioResponse(
        Long idVoluntario,
        Long idPessoa,
        String nomePessoa,
        Funcao funcao,
        Long idAbrigo
) {
}