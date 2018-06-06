package org.aikidodanang.controller;

import org.aikidodanang.model.Header;
import org.aikidodanang.model.Nav;
import org.aikidodanang.model.NavItem;
import org.aikidodanang.model.Post;
import org.aikidodanang.service.NavService;
import org.aikidodanang.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PostController {
    private final PostService postService;
    private final NavService navService;

    public PostController(PostService postService, NavService navService) {
        this.postService = postService;
        this.navService = navService;
    }

    @GetMapping({"/{page}/{link}/{defaultPost}", "/{page}/{defaultPost}"})
    public String getPostWithPage(@PathVariable(value = "page") String page,
                                  @PathVariable(value = "defaultPost", required = false) String defaultPost,
                                  @PathVariable(value = "link", required = false) String link,
                                  Model model) {
        Nav nav = navService.findByPage(page);
        NavItem navItem = navService.findByPageAndLinkOrDefaultPost(page, defaultPost == null ? link : defaultPost);
        Post post = postService.findByTitle(defaultPost == null ? navItem.getDefaultPost() : defaultPost);

        model.addAttribute("header", generateHeader(navItem, post));
        model.addAttribute("nav", nav);
        model.addAttribute("post", post);

        return "post";
    }

    private Header generateHeader(NavItem navItem, Post post) {
        Header header = new Header();
        header.setImage(navItem.getImage());
        header.setClassHeading("site-heading");
        header.setHeading(post.getHeading());
        header.setSubHeading(post.getSubHeading());
        header.setMeta("Posted by " + post.getUserName());

        return header;
    }
}
