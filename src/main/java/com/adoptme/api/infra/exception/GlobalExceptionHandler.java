package com.adoptme.api.infra.exception;

import com.adoptme.api.formularioadocao.dto.ErroRespostaDto;
import com.adoptme.api.formularioadocao.exception.AnimalJaAdotadoException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespostaDto> tratarErroGenerico(Exception ex) {

        ErroRespostaDto erro = new ErroRespostaDto(
                LocalDateTime.now(),
                500,
                "Erro interno",
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }
}