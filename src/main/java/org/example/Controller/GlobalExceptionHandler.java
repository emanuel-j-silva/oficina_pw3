package org.example.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.DTO.DadosErroValidacaoDTO;
import org.example.Exception.ConsertoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConsertoNotFoundException.class)
    public ResponseEntity<String> handleConsertoNotFoundException(ConsertoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        var listErros = erros.stream().map(DadosErroValidacaoDTO::new).toList();

        return ResponseEntity.badRequest().body(listErros);
    }

}
