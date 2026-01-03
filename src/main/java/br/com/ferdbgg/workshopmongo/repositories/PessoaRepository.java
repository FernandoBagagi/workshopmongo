package br.com.ferdbgg.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ferdbgg.workshopmongo.domain.Pessoa;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {

}
