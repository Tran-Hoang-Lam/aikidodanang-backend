package org.aikidodanang.controller;

import org.aikidodanang.model.Header;
import org.aikidodanang.model.Nav;
import org.aikidodanang.model.NavItem;
import org.aikidodanang.model.Post;
import org.springframework.ui.Model;

public abstract class BaseController {
    public Header generatePostHeader(NavItem navItem, Post post) {
        Header header = new Header();
        header.setImage(navItem.getImage());
        header.setClassHeading("site-heading");
        header.setHeading(post.getHeading());
        header.setSubHeading(post.getSubHeading());
        header.setMeta("Posted by " + post.getUserName());

        return header;
    }

    public void handleModelForPostPage(Model model, Nav nav, NavItem navItem, Post post) {
        model.addAttribute("header", generatePostHeader(navItem, post));
        model.addAttribute("nav", nav);
        model.addAttribute("post", post);
    }
}
