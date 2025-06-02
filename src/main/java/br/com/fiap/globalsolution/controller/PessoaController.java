package br.com.fiap.globalsolution.controller;


import br.com.fiap.globalsolution.dto.PessoaRequest;
import br.com.fiap.globalsolution.dto.PessoaResponse;
import br.com.fiap.globalsolution.model.Pessoa;
import br.com.fiap.globalsolution.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/pessoas")
@Tag(name = "api-pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Cria uma nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos!",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PessoaResponse> createPessoa(@Valid @RequestBody PessoaRequest pessoa) {
        return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as pessoas")
    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> readPessoas(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest
                .of(pageNumber, 2, Sort.by("nome").ascending());
        return new ResponseEntity<>(pessoaService.findAll(pageable), HttpStatus.OK);
    }

    // @PathVariable localhost:8080/pessoas/1
    // @RequestParam localhost:8080/pessoas/?id=1
    @Operation(summary = "Retorna uma pessoa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PessoaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> readPessoa(@PathVariable Long id) {
        PessoaResponse pessoa = pessoaService.findById(id);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoa,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma pessoa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa atualizada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "400", description = "Nenhuma pessoa encontrada para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> updatePessoa(@PathVariable Long id,
                                                         @RequestBody PessoaRequest pessoaRequest) {
        PessoaResponse pessoa = pessoaService.update(pessoaRequest, id);
        if (pessoa == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pessoa,HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui uma pessoa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa excluída com sucesso",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhuma pessoa encontrada para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        boolean salvo = pessoaService.delete(id);
        if (!salvo) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
