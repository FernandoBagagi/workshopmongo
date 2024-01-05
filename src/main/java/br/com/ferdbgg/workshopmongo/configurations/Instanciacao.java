package br.com.ferdbgg.workshopmongo.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ferdbgg.workshopmongo.domain.Usuario;
import br.com.ferdbgg.workshopmongo.repositories.UsuarioRepository;

@Configuration
public class Instanciacao implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public Instanciacao(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.usuarioRepository.deleteAll();
        List<Usuario> usuariosDumb = new ArrayList<>();
        usuariosDumb.add(new Usuario(null, "Maria Brown", "maria@gmail.com"));
        usuariosDumb.add(new Usuario(null, "Alex Green", "alex@gmail.com"));
        usuariosDumb.add(new Usuario(null, "Carlos José", "carlos@gmail.com"));
        this.usuarioRepository.saveAll(usuariosDumb);
    }

}
