package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.model.Funcao;
import br.com.fiap.globalsolution.model.Pessoa;
import jakarta.validation.constraints.NotNull;

public record VoluntarioRequest(
        @NotNull(message = "O ID é obrigatório.") Pessoa pessoa,
        @NotNull(message = "A função é obrigatória.") Funcao funcao,
        @NotNull(message = "O abrigo é obrigatório.") Abrigo abrigo
) {
}
