package org.aikidodanang.service;

import org.aikidodanang.model.Nav;
import org.aikidodanang.model.NavItem;

public interface NavService {
    Nav findByPage(String page);
    NavItem findByPageAndLinkOrDefaultPost(Nav nav, String link);
}
