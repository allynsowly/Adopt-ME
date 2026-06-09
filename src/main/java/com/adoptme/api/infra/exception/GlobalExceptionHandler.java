package com.adoptme.api.infra.exception;

import com.adoptme.api.formulario_adocao.dto.ErroRespostaDto;
import com.adoptme.api.formulario_adocao.exececao.AnimalJaAdotadoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de recurso não encontrado (Lançados pelos orElseThrow do Service)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroRespostaDto> tratarErro404(EntityNotFoundException ex) {
        ErroRespostaDto erro = new ErroRespostaDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso Não Encontrado",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Trata a sua exceção específica de violação de regra de negócio no fluxo de adoção
    @ExceptionHandler(AnimalJaAdotadoException.class)
    public ResponseEntity<ErroRespostaDto> tratarErroAnimalJaAdotado(AnimalJaAdotadoException ex) {
        ErroRespostaDto erro = new ErroRespostaDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Regra de Negócio Violada",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Trata erros de validação de campos (@Valid dos DTOs que chegam incorretos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDto> tratarErroValidacaoCampos(MethodArgumentNotValidException ex) {
        List<String> errosCampos = ex.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        ErroRespostaDto erro = new ErroRespostaDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                "Um ou mais campos enviados na requisição estão inválidos.",
                errosCampos
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Trata validações genéricas de argumentos ou outras falhas de regras de negócios
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroRespostaDto> tratarErroArgumentoInvalido(IllegalArgumentException ex) {
        ErroRespostaDto erro = new ErroRespostaDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição Inválida",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}