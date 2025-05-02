package com.coded.menu

import com.coded.Profiles.ProfileService
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/api/menu/v1")
class MenuController(
    private val menuService: MenuService
) {
    @GetMapping
    fun getMenu(): List<MenuItemEntity> = menuService.getMenu()
}
