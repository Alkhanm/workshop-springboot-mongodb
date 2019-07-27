package com.alkhanm.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.alkhanm.workshopmongo.domain.Post;

@Repository                                            
public interface PostRepository extends MongoRepository<Post, String> {//Associa o repositorio a classe Post com o Id em String.
	
}