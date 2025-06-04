package br.com.fiap.globalsolution.mapper;

import br.com.fiap.globalsolution.dto.VoluntarioRequest;
import br.com.fiap.globalsolution.dto.VoluntarioResponse;
import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.model.Pessoa;
import br.com.fiap.globalsolution.model.Voluntario;

public class VoluntarioMapper {
    public Voluntario requestToVoluntario(VoluntarioRequest voluntarioRequest) {
        Voluntario voluntario = new Voluntario();

        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(voluntarioRequest.idPessoa());

        Abrigo abrigo = new Abrigo();
        abrigo.setIdAbrigo(voluntarioRequest.idAbrigo());

        voluntario.setPessoa(pessoa);
        voluntario.setFuncao(voluntarioRequest.funcao());
        voluntario.setAbrigo(abrigo);
        return voluntario;
    }

    public VoluntarioResponse voluntarioToResponse(Voluntario voluntario) {
        return new VoluntarioResponse(
                voluntario.getIdVoluntario(),
                voluntario.getPessoa().getIdPessoa(),
                voluntario.getPessoa().getNome(),
                voluntario.getFuncao(),
                voluntario.getAbrigo().getIdAbrigo());
    }
}
