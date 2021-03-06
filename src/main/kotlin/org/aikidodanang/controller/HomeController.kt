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
        navigationService: NavigationService,
        postService: PostService
): BaseController(
        navigationService = navigationService,
        postService = postService
) {
    override fun getScreenName(): String {
        return "index"
    }

    @GetMapping
    fun redirectHome(model: Model): String {
        getNavigation(model)
        model.addAttribute("articleList", postService.findAllByArticle(true))
        return getScreenName()
    }
}