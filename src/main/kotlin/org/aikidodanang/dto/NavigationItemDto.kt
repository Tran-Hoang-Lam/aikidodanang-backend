package org.aikidodanang.dto

import org.aikidodanang.model.NavigationItem

data class NavigationItemDto(
        val displayName: String,
        val friendlyName: String,
        val postId: String
) {
    companion object {
        fun fromNavigation(navigationItem: NavigationItem) = NavigationItemDto(
                displayName = navigationItem.displayName,
                friendlyName = navigationItem.friendlyName,
                postId = navigationItem.postId
        )
    }
}