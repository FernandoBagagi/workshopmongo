package com.exemplo.springapimongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.springapimongodb.domain.Pessoa;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {

}
