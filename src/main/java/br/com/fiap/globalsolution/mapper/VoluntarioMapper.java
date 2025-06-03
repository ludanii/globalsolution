package br.com.fiap.globalsolution.mapper;

import br.com.fiap.globalsolution.dto.VoluntarioRequest;
import br.com.fiap.globalsolution.dto.VoluntarioResponse;
import br.com.fiap.globalsolution.model.Voluntario;

public class VoluntarioMapper {
    public Voluntario requestToVoluntario(VoluntarioRequest voluntarioRequest) {
        Voluntario voluntario = new Voluntario();
        voluntario.setPessoa(voluntarioRequest.pessoa());
        voluntario.setFuncao(voluntarioRequest.funcao());
        voluntario.setAbrigo(voluntarioRequest.abrigo());
        return voluntario;
    }

    public VoluntarioResponse voluntarioToResponse(Voluntario voluntario) {
        return new VoluntarioResponse(
                voluntario.getIdVoluntario(),
                voluntario.getPessoa(),
                voluntario.getFuncao(),
                voluntario.getAbrigo());
    }
}
