package com.germano.workshop.services;

import com.germano.workshop.domain.Post;
import com.germano.workshop.domain.User;
import com.germano.workshop.dto.UserDTO;
import com.germano.workshop.repository.PostRepository;
import com.germano.workshop.repository.UserRepository;
import com.germano.workshop.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repository.searchTitle(text);
    }

}
