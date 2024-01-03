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

    /*public Usuario update(Integer id, Usuario usuarioNovosDados) {
        Usuario usuarioMonitoradoJPA = this.usuarioRepository.getReferenceById(id);
        //Dá EntityNotFoundException ao tentar acessar uma entidade que não está persistida
        try {
            this.atualizarDados(usuarioMonitoradoJPA, usuarioNovosDados);
            return this.usuarioRepository.save(usuarioMonitoradoJPA);
        } catch(EntityNotFoundException exception) {
            throw new ObjetoNaoEncontradoException(id);
        }
    }

    private void atualizarDados(Usuario usuarioMonitoradoJPA, Usuario usuarioNovosDados) {
        //Verificar se a regra de negócio permite colocar null ou não
        if(usuarioNovosDados.getNome() != null) {
            usuarioMonitoradoJPA.setNome(usuarioNovosDados.getNome()); 
        }
        if(usuarioNovosDados.getEmail() != null) {
            usuarioMonitoradoJPA.setEmail(usuarioNovosDados.getEmail());
        }
        if(usuarioNovosDados.getTelefone() != null) {
            usuarioMonitoradoJPA.setTelefone(usuarioNovosDados.getTelefone());
        }
    }*/

}
