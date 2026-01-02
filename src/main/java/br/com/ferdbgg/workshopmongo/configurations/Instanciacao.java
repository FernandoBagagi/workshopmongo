package br.com.ferdbgg.workshopmongo.configurations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ferdbgg.workshopmongo.domain.Post;
import br.com.ferdbgg.workshopmongo.domain.Usuario;
import br.com.ferdbgg.workshopmongo.dtos.AutorDTO;
import br.com.ferdbgg.workshopmongo.dtos.ComentarioDTO;
import br.com.ferdbgg.workshopmongo.repositories.PostRepository;
import br.com.ferdbgg.workshopmongo.repositories.UsuarioRepository;
import lombok.val;

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

                this.usuarioRepository.deleteAll();
                this.postRepository.deleteAll();

                final List<Usuario> usuariosDumb = List.of(
                                new Usuario(
                                                null,
                                                "Maria Brown",
                                                "maria@gmail.com" //

                                ),
                                new Usuario(
                                                null,
                                                "Alex Green",
                                                "alex@gmail.com" //

                                ),
                                new Usuario(
                                                null,
                                                "Carlos José",
                                                "carlos@gmail.com" //

                                )//
                );

                val usuariosSalvos = this.usuarioRepository.saveAll(Objects.requireNonNull(usuariosDumb));

                final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setTimeZone(TimeZone.getDefault());

                final List<ComentarioDTO> comentariosDumb = List.of(
                                new ComentarioDTO(
                                                "Boa viagem",
                                                dateFormat.parse("2018-03-21"),
                                                new AutorDTO(usuariosSalvos.get(1)) //
                                ),
                                new ComentarioDTO(
                                                "Boa viagem!!",
                                                dateFormat.parse("2018-03-22"),
                                                new AutorDTO(usuariosSalvos.get(2)) //
                                ) //
                );

                final List<Post> postsDumb = List.of(
                                new Post(
                                                null,
                                                dateFormat.parse("2018-03-21"),
                                                "Partiu viagem",
                                                "Vou viajar para São Paulo. Abraços!",
                                                new AutorDTO(usuariosSalvos.get(0)),
                                                comentariosDumb //
                                ),
                                new Post(
                                                null,
                                                dateFormat.parse("2018-03-23"),
                                                "Bom dia",
                                                "Acordei feliz hoje!",
                                                new AutorDTO(usuariosSalvos.get(0)),
                                                List.of() //
                                )//
                );

                val postsSalvos = this.postRepository.saveAll(Objects.requireNonNull(postsDumb));

                usuariosSalvos.get(0).getPosts().add(postsSalvos.get(0));
                usuariosSalvos.get(0).getPosts().add(postsSalvos.get(1));

                this.usuarioRepository.save(Objects.requireNonNull(usuariosSalvos.get(0)));

        }

}
