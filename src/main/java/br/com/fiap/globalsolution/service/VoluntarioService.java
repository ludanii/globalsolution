package br.com.fiap.globalsolution.service;

import br.com.fiap.globalsolution.dto.VoluntarioRequest;
import br.com.fiap.globalsolution.dto.VoluntarioResponse;
import br.com.fiap.globalsolution.mapper.VoluntarioMapper;
import br.com.fiap.globalsolution.model.Voluntario;
import br.com.fiap.globalsolution.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoluntarioService {
    private final VoluntarioRepository voluntarioRepository;
    private final VoluntarioMapper voluntarioMapper = new VoluntarioMapper();

    @Autowired
    public VoluntarioService(VoluntarioRepository voluntarioRepository) {
        this.voluntarioRepository = voluntarioRepository;
    }

    public VoluntarioResponse save(VoluntarioRequest voluntarioRequest) {
        return voluntarioMapper.voluntarioToResponse(voluntarioRepository.save(voluntarioMapper.requestToVoluntario(voluntarioRequest)));
    }

    public Page<VoluntarioResponse> findAll(Pageable pageable) {
        return voluntarioRepository.findAll(pageable).map(voluntarioMapper::voluntarioToResponse);
    }

    public Voluntario findVoluntarioById(Long id) {
        Optional<Voluntario> voluntario = voluntarioRepository.findById(id);
        return voluntario.orElse(null);
    }

    public VoluntarioResponse findById(Long id) {
        Optional<Voluntario> voluntario = voluntarioRepository.findById(id);
        return voluntario.map(voluntarioMapper::voluntarioToResponse).orElse(null);
    }

    public VoluntarioResponse update(VoluntarioRequest voluntarioRequest, Long id) {
        Optional<Voluntario> voluntario = voluntarioRepository.findById(id);
        if (voluntario.isPresent()) {
            Voluntario voluntarioSalvo = voluntarioRepository.save(voluntario.get());
            return voluntarioMapper.voluntarioToResponse(voluntarioSalvo);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Voluntario> voluntario = voluntarioRepository.findById(id);
        if (voluntario.isPresent()) {
            voluntarioRepository.delete(voluntario.get());
            return true;
        }
        return false;
    }
}
