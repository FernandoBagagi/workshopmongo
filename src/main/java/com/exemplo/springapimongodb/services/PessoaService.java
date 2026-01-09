package com.exemplo.springapimongodb.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.exemplo.springapimongodb.domain.Pessoa;
import com.exemplo.springapimongodb.dtos.PessoaDTO;
import com.exemplo.springapimongodb.repositories.PessoaRepository;
import com.exemplo.springapimongodb.services.exceptions.ObjetoNaoEncontradoException;

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

    public void deleteById(@NonNull String id) throws ObjetoNaoEncontradoException {

        this.findById(id); // Se não lançar exceção é pq encontrou

        this.repository.deleteById(id);

    }

    public PessoaDTO update( //
            @NonNull String id,
            @NonNull PessoaDTO pessoaNovosDados //
    ) throws ObjetoNaoEncontradoException {

        final Pessoa pessoaBanco = Objects.requireNonNull(this.findById(id));

        this.atualizarDados(pessoaBanco, pessoaNovosDados);

        final Pessoa pessoaSalva = this.repository.save(pessoaBanco);

        return PessoaDTO.parse(pessoaSalva);

    }

    private void atualizarDados( //
            @NonNull final Pessoa usuarioBanco,
            @NonNull PessoaDTO usuarioNovosDados //
    ) {

        if (usuarioNovosDados.getNome() != null) {
            usuarioBanco.setNome(usuarioNovosDados.getNome());
        }

    }

}
