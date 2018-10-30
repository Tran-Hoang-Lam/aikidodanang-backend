package org.aikidodanang.model

import org.aikidodanang.dto.NavigationItemDto
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "navigation-item")
data class NavigationItem(
        val id: String,
        val displayName: String,
        val friendlyName: String,
        val postTitle: String
) {
    companion object {
        fun fromNavigationDto(navigationItemDto: NavigationItemDto) = NavigationItem(
                displayName = navigationItemDto.displayName,
                postTitle = navigationItemDto.postTitle,
                friendlyName = navigationItemDto.friendlyName,
                id = ""
        )
    }
}