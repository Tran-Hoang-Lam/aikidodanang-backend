package org.aikidodanang.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "navigation-item")
data class NavigationItem(
        val id: String,
        val displayName: String,
        val friendlyName: String,
        val postId: String
)