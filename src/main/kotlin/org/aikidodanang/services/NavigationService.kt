package org.aikidodanang.services

import org.aikidodanang.dto.NavigationTreeDto

interface NavigationService {
    fun getNavigationTree(): MutableList<NavigationTreeDto>
}