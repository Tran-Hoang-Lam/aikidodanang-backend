package org.aikidodanang.services

import org.aikidodanang.dto.PostDto
import org.aikidodanang.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
        val postRepository: PostRepository
) : PostService {
    override fun findByTitle(title: String): PostDto {
        return PostDto.fromPost(postRepository.findByTitle(title))
    }

    override fun findAllByArticle(article: Boolean): List<PostDto> {
        val articleList = postRepository.findAllByArticle(article)
        return articleList.map { PostDto.fromPost(it) }
    }
}