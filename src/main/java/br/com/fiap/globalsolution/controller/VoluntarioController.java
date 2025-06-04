package br.com.fiap.globalsolution.controller;

import br.com.fiap.globalsolution.dto.VoluntarioRequest;
import br.com.fiap.globalsolution.dto.VoluntarioResponse;
import br.com.fiap.globalsolution.model.Voluntario;
import br.com.fiap.globalsolution.service.VoluntarioService;
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
@RequestMapping(value = "/voluntarios")
@Tag(name = "api-voluntarios")
public class VoluntarioController {
    @Autowired
    private VoluntarioService voluntarioService;

    @Operation(summary = "Cria um novo voluntário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voluntário criado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Voluntario.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos!",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<VoluntarioResponse> createVoluntario(@Valid @RequestBody VoluntarioRequest voluntario) {
        return new ResponseEntity<>(voluntarioService.save(voluntario), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os voluntários")
    @GetMapping
    public ResponseEntity<Page<VoluntarioResponse>> readVoluntarios(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest
                .of(pageNumber, 2, Sort.by("funcao").ascending());
        return new ResponseEntity<>(voluntarioService.findAll(pageable), HttpStatus.OK);
    }

    // @PathVariable localhost:8080/voluntarios/1
    // @RequestParam localhost:8080/voluntarios/?id=1
    @Operation(summary = "Retorna um voluntário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntário encontrado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VoluntarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum voluntário encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<VoluntarioResponse> readVoluntario(@PathVariable Long id) {
        VoluntarioResponse voluntario = voluntarioService.findById(id);
        if (voluntario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(voluntario,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um voluntário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voluntário atualizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Voluntario.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum voluntário encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<VoluntarioResponse> updateVoluntario(@PathVariable Long id,
                                                               @RequestBody VoluntarioRequest voluntarioRequest) {
        VoluntarioResponse voluntario = voluntarioService.update(voluntarioRequest, id);
        if (voluntario == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(voluntario,HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um voluntário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntário excluído com sucesso!",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum voluntário encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntario(@PathVariable Long id) {
        boolean salvo = voluntarioService.delete(id);
        if (!salvo) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}