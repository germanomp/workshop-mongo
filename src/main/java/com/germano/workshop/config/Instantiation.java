package com.germano.workshop.config;

import com.germano.workshop.domain.Post;
import com.germano.workshop.domain.User;
import com.germano.workshop.dto.AuthorDTO;
import com.germano.workshop.dto.CommentDTO;
import com.germano.workshop.repository.PostRepository;
import com.germano.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

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

        Post post1 = new Post(null, sdf.parse("21/08/2023"), "Partiu viagem", "Vou viajar para são paulo.", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/08/2023"), "Bom dia", "Acordei agora.", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem", sdf.parse("21/08/2023"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("23/08/2023"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um bom dia", sdf.parse("21/08/2023"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
