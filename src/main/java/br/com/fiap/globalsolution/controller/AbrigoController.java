package br.com.fiap.globalsolution.controller;

import br.com.fiap.globalsolution.dto.AbrigoRequest;
import br.com.fiap.globalsolution.dto.AbrigoResponse;
import br.com.fiap.globalsolution.model.Abrigo;
import br.com.fiap.globalsolution.service.AbrigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/abrigos")
@Tag(name = "api-abrigos")
public class AbrigoController {
    @Autowired
    private AbrigoService abrigoService;

    @Operation(summary = "Cria um novo abrigo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Abrigo criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<AbrigoResponse> createAbrigo(@Valid @RequestBody AbrigoRequest abrigo) {
        return new ResponseEntity<>(abrigoService.save(abrigo), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os abrigos")
    @GetMapping
    public ResponseEntity<Page<AbrigoResponse>> readAbrigos(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest
                .of(pageNumber, 2, Sort.by("bairro").ascending());
        return new ResponseEntity<>(abrigoService.findAll(pageable), HttpStatus.OK);
    }

    // @PathVariable localhost:8080/abrigos/1
    // @RequestParam localhost:8080/abrigos/?id=1
    @Operation(summary = "Retorna um abrigo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AbrigoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum abrigo encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<AbrigoResponse> readAbrigo(@PathVariable Long id) {
        AbrigoResponse abrigo = abrigoService.findById(id);
        if (abrigo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(abrigo,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um abrigo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Abrigo atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum abrigo encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<AbrigoResponse> updateAbrigo(@PathVariable Long id,
                                                               @RequestBody AbrigoRequest abrigoRequest) {
        AbrigoResponse abrigo = abrigoService.update(abrigoRequest, id);
        if (abrigo == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(abrigo,HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um abrigo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo excluído com sucesso",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum abrigo encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbrigo(@PathVariable Long id) {
        boolean salvo = abrigoService.delete(id);
        if (!salvo) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
