package org.aikidodanang.services

import org.aikidodanang.dto.PostDto

interface PostService {
    fun findByTitle(title: String): PostDto
}