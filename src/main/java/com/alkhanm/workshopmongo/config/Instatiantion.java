package com.alkhanm.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alkhanm.workshopmongo.domain.Post;
import com.alkhanm.workshopmongo.domain.User;
import com.alkhanm.workshopmongo.DTO.AuthorDTO;
import com.alkhanm.workshopmongo.DTO.CommentDTO;
import com.alkhanm.workshopmongo.repository.PostRepository;
import com.alkhanm.workshopmongo.repository.UserRepository;

@Configuration
public class Instatiantion implements CommandLineRunner {

	@Autowired
	private UserRepository userReposiroty;

	@Autowired
	private PostRepository postReposiroty;

	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21-03-2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23-03-2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		Post post3 = new Post(null, sdf.parse("30-05-2018"), "Olá", "Tudo bom com vcs?!", new AuthorDTO(alex));
		Post post4 = new Post(null, sdf.parse("25-08-2017"), "Bom dia", "Alguem poderia me ajudar?", new AuthorDTO(bob));
		Post post5 = new Post(null, new Date(), "Acorda povo.", "Bom dia, meus consagrados!", new AuthorDTO(bob));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21-03-2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22-03-2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Não!", sdf.parse("23-03-2018"), new AuthorDTO(alex));
		CommentDTO c4 = new CommentDTO("Comigo não kkk", sdf.parse("23-03-2018"), new AuthorDTO(alex));
		CommentDTO c5 = new CommentDTO("eeeh!", sdf.parse("23-08-2019"), new AuthorDTO(bob));
		CommentDTO c6 = new CommentDTO("lorem ipsom!", sdf.parse("11-11-2011"), new AuthorDTO(bob));
		CommentDTO c7 = new CommentDTO("Lorem ipsom ad poi lo!", sdf.parse("21-01-2017"), new AuthorDTO(maria));
		CommentDTO c8 = new CommentDTO("ipsom lorem!", sdf.parse("28-08-2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3, c2));
		post3.getComments().addAll(Arrays.asList(c3, c1, c8));
		post4.getComments().addAll(Arrays.asList(c3, c6, c7));
		post5.getComments().addAll(Arrays.asList(c3, c4, c5));
		
		postReposiroty.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userReposiroty.save(maria);
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userReposiroty.save(bob);
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userReposiroty.save(alex);
		
	}

}





