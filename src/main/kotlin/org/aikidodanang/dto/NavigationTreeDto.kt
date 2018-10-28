package org.aikidodanang.dto

data class NavigationTreeDto(
        val item: NavigationItemDto,
        val subNav: MutableList<NavigationItemDto>
)