package org.aikidodanang.service;

import org.aikidodanang.model.Post;

import java.util.List;

public interface PostService {
    Post findByTitle(String title);

    List<Post> findByArticle(boolean isArticle);
}
