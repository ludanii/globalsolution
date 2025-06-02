package br.com.fiap.globalsolution.service;

import br.com.fiap.globalsolution.dto.PessoaRequest;
import br.com.fiap.globalsolution.dto.PessoaResponse;
import br.com.fiap.globalsolution.mapper.PessoaMapper;
import br.com.fiap.globalsolution.model.Pessoa;
import br.com.fiap.globalsolution.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper = new PessoaMapper();

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaResponse save(PessoaRequest pessoaRequest) {
        return pessoaMapper.pessoaToResponse(pessoaRepository.save(pessoaMapper.requestToPessoa(pessoaRequest)));
    }

    public Page<PessoaResponse> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable).map(pessoaMapper::pessoaToResponse);
    }

    public Pessoa findPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElse(null);
    }

    public PessoaResponse findById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(pessoaMapper::pessoaToResponse).orElse(null);
    }

    public PessoaResponse update(PessoaRequest pessoaRequest, Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            pessoa.setNome(pessoaRequest.nome());
            pessoa.setCpf(pessoaRequest.cpf());
            pessoa.setDataNascimento(pessoaRequest.dataNascimento());
            pessoa.setBairro(pessoaRequest.bairro());
            pessoa.setProfissao(pessoaRequest.profissao());
            pessoa.setPcd(pessoaRequest.pcd());

            Pessoa pessoaUpdate = pessoaRepository.save(pessoa);
            return pessoaMapper.pessoaToResponse(pessoaUpdate);
        }

        return null;
    }

    public boolean delete(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            pessoaRepository.delete(pessoa.get());
            return true;
        }
        return false;
    }
}
