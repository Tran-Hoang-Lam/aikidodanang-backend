package org.aikidodanang.service;

import org.aikidodanang.model.Nav;
import org.aikidodanang.model.NavItem;
import org.aikidodanang.repository.NavRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NavServiceImpl implements NavService {
    private final NavRepository navRepository;

    public NavServiceImpl(NavRepository navRepository) {
        this.navRepository = navRepository;
    }

    @Override
    public Nav findByPage(String page) {
        return navRepository.findByPage(page);
    }

    @Override
    public NavItem findByPageAndLinkOrDefaultPost(String page, String linkOrDefaultPost) {
        Nav nav = findByPage(page);
        NavItem navItem = nav.getNavItem()
                .stream()
                .filter(item -> item.getLink().equals(linkOrDefaultPost) || item.getDefaultPost().equals(linkOrDefaultPost))
                .findFirst()
                .orElse(NavItem.DEFAULT_NAV_ITEM);
        return navItem;
    }
}
