package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Bairro;
import br.com.fiap.globalsolution.model.TipoDeficiencia;

import java.time.LocalDate;

public record PessoaResponse(
        Long idPessoa,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        Bairro bairro,
        String cidade,
        String estado,
        String profissao,
        TipoDeficiencia pcd,
        String senha,
        Long idAbrigo
) {
}
