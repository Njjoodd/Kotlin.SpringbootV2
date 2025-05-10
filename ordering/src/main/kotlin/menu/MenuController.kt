package com.coded.menu

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu/v1")
class MenuController(
    private val menuService: MenuService,

    @Value("\${feature.welcome.festive:false}")
    private val isFestive: Boolean,

    @Value("\${feature.menu.discount:false}")
    private val isDiscountActive: Boolean
) {

    @GetMapping
    fun getMenu(): Any {
        val originalMenu = menuService.getMenu()


        val discountedMenu = if (isDiscountActive) {
            originalMenu.map {
                it.copy(price = (it.price * 0.8).toBigDecimal().setScale(2, java.math.RoundingMode.HALF_UP).toDouble())
            }
        } else {
            originalMenu
        }

        return if (isFestive) {
            mapOf(
                "message" to "🎉 Eidkom Mubarak! Enjoy the offers!",
                "menu" to discountedMenu
            )
        } else {
            discountedMenu
        }
    }
}
