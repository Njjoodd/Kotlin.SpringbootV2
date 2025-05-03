package com.coded.menu

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {

    @Value("\${feature.menu.discount:false}")
    private val discountEnabled: Boolean = false

    private val menuCache = serverCache.getMap<String, List<MenuItemEntity>>("menu")

    fun getMenu(): List<MenuItemEntity> {
        val cached = menuCache["all"]
        val menu = if (cached != null) {
            println("Returning menu from cache")
            cached
        } else {
            println("Fetching menu from DB and caching it")
            val freshMenu = menuRepository.findAll()
            menuCache["all"] = freshMenu
            freshMenu
        }

        return if (discountEnabled) {
            println("Applying 20% discount")
            menu.map { it.copy(price = it.price * 0.8) }
        } else {
            menu
        }
    }
}
