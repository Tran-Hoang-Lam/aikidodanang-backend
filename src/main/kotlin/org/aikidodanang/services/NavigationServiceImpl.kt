package org.aikidodanang.services

import org.aikidodanang.converter.NavigationConverter
import org.aikidodanang.dto.NavigationItemDto
import org.aikidodanang.dto.NavigationTreeDto
import org.aikidodanang.repository.NavigationItemRepository
import org.aikidodanang.repository.NavigationTreeRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

val navigationConverter = Mappers.getMapper(NavigationConverter::class.java)!!

@Service("navigationService")
class NavigationServiceImpl(
        val navigationItemRepository: NavigationItemRepository,
        val navigationTreeRepository: NavigationTreeRepository
) : NavigationService {
    override fun getNavigationTree(): MutableList<NavigationTreeDto> {
        val result: MutableList<NavigationTreeDto> = mutableListOf()
        val navigationTree = navigationTreeRepository.findAll()
        navigationTree.forEach { it ->
            val mainNavigationItemDto = navigationItemRepository.findByFriendlyName(friendlyName = it.friendlyName)
            val navigationItemSubList: List<NavigationItemDto> = mutableListOf()
            if (it.subNav.any()) {
                it.subNav.forEach {
                    navigationItemSubList.plus(navigationItemRepository.findByFriendlyName(it))
                }
            }
            result.add(NavigationTreeDto(
                    item = navigationConverter.navigationToNavigationDto(mainNavigationItemDto),
                    subNav = navigationItemSubList)
            )
        }
        return result
    }
}