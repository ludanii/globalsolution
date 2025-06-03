package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.model.Funcao;
import br.com.fiap.globalsolution.model.Pessoa;

public record VoluntarioResponse(
        Long idVoluntario,
        Pessoa pessoa,
        Funcao funcao,
        Abrigo abrigo
) {
}
