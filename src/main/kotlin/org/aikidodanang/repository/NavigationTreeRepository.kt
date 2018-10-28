package org.aikidodanang.repository

import org.aikidodanang.model.NavigationTree
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NavigationTreeRepository : MongoRepository<NavigationTree, String>