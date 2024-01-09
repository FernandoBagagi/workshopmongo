package br.com.ferdbgg.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ferdbgg.workshopmongo.domain.Post;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    /*
     * Links úteis
     * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
     * https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
     * https://docs.mongodb.com/manual/reference/operator/query/regex/
     */

    List<Post> findByTituloContaining(String palavra);

    List<Post> findByTituloContainingIgnoreCase(String palavra);

    @Query("{ titulo : { $regex: ?0, $options: 'i' } }")
    List<Post> encontrarPorTitulo(String titulo);

    @Query("{ $and: [ { $or: [ { titulo : { $regex: ?0, $options: 'i' } }, { corpo : { $regex: ?0, $options: 'i' } } ] }, { data: { $gte: ?1 } }, { data: { $lte: ?2 } } ] }")
    List<Post> buscaCompleta(String palavra, Date dataMin, Date dataMax);

    // TODO: ver porque não foi acessando assim
    // , { comentarios.texto : { $regex: ?0, $options: 'i' } }
}
