package org.aikidodanang.dto

data class NavigationTreeDto(
        val item: NavigationItemDto,
        val subNav: List<NavigationItemDto>
)