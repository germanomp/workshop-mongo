package com.germano.workshop.repository;

import com.germano.workshop.domain.Post;
import com.germano.workshop.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
