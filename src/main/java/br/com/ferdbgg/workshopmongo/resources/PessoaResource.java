package br.com.ferdbgg.workshopmongo.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
    public ResponseEntity<Page<PessoaDTO>> findAll(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) //
            @NonNull Pageable pageable //
    ) {
        final Page<Pessoa> pessoasBanco = this.pessoaService.findAll(pageable);
        final Page<PessoaDTO> pessoasDTOs = pessoasBanco.map(PessoaDTO::parse);
        return ResponseEntity.ok().body(pessoasDTOs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable String id) {
        final Pessoa pessoaBanco = this.pessoaService.findById(id);
        final PessoaDTO pessoaDTO = PessoaDTO.parse(pessoaBanco);
        return ResponseEntity.ok().body(pessoaDTO);
    }

}
