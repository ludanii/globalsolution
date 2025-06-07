package br.com.fiap.globalsolution.service;

import br.com.fiap.globalsolution.dto.AvisoRequest;
import br.com.fiap.globalsolution.dto.AvisoResponse;
import br.com.fiap.globalsolution.mapper.AvisoMapper;
import br.com.fiap.globalsolution.model.Aviso;
import br.com.fiap.globalsolution.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisoService {
    private final AvisoRepository avisoRepository;
    private final AvisoMapper avisoMapper = new AvisoMapper();

    @Autowired
    public AvisoService(AvisoRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    public AvisoResponse save(AvisoRequest avisoRequest) {
        return avisoMapper.avisoToResponse(avisoRepository.save(avisoMapper.requestToAviso(avisoRequest)));
    }

    public Page<AvisoResponse> findAll(Pageable pageable) {
        return avisoRepository.findAll(pageable).map(avisoMapper::avisoToResponse);
    }

    public List<Aviso> findAll() {
        return avisoRepository.findAll();
    }

    public Aviso findAvisoById(Long id) {
        Optional<Aviso> aviso = avisoRepository.findById(id);
        return aviso.orElse(null);
    }

    public AvisoResponse findById(Long id) {
        Optional<Aviso> aviso = avisoRepository.findById(id);
        return aviso.map(avisoMapper::avisoToResponse).orElse(null);
    }

    public AvisoResponse update(AvisoRequest avisoRequest, Long id) {
        Optional<Aviso> avisoOptional = avisoRepository.findById(id);
        if (avisoOptional.isPresent()) {
            Aviso aviso = avisoOptional.get();
            aviso.setOcorrencia(avisoRequest.ocorrencia());
            aviso.setGravidade(avisoRequest.gravidade());
            aviso.setBairro(avisoRequest.bairro());
            aviso.setDataHora(avisoRequest.dataHora());

            Aviso avisoUpdate = avisoRepository.save(aviso);
            return avisoMapper.avisoToResponse(avisoUpdate);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Aviso> aviso = avisoRepository.findById(id);
        if (aviso.isPresent()) {
            avisoRepository.delete(aviso.get());
            return true;
        }
        return false;
    }
}
