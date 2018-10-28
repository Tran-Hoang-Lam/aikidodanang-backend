package org.aikidodanang.converter

import org.aikidodanang.dto.NavigationItemDto
import org.aikidodanang.model.NavigationItem
import org.mapstruct.Mapper

@Mapper
interface NavigationConverter {
    fun navigationToNavigationDto(navigationItem: NavigationItem): NavigationItemDto
    fun navigationDtoToNavigation(navigationItemDto: NavigationItemDto): NavigationItem
}