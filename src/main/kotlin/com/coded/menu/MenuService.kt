package com.coded.menu

import org.springframework.stereotype.Service
import com.coded.menu.serverCache



@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    private val menuCache = serverCache.getMap<String, List<MenuItemEntity>>("menu")

    fun getMenu(): List<MenuItemEntity> {
        val cached = menuCache["all"]
        return if (cached != null) {
            println("Returning menu from cache")
            cached
        } else {
            println("Fetching menu from DB and caching it")
            val menu = menuRepository.findAll()
            menuCache["all"] = menu
            menu
        }
    }
}
