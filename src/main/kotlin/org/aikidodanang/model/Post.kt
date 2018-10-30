package org.aikidodanang.model

import org.aikidodanang.dto.PostDto
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "posts")
data class Post(
        val id: String,
        val title: String,
        val image: String,
        val heading: String,
        val subHeading: String,
        val content: String,
        val createDate: LocalDateTime
) {
    companion object {
        fun fromPostDto(postDto: PostDto) = Post(
                id = postDto.id,
                title = postDto.title,
                image = postDto.image,
                heading = postDto.heading,
                subHeading = postDto.subHeading,
                content = postDto.content,
                createDate = postDto.createDate
        )
    }
}