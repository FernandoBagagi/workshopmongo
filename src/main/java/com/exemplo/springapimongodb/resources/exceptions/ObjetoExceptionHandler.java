package com.exemplo.springapimongodb.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exemplo.springapimongodb.services.exceptions.ObjetoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ObjetoExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<PadraoError> objetoNaoEncontrado(ObjetoNaoEncontradoException exception,
            HttpServletRequest request) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        final PadraoError erro = new PadraoError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("Objeto não encontrado");
        erro.setMessage(exception.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }

    /*
     * @ExceptionHandler(BancoDadosException.class)
     * public ResponseEntity<PadraoError> erroBancoDados(BancoDadosException
     * exception, HttpServletRequest request) {
     * HttpStatus status = HttpStatus.BAD_REQUEST;
     * PadraoError erro = new PadraoError();
     * erro.setTimestamp(Instant.now());
     * erro.setStatus(status.value());
     * erro.setError("Não foi possível realizar a operação no banco de dados");
     * erro.setMessage(exception.getMessage());
     * erro.setPath(request.getRequestURI());
     * return ResponseEntity.status(status).body(erro);
     * }
     */

}
