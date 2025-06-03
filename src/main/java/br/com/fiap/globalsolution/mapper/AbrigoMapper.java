package br.com.fiap.globalsolution.mapper;

import br.com.fiap.globalsolution.dto.AbrigoRequest;
import br.com.fiap.globalsolution.dto.AbrigoResponse;
import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.model.Voluntario;

import java.util.List;
import java.util.stream.Collectors;

public class AbrigoMapper {
    public Abrigo requestToAbrigo(AbrigoRequest abrigoRequest) {
        Abrigo abrigo = new Abrigo();
        abrigo.setBairro(abrigoRequest.bairro());
        abrigo.setCep(abrigoRequest.cep());
        abrigo.setQtdVagas(abrigoRequest.qtdVagas());
        return abrigo;
    }

    public AbrigoResponse abrigoToResponse(Abrigo abrigo) {
        List<Long> idsVoluntarios = abrigo.getVoluntarios() != null
                ? abrigo.getVoluntarios().stream()
                .map(Voluntario::getIdVoluntario)
                .collect(Collectors.toList())
                : List.of();

        return new AbrigoResponse(
                abrigo.getIdAbrigo(),
                abrigo.getBairro(),
                abrigo.getCep(),
                abrigo.getQtdVagas(),
                idsVoluntarios
        );
    }
}