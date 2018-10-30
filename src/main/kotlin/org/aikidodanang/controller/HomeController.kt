package org.aikidodanang.controller

import org.aikidodanang.services.NavigationService
import org.aikidodanang.services.PostService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController(
        val navigationService: NavigationService,
        val postService: PostService
) {

    @GetMapping
    fun redirectHome(model: Model): String {
        model.addAttribute("navigation", navigationService.getNavigationTree())
        model.addAttribute("post", postService.findByTitle("so-luoc-ve-to-su"))
        return "index"
    }
}