package br.com.fiap.globalsolution.controller;

import br.com.fiap.globalsolution.dto.AvisoRequest;
import br.com.fiap.globalsolution.dto.AvisoResponse;
import br.com.fiap.globalsolution.model.Aviso;
import br.com.fiap.globalsolution.service.AvisoService;
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
@RequestMapping(value = "/avisos")
@Tag(name = "api-avisos")
public class AvisoController {
    @Autowired
    private AvisoService avisoService;

    @Operation(summary = "Cria um novo aviso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aviso criado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Aviso.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos!",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<AvisoResponse> createAviso(@Valid @RequestBody AvisoRequest aviso) {
        return new ResponseEntity<>(avisoService.save(aviso), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os avisos")
    @GetMapping
    public ResponseEntity<Page<AvisoResponse>> readAvisos(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest
                .of(pageNumber, 2, Sort.by("ocorrencia").ascending());
        return new ResponseEntity<>(avisoService.findAll(pageable), HttpStatus.OK);
    }

    // @PathVariable localhost:8080/avisos/1
    // @RequestParam localhost:8080/avisos/?id=1
    @Operation(summary = "Retorna um aviso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aviso encontrado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AvisoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum aviso encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<AvisoResponse> readAviso(@PathVariable Long id) {
        AvisoResponse aviso = avisoService.findById(id);
        if (aviso == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aviso,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um aviso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aviso atualizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Aviso.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum aviso encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<AvisoResponse> updateAviso(@PathVariable Long id,
                                                               @RequestBody AvisoRequest avisoRequest) {
        AvisoResponse aviso = avisoService.update(avisoRequest, id);
        if (aviso == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(aviso,HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um aviso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aviso excluído com sucesso!",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum aviso encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAviso(@PathVariable Long id) {
        boolean salvo = avisoService.delete(id);
        if (!salvo) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}