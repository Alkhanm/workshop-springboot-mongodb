package com.alkhanm.workshopmongo.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkhanm.workshopmongo.domain.Post;
import com.alkhanm.workshopmongo.domain.User;
import com.alkhanm.workshopmongo.repository.PostRepository;
import com.alkhanm.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
