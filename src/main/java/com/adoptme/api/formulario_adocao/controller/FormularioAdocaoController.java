package com.adoptme.api.formulario_adocao.controller;

import com.adoptme.api.formulario_adocao.dto.FormularioAdocaoRequestDto;
import com.adoptme.api.formulario_adocao.dto.FormularioAdocaoResponseDto;
import com.adoptme.api.formulario_adocao.dto.RejeicaoAdocaoDto;
import com.adoptme.api.formulario_adocao.service.FormularioAdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Adoções", description = "Endpoints para gerenciamento e controle do fluxo de adoção de animais")
@RequestMapping("/formulario-adocao")
@AllArgsConstructor
@RestController
public class FormularioAdocaoController {

    private final FormularioAdocaoService service;

    @PostMapping
    @Operation(summary = "Registra uma nova solicitação de adoção", description = "Valida os dados de entrada e cria um formulário com o status inicial PENDENTE se o animal estiver disponível.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitação de adoção aberta com sucesso"),
            @ApiResponse(responseCode = "400", description = "Animal já adotado ou dados da requisição inválidos")
    })
    public ResponseEntity<FormularioAdocaoResponseDto> criarSolicitacao(@RequestBody @Valid FormularioAdocaoRequestDto dto) {
        FormularioAdocaoResponseDto response = service.criarSolicitacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todas as solicitações de adoção", description = "Retorna o histórico de todas as solicitações de triagem cadastradas no banco de dados.")
    @ApiResponse(responseCode = "200", description = "Lista de formulários retornada com sucesso")
    public ResponseEntity<List<FormularioAdocaoResponseDto>> listarTodos() {
        List<FormularioAdocaoResponseDto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma solicitação de adoção pelo ID", description = "Retorna os detalhes detalhados de um formulário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Formulário não encontrado no sistema")
    })
    public ResponseEntity<FormularioAdocaoResponseDto> buscarPorId(@PathVariable Long id) {
        FormularioAdocaoResponseDto response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/aprovar")
    @Operation(summary = "Aprova uma solicitação de adoção", description = "Modifica o status do formulário para APROVADO e altera o estado do animal associado para adotado (bloqueado).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Adoção aprovada e animal bloqueado com sucesso"),
            @ApiResponse(responseCode = "400", description = "O animal já foi adotado por outro processo"),
            @ApiResponse(responseCode = "404", description = "ID do formulário não encontrado")
    })
    public ResponseEntity<Void> aprovarSolicitacao(@PathVariable Long id) {
        service.aprovarSolicitacao(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rejeitar")
    @Operation(summary = "Rejeita uma solicitação de adoção", description = "Modifica o status do formulário para REJEITADO e insere uma justificativa textual obrigatória.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Adoção rejeitada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou justificativa em branco"),
            @ApiResponse(responseCode = "404", description = "ID do formulário não encontrado")
    })
    public ResponseEntity<Void> rejeitarSolicitacao(@PathVariable Long id, @RequestBody @Valid RejeicaoAdocaoDto dto) {
        service.rejeitarSolicitacao(id, dto.justificativa());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove uma solicitação de adoção",
            description = "Exclui permanentemente um formulário de adoção pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Formulário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Formulário não encontrado")
    })
    public ResponseEntity<Void> deletarSolicitacao(@PathVariable Long id) {
        service.deletarSolicitacao(id);
        return ResponseEntity.noContent().build();
    }
}