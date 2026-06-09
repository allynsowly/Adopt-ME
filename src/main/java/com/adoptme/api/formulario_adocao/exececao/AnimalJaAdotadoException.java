package com.adoptme.api.formulario_adocao.exececao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnimalJaAdotadoException extends RuntimeException{

    public AnimalJaAdotadoException(String message) {
        super(message);
    }

}

