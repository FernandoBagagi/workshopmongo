package br.com.ferdbgg.workshopmongo.configurations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ferdbgg.workshopmongo.domain.Post;
import br.com.ferdbgg.workshopmongo.domain.Usuario;
import br.com.ferdbgg.workshopmongo.dtos.AutorDTO;
import br.com.ferdbgg.workshopmongo.dtos.ComentarioDTO;
import br.com.ferdbgg.workshopmongo.repositories.PostRepository;
import br.com.ferdbgg.workshopmongo.repositories.UsuarioRepository;

@Configuration
public class Instanciacao implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PostRepository postRepository;

    public Instanciacao(UsuarioRepository usuarioRepository, PostRepository postRepository) {
        this.usuarioRepository = usuarioRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());

        this.usuarioRepository.deleteAll();
        this.postRepository.deleteAll();

        List<Usuario> usuariosDumb = new ArrayList<>();
        usuariosDumb.add(new Usuario(null, "Maria Brown", "maria@gmail.com"));
        usuariosDumb.add(new Usuario(null, "Alex Green", "alex@gmail.com"));
        usuariosDumb.add(new Usuario(null, "Carlos José", "carlos@gmail.com"));
        this.usuarioRepository.saveAll(usuariosDumb);

        List<ComentarioDTO> comentariosDumb = new ArrayList<>();
        comentariosDumb.add(new ComentarioDTO("Boa viagem", simpleDateFormat.parse("2018-03-21"),
                new AutorDTO(usuariosDumb.get(1))));
        comentariosDumb.add(new ComentarioDTO("Boa viagem!!", simpleDateFormat.parse("2018-03-21"),
                new AutorDTO(usuariosDumb.get(2))));

        List<Post> postsDumb = new ArrayList<>();
        postsDumb.add(new Post(null, simpleDateFormat.parse("2018-03-21"), "Partiu viagem",
                "Vou viajar para São Paulo. Abraços!", new AutorDTO(usuariosDumb.get(0)), comentariosDumb));
        postsDumb.add(new Post(null, simpleDateFormat.parse("2018-03-23"), "Bom dia", "Acordei feliz hoje!",
                new AutorDTO(usuariosDumb.get(0)), new ArrayList<>()));
        this.postRepository.saveAll(postsDumb);

        usuariosDumb.get(0).getPosts().add(postsDumb.get(0));
        usuariosDumb.get(0).getPosts().add(postsDumb.get(1));
        this.usuarioRepository.save(usuariosDumb.get(0));

    }

}
