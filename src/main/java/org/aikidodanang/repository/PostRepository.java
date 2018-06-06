package org.aikidodanang.repository;

import org.aikidodanang.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
    Post findByTitle(String title);
}
