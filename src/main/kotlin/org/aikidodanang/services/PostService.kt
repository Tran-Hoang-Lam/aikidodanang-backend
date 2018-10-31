package org.aikidodanang.services

import org.aikidodanang.dto.PostDto

interface PostService {
    fun findByTitle(title: String): PostDto
    fun findAllByArticle(article: Boolean): List<PostDto>
}