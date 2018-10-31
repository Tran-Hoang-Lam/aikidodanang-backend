package org.aikidodanang.dto

import org.aikidodanang.model.Post
import java.time.LocalDateTime

data class PostDto(
        val id: String,
        val title: String,
        val image: String,
        val heading: String,
        val subHeading: String,
        val content: String,
        val article: Boolean,
        val createDate: LocalDateTime
) {
    companion object {
        fun fromPost(post: Post) = PostDto(
                id = post.id,
                title = post.title,
                image = post.image,
                heading = post.heading,
                subHeading = post.subHeading,
                content = post.content,
                article = post.article,
                createDate = post.createDate
        )
    }
}