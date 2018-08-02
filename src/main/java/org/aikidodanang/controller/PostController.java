package org.aikidodanang.controller;

import org.aikidodanang.model.Nav;
import org.aikidodanang.model.NavItem;
import org.aikidodanang.model.Post;
import org.aikidodanang.service.AlbumService;
import org.aikidodanang.service.NavService;
import org.aikidodanang.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PostController extends BaseController {
    private final PostService postService;
    private final NavService navService;
    private final AlbumService albumService;

    public PostController(PostService postService, NavService navService, AlbumService albumService) {
        this.postService = postService;
        this.navService = navService;
        this.albumService = albumService;
    }

    @GetMapping({"/{page}/{defaultPost}"})
    public String getPostWithPage(@PathVariable(value = "page") String page,
                                  @PathVariable(value = "defaultPost", required = false) String defaultPost,
                                  Model model) {
        Nav nav = navService.findByPage(page);
        NavItem navItem = navService.findByPageAndLinkOrDefaultPost(nav, defaultPost);
        Post post = postService.findByTitle(defaultPost == null ? navItem.getDefaultPost() : defaultPost);

        handleModelForPostPage(model, nav, navItem, post);

        if (page.equals("album")) {
            model.addAttribute("albums", albumService.findAll());
            return "album";
        }

        return "post";
    }
}
