package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Bairro;
import br.com.fiap.globalsolution.model.Gravidade;
import br.com.fiap.globalsolution.model.Ocorrencia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AvisoRequest(
        @NotNull(message = "A ocorrência é obrigatória.") Ocorrencia ocorrencia,
        @NotNull(message = "O nível de gravidade é obrigatório.") Gravidade gravidade,
        @NotNull(message = "O bairro é obrigatório.") Bairro bairro,
        @NotNull(message = "A data e hora é obrigatório.") LocalDateTime dataHora
) {
}
