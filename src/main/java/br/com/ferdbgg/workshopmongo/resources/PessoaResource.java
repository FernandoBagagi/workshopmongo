package br.com.ferdbgg.workshopmongo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ferdbgg.workshopmongo.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

}
