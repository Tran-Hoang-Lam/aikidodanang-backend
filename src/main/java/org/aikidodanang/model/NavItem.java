package org.aikidodanang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavItem {
    public static final NavItem DEFAULT_NAV_ITEM;
    static {
        DEFAULT_NAV_ITEM = new NavItem();
        DEFAULT_NAV_ITEM.setImage(Header.DEFAULT_HEADER.getImage());
    }

    private String displayName;
    private String link;
    private String page;
    private String defaultPost;
    private String image;
}
