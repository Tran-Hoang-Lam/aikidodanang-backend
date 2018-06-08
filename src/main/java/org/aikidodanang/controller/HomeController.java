package org.aikidodanang.controller;

import org.aikidodanang.model.Header;
import org.aikidodanang.model.Nav;
import org.aikidodanang.model.Post;
import org.aikidodanang.service.NavService;
import org.aikidodanang.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final String INDEX_PAGE = "index";

    private final PostService postService;
    private final NavService navService;

    public HomeController(PostService postService, NavService navService) {
        this.postService = postService;
        this.navService = navService;
    }

    @GetMapping({"/"})
    public String redirectHome(Model model) {
        Nav nav = navService.findByPage(INDEX_PAGE);
        List<Post> postList = postService.findByArticle(true);

        model.addAttribute("header", Header.DEFAULT_HEADER);
        model.addAttribute("nav", nav);
        model.addAttribute("posts", postList);

        return "index";
    }
}
