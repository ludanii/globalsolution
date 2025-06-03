package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Bairro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AbrigoRequest(
        @NotNull(message = "O bairro é obrigatório.") Bairro bairro,
        @NotBlank(message = "O CEP é obrigatório.")String cep,
        @NotNull(message = "A quantidade de vagas é obrigatório.") Integer qtdVagas
) {
}
