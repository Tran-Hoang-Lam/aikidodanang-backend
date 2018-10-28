package org.aikidodanang.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "navigation-tree")
data class NavigationTree(
        val id: String,
        val friendlyName: String,
        val subNav: List<String>
)