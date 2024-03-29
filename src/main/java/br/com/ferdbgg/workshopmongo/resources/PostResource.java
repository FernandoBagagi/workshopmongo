package br.com.ferdbgg.workshopmongo.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ferdbgg.workshopmongo.domain.Post;
import br.com.ferdbgg.workshopmongo.resources.util.UtilURL;
import br.com.ferdbgg.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        final List<Post> posts = this.postService.findAll();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        final Post post = this.postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/procuraPorTitulo")
    public ResponseEntity<List<Post>> findByTituloContaining(
            @RequestParam(value = "palavra", defaultValue = "") String palavra) {
        final String palavraDecodificada = UtilURL.decodificarParametroURL(palavra);
        final List<Post> posts = this.postService.findByTituloContaining(palavraDecodificada);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/procura")
    public ResponseEntity<List<Post>> findContaining(@RequestParam(value = "palavra", defaultValue = "") String palavra,
            @RequestParam(value = "dataMin", defaultValue = "") String dataMin,
            @RequestParam(value = "dataMax", defaultValue = "") String dataMax) {
        final String palavraDecodificada = UtilURL.decodificarParametroURL(palavra);
        final Date dataMinDecodificada = UtilURL.converterDataURL(dataMin, new Date(0L));
        final Date dataMaxDecodificada = UtilURL.converterDataURL(dataMax);
        final List<Post> posts = this.postService.findContaining(palavraDecodificada, dataMinDecodificada,
                dataMaxDecodificada);
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping
    public ResponseEntity<Post> insert(@RequestBody Post novoPost) {
        final Post post = this.postService.insert(novoPost);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).body(post);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        this.postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post postNovosDados) {
        final Post post = this.postService.update(id, postNovosDados);
        return ResponseEntity.ok().body(post);
    }

}
