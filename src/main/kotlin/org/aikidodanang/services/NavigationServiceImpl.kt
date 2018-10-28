package org.aikidodanang.services

import org.aikidodanang.dto.NavigationItemDto
import org.aikidodanang.dto.NavigationTreeDto
import org.aikidodanang.repository.NavigationItemRepository
import org.aikidodanang.repository.NavigationTreeRepository
import org.springframework.stereotype.Service

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
            val navigationItemSubList: MutableList<NavigationItemDto> = mutableListOf()
            if (it.subNav.any()) {
                it.subNav.forEach {
                    navigationItemSubList.add(NavigationItemDto.fromNavigation(navigationItemRepository.findByFriendlyName(it)))
                }
            }
            result.add(NavigationTreeDto(
                    item = NavigationItemDto.fromNavigation(mainNavigationItemDto),
                    subNav = navigationItemSubList)
            )
        }
        return result
    }
}