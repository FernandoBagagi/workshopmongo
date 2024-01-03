package br.com.ferdbgg.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ferdbgg.workshopmongo.domain.Usuario;
import br.com.ferdbgg.workshopmongo.dtos.UsuarioDTO;
import br.com.ferdbgg.workshopmongo.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    private final UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        final List<Usuario> usuarios = this.usuarioService.findAll();
        final List<UsuarioDTO> usuariosDTO = usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(usuariosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable String id){
        final Usuario usuario = this.usuarioService.findById(id);
        final UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO novoUsuario){
        final UsuarioDTO usuario = new UsuarioDTO(this.usuarioService.insert(new Usuario(novoUsuario)));
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        this.usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable String id, @RequestBody UsuarioDTO usuarioNovosDados){
        final UsuarioDTO usuario = new UsuarioDTO(this.usuarioService.update(id, new Usuario(usuarioNovosDados)));
        return ResponseEntity.ok().body(usuario);
    }

}
