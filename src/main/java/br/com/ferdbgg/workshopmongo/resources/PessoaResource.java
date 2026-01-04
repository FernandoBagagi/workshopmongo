package br.com.ferdbgg.workshopmongo.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferdbgg.workshopmongo.domain.Pessoa;
import br.com.ferdbgg.workshopmongo.dtos.PessoaDTO;
import br.com.ferdbgg.workshopmongo.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        // TODO: ver como paginar isso
        final List<Pessoa> pessoasBanco = this.pessoaService.findAll();
        final List<PessoaDTO> pessoasDTOs = pessoasBanco.stream().map(PessoaDTO::parse).toList();
        return ResponseEntity.ok().body(pessoasDTOs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable String id) {
        final Pessoa pessoaBanco = this.pessoaService.findById(id);
        final PessoaDTO pessoaDTO = PessoaDTO.parse(pessoaBanco);
        return ResponseEntity.ok().body(pessoaDTO);
    }

}
