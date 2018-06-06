package org.aikidodanang.controller;

import org.aikidodanang.model.Header;
import org.aikidodanang.model.Nav;
import org.aikidodanang.service.NavService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final String INDEX_PAGE = "index";

    protected final NavService navService;

    public HomeController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping({"/"})
    public String redirectHome(Model model) {
        Nav nav = navService.findByPage(INDEX_PAGE);

        model.addAttribute("header", Header.DEFAULT_HEADER);
        model.addAttribute("nav", nav);

        return "index";
    }
}
