package br.com.ferdbgg.workshopmongo.dtos;

import java.io.Serializable;

import br.com.ferdbgg.workshopmongo.domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AutorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;

    public AutorDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }

}
