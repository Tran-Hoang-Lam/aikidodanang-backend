package org.aikidodanang.repository

import org.aikidodanang.model.NavigationItem
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NavigationItemRepository : MongoRepository<NavigationItem, String> {
    fun findByFriendlyName(friendlyName: String): NavigationItem
}