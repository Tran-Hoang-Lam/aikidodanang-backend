package org.aikidodanang.service;

import org.aikidodanang.model.Post;

public interface PostService {
    Post findByTitle(String title);
}
