package com.exemplo.springapimongodb.services.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjetoNaoEncontradoException(Object id) {
        super("Objeto de id " + id.toString() + " não foi encontrado");
    }

}
