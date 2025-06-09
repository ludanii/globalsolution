package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Bairro;

import java.util.List;

public record  AbrigoResponse(
        Long idAbrigo,
        Bairro bairro,
        String cep,
        Integer qtdVagas,
        List<Long> idsVoluntarios,
        List<Long> idsPessoas
) {
}
