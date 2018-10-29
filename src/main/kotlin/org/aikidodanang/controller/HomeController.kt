package org.aikidodanang.controller

import org.aikidodanang.services.NavigationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController(
        val navigationService: NavigationService
) {

    @GetMapping
    fun redirectHome(model: Model): String {
        model.addAttribute("navigation", navigationService.getNavigationTree())
        return "index"
    }
}