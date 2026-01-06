package br.com.ferdbgg.workshopmongo.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.ferdbgg.workshopmongo.domain.Pessoa;
import br.com.ferdbgg.workshopmongo.dtos.PessoaDTO;
import br.com.ferdbgg.workshopmongo.repositories.PessoaRepository;
import br.com.ferdbgg.workshopmongo.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Page<Pessoa> findAll(@NonNull Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Pessoa findById(String id) throws ObjetoNaoEncontradoException {

        if (id == null || id.isBlank()) {
            throw new ObjetoNaoEncontradoException("vazio");
        }

        final Optional<Pessoa> pessoa = this.repository.findById(id);
        return pessoa.orElseThrow(() -> new ObjetoNaoEncontradoException(id));

    }

    public PessoaDTO insert(@NonNull PessoaDTO pessoaDTONova) {
        final Pessoa pessoaNova = Objects.requireNonNull(pessoaDTONova.toPessoa());
        final Pessoa pessoaSalva = this.insert(pessoaNova);
        return PessoaDTO.parse(pessoaSalva);
    }

    public Pessoa insert(@NonNull Pessoa pessoa) {
        return this.repository.save(pessoa);
    }

    public void deleteById(String id) throws ObjetoNaoEncontradoException {

        this.findById(id); // Se não lançar exceção é pq encontrou

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
