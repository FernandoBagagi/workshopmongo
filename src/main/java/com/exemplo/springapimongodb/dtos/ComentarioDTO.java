package com.exemplo.springapimongodb.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComentarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String texto;
    private Date data;
    private AutorDTO autor;

}
