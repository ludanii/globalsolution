package br.com.fiap.globalsolution.service;

import br.com.fiap.globalsolution.dto.AbrigoRequest;
import br.com.fiap.globalsolution.dto.AbrigoResponse;
import br.com.fiap.globalsolution.mapper.AbrigoMapper;
import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbrigoService {
    private final AbrigoRepository abrigoRepository;
    private final AbrigoMapper abrigoMapper = new AbrigoMapper();

    @Autowired
    public AbrigoService(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    public AbrigoResponse save(AbrigoRequest abrigoRequest) {
        return abrigoMapper.abrigoToResponse(abrigoRepository.save(abrigoMapper.requestToAbrigo(abrigoRequest)));
    }

    public Page<AbrigoResponse> findAll(Pageable pageable) {
        return abrigoRepository.findAll(pageable).map(abrigoMapper::abrigoToResponse);
    }

    public Abrigo findAbrigoById(Long id) {
        Optional<Abrigo> abrigo = abrigoRepository.findById(id);
        return abrigo.orElse(null);
    }

    public AbrigoResponse findById(Long id) {
        Optional<Abrigo> abrigo = abrigoRepository.findById(id);
        return abrigo.map(abrigoMapper::abrigoToResponse).orElse(null);
    }

    public AbrigoResponse update(AbrigoRequest abrigoRequest, Long id) {
        Optional<Abrigo> abrigoOptional = abrigoRepository.findById(id);
        if (abrigoOptional.isPresent()) {
            Abrigo abrigo = abrigoOptional.get();
            abrigo.setBairro(abrigoRequest.bairro());
            abrigo.setCep(abrigoRequest.cep());
            abrigo.setQtdVagas(abrigoRequest.qtdVagas());

            Abrigo abrigoUpdate = abrigoRepository.save(abrigo);
            return abrigoMapper.abrigoToResponse(abrigoUpdate);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Abrigo> abrigo = abrigoRepository.findById(id);
        if (abrigo.isPresent()) {
            abrigoRepository.delete(abrigo.get());
            return true;
        }
        return false;
    }
}