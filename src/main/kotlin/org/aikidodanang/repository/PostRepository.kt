package org.aikidodanang.repository

import org.aikidodanang.model.Post
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : MongoRepository<Post, String> {
    fun findByTitle(title: String): Post
}