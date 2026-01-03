package br.com.ferdbgg.workshopmongo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import br.com.ferdbgg.workshopmongo.domain.Pessoa;
import br.com.ferdbgg.workshopmongo.repositories.PessoaRepository;
import br.com.ferdbgg.workshopmongo.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> findAll() {
        return this.repository.findAll();
    }

    public Pessoa findById(String id) throws ObjetoNaoEncontradoException {
        
        if (id == null || id.isBlank()) {
            throw new ObjetoNaoEncontradoException("vazio");
        }

        final Optional<Pessoa> pessoa = this.repository.findById(id);
        return pessoa.orElseThrow(() -> new ObjetoNaoEncontradoException(id));

    }

    public Pessoa insert(Pessoa pessoa) throws ObjetoNaoEncontradoException {

        if (pessoa == null) {
            // TODO: Fazer uma exceção Personalizada
            throw new ObjetoNaoEncontradoException("vazio");
        }

        try {
            return this.repository.save(pessoa);
        } catch (OptimisticLockingFailureException e) {
            // TODO: Fazer uma exceção Personalizada para quando der um erro ao salvar
            throw new ObjetoNaoEncontradoException("vazio");
        }

    }

    public void deleteById(String id) throws ObjetoNaoEncontradoException {

        this.findById(id); //Se não lançar exceção é pq encontrou
        
        this.repository.deleteById(Objects.requireNonNull(id));

    }

    public Pessoa update(String id, Pessoa pessoaNovosDados) throws ObjetoNaoEncontradoException {
        final Pessoa pessoaBanco = this.findById(id);
        final Pessoa pessoaAtualizada = this.atualizarDados(pessoaBanco, pessoaNovosDados);
        return this.repository.save(Objects.requireNonNull(pessoaAtualizada));
    }

    private Pessoa atualizarDados(Pessoa usuarioBanco, Pessoa usuarioNovosDados) {
        
        if (!Objects.equals(usuarioBanco.getNome(), usuarioNovosDados.getNome())) {
            usuarioBanco.setNome(usuarioNovosDados.getNome());
        }

        return usuarioBanco;
    }

}
