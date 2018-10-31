package org.aikidodanang.controller

import org.aikidodanang.services.NavigationService
import org.aikidodanang.services.PostService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("article")
class ArticleController(
        navigationService: NavigationService,
        postService: PostService
) : BaseController(
        navigationService = navigationService,
        postService = postService
) {
    override fun getScreenName(): String {
        return "view-post"
    }

    @GetMapping("{postTitle}")
    fun redirectPage(@PathVariable("postTitle") postTitle: String, model: Model): String {
        getNavigation(model)
        defaultPostProcess(postTitle, model)
        return getScreenName()
    }
}