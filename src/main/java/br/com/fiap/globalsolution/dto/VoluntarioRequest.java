package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Funcao;
import jakarta.validation.constraints.NotNull;

public record VoluntarioRequest(
        @NotNull(message = "O ID da pessoa é obrigatório.") Long idPessoa,
        @NotNull(message = "A função é obrigatória.") Funcao funcao,
        Long idAbrigo
) {
}

