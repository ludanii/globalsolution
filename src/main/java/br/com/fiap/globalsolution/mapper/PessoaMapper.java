package br.com.fiap.globalsolution.mapper;

import br.com.fiap.globalsolution.dto.PessoaRequest;
import br.com.fiap.globalsolution.dto.PessoaResponse;
import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.model.Pessoa;

public class PessoaMapper {
    public Pessoa requestToPessoa(PessoaRequest pessoaRequest) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaRequest.nome());
        pessoa.setCpf(pessoaRequest.cpf());
        pessoa.setDataNascimento(pessoaRequest.dataNascimento());
        pessoa.setBairro(pessoaRequest.bairro());
        pessoa.setProfissao(pessoaRequest.profissao());
        pessoa.setPcd(pessoaRequest.pcd());
        pessoa.setSenha(pessoaRequest.senha());

        if (pessoaRequest.idAbrigo() != null) {
            Abrigo abrigo = new Abrigo();
            abrigo.setIdAbrigo(pessoaRequest.idAbrigo());
            pessoa.setAbrigo(abrigo);
        }


        return pessoa;
    }

    public PessoaResponse pessoaToResponse(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.getIdPessoa(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getBairro(),
                pessoa.getCidade(),
                pessoa.getEstado(),
                pessoa.getProfissao(),
                pessoa.getPcd(),
                pessoa.getSenha(),
                pessoa.getAbrigo() != null ? pessoa.getAbrigo().getIdAbrigo() : null);

    }
}
