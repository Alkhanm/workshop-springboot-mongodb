package com.alkhanm.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.alkhanm.workshopmongo.domain.Post;

@Repository                                            
public interface PostRepository extends MongoRepository<Post, String> {//Associa o repositorio a classe Post com o Id em String.
	
	//Usa o campo titulo, pegando o primeiro campo(text) que está no indice 0. E com a opção IgnoreCase representado pelo 'i'
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
}
