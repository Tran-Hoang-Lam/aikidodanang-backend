package org.aikidodanang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavItem {
    public static final NavItem DEFAULT_NAV_ITEM;
    static {
        DEFAULT_NAV_ITEM = new NavItem();
        DEFAULT_NAV_ITEM.setImage("https://res.cloudinary.com/dqbdhavya/image/upload/v1528383544/article.jpg");
    }

    @Field("displayName")
    private String displayName;
    @Field("page")
    private String page;
    @Field("defaultPost")
    private String defaultPost;
    @Field("image")
    private String image;
}
