package br.com.fiap.globalsolution.mapper;

import br.com.fiap.globalsolution.dto.AvisoRequest;
import br.com.fiap.globalsolution.dto.AvisoResponse;
import br.com.fiap.globalsolution.model.Aviso;

public class AvisoMapper {
    public Aviso requestToAviso(AvisoRequest avisoRequest) {
        Aviso aviso = new Aviso();
        aviso.setOcorrencia(avisoRequest.ocorrencia());
        aviso.setGravidade(avisoRequest.gravidade());
        aviso.setBairro(avisoRequest.bairro());
        aviso.setDataHora(avisoRequest.dataHora());
        return aviso;
    }

    public AvisoResponse avisoToResponse(Aviso aviso) {
        return new AvisoResponse(
                aviso.getIdAviso(),
                aviso.getOcorrencia(),
                aviso.getGravidade(),
                aviso.getBairro(),
                aviso.getDataHora());
    }
}
