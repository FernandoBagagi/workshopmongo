package br.com.ferdbgg.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ferdbgg.workshopmongo.domain.Usuario;
import br.com.ferdbgg.workshopmongo.repositories.UsuarioRepository;
import br.com.ferdbgg.workshopmongo.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(String id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjetoNaoEncontradoException(id));
    }

    public Usuario insert(Usuario novoUsuario) {
        return this.usuarioRepository.save(novoUsuario);
    }

    public void deleteById(String id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if(usuario.isPresent()) {
            this.usuarioRepository.deleteById(id);
            /*try {
                this.usuarioRepository.deleteById(id);
            } catch(DataIntegrityViolationException exception) {
                throw new BancoDadosException(exception.getMessage());
            }*/
        } else {
            throw new ObjetoNaoEncontradoException(id);
        }
    }

    public Usuario update(String id, Usuario usuarioNovosDados) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if(usuario.isPresent()) {
            final Usuario usuarioBanco = usuario.get();
            this.atualizarDados(usuarioBanco, usuarioNovosDados);
            return this.usuarioRepository.save(usuarioBanco);    
        } else {
            throw new ObjetoNaoEncontradoException(id);
        }
    }

    private void atualizarDados(Usuario usuarioBanco, Usuario usuarioNovosDados) {
        //Verificar se a regra de negócio permite colocar null ou não
        if(usuarioNovosDados.getNome() != null) {
            usuarioBanco.setNome(usuarioNovosDados.getNome()); 
        }
        if(usuarioNovosDados.getEmail() != null) {
            usuarioBanco.setEmail(usuarioNovosDados.getEmail());
        }
    }

}
