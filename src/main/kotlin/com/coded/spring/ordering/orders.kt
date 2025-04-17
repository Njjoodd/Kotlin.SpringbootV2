package com.coded.spring.ordering

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController {

    val orderDatabase = mutableListOf<Order>()

    @PostMapping
    fun placeOrder (@RequestBody order: Order): String { orderDatabase.add(order)
        return "Order received!"
    }

    @GetMapping
    fun listOrders(): List<Order> {
        return orderDatabase
    }
    
}

data class Order(val user: String, val resturant: String, val items: List<String>)

