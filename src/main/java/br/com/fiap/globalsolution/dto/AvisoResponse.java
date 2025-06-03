package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Bairro;
import br.com.fiap.globalsolution.model.Gravidade;
import br.com.fiap.globalsolution.model.Ocorrencia;

import java.time.LocalDateTime;

public record AvisoResponse(
        Long idAviso,
        Ocorrencia ocorrencia,
        Gravidade gravidade,
        Bairro bairro,
        LocalDateTime dataHora
) {
}
