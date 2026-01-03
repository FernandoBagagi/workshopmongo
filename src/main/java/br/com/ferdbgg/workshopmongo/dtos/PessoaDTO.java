package br.com.ferdbgg.workshopmongo.dtos;

import java.io.Serializable;

import br.com.ferdbgg.workshopmongo.domain.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;

    private PessoaDTO(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static PessoaDTO parse(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getId(), pessoa.getNome());
    }

    public Pessoa toPessoa() {
        return new Pessoa(this.id, this.nome);
    }

}
