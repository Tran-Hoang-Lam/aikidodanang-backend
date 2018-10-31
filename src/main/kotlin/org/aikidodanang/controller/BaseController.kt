package org.aikidodanang.controller

import org.aikidodanang.services.NavigationService
import org.aikidodanang.services.PostService
import org.springframework.ui.Model

abstract class BaseController(
        val navigationService: NavigationService,
        val postService: PostService
) {
    abstract fun getScreenName(): String

    fun getNavigation(model: Model) {
        model.addAttribute("navigation", navigationService.getNavigationTree())
    }

    fun defaultPostProcess(title: String, model: Model) {
        model.addAttribute("post", postService.findByTitle(title))
    }
}