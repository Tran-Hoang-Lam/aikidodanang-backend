package org.aikidodanang.model

import org.aikidodanang.dto.NavigationItemDto
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "navigation-item")
data class NavigationItem(
        val id: String,
        val displayName: String,
        val friendlyName: String,
        val postId: String
) {
    companion object {
        fun fromNavigationDto(navigationItemDto: NavigationItemDto) = NavigationItem(
                displayName = navigationItemDto.displayName,
                postId = navigationItemDto.postId,
                friendlyName = navigationItemDto.friendlyName,
                id = ""
        )
    }
}