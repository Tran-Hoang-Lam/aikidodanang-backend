package org.aikidodanang.service;

import org.aikidodanang.model.Post;
import org.aikidodanang.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findByTitle(String title) {
        return postRepository.findByTitle(title);
    }
}
