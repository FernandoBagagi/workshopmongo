package br.com.ferdbgg.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ferdbgg.workshopmongo.domain.Post;
import br.com.ferdbgg.workshopmongo.repositories.PostRepository;
import br.com.ferdbgg.workshopmongo.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class PostService {
    
    private final PostRepository postRepository;
    
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> post = this.postRepository.findById(id);
        return post.orElseThrow(() -> new ObjetoNaoEncontradoException(id));
    }

    public Post insert(Post novoPost) {
        return this.postRepository.save(novoPost);
    }

    public void deleteById(String id) {
        Optional<Post> post = this.postRepository.findById(id);
        if(post.isPresent()) {
            this.postRepository.deleteById(id);
        } else {
            throw new ObjetoNaoEncontradoException(id);
        }
    }

    public Post update(String id, Post postNovosDados) {
        Optional<Post> post = this.postRepository.findById(id);
        if(post.isPresent()) {
            final Post postBanco = post.get();
            this.atualizarDados(postBanco, postNovosDados);
            return this.postRepository.save(postBanco);    
        } else {
            throw new ObjetoNaoEncontradoException(id);
        }
    }

    private void atualizarDados(Post postBanco, Post postNovosDados) {
        //Verificar se a regra de negócio permite colocar null ou não
        if(postNovosDados.getData() != null) {
            postBanco.setData(postNovosDados.getData());
        }
        if(postNovosDados.getTitulo() != null) {
            postBanco.setTitulo(postNovosDados.getTitulo()); 
        }
        if(postNovosDados.getCorpo() != null) {
            postBanco.setCorpo(postNovosDados.getCorpo());
        }
        if(postNovosDados.getAutor() != null) {
            postBanco.setAutor(postNovosDados.getAutor());
        }
    }

}
