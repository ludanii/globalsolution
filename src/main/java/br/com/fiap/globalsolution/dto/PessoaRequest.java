package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.Bairro;
import br.com.fiap.globalsolution.model.TipoDeficiencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaRequest(
        @NotBlank(message = "O nome é obrigatório.") String nome,
        @CPF(message = "CPF fora do formato correto.") String cpf,
        @NotNull(message = "A data de nascimento é obrigatória.") LocalDate dataNascimento,
        @NotNull(message = "O bairro é obrigatório.") Bairro bairro,
        @NotBlank(message = "A profissão é obrigatória.") String profissao,
        @NotNull(message = "O tipo de deficiência é obrigatório.") TipoDeficiencia pcd,
        @NotBlank(message = "A senha é obrigatória.") String senha
        ) {
}
