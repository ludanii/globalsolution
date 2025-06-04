package br.com.fiap.globalsolution.service;

import br.com.fiap.globalsolution.dto.VoluntarioRequest;
import br.com.fiap.globalsolution.dto.VoluntarioResponse;
import br.com.fiap.globalsolution.mapper.VoluntarioMapper;
import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.model.Pessoa;
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
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findById(id);
        if (voluntarioOptional.isPresent()) {
            Voluntario voluntario = voluntarioOptional.get();

            Pessoa pessoa = new Pessoa();
            pessoa.setIdPessoa(voluntarioRequest.idPessoa());
            voluntario.setPessoa(pessoa);

            voluntario.setFuncao(voluntarioRequest.funcao());

            Abrigo abrigo = new Abrigo();
            abrigo.setIdAbrigo(voluntarioRequest.idAbrigo());
            voluntario.setAbrigo(abrigo);

            Voluntario voluntarioUpdate = voluntarioRepository.save(voluntario);
            return voluntarioMapper.voluntarioToResponse(voluntarioUpdate);
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
