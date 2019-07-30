package com.alkhanm.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alkhanm.workshopmongo.DTO.AuthorDTO;
import com.alkhanm.workshopmongo.DTO.CommentDTO;
import com.alkhanm.workshopmongo.domain.Post;
import com.alkhanm.workshopmongo.domain.User;
import com.alkhanm.workshopmongo.repository.PostRepository;
import com.alkhanm.workshopmongo.repository.UserRepository;

@Configuration //define que é uma classe de configuração que será carregada sempre que o app rodar.
public class Instatiantion implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post = new Post(null, sdf.parse("21/03/2018"), "Partiu, viagem!", "Ai galera, bora?", new AuthorDTO(bob));
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Bom dia!", "Acordei feliz.", new AuthorDTO(bob));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("22/03/2018"), new AuthorDTO(alex));
		
		post.getComments().addAll(Arrays.asList(c1, c2));
		post1.getComments().addAll(Arrays.asList(c3));
		
		
		postRepository.saveAll(Arrays.asList(post, post1));
		
		bob.getPosts().addAll(Arrays.asList(post, post1));
		userRepository.save(bob);
		
	}
}
