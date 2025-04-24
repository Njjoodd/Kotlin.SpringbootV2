package com.coded.ordering

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/orders")
class OrderController(
    val orderRepository: OrderRepository
) {

    @GetMapping("/welcome")
    fun welcome(): String {
        return "Welcome to the online ordering service! Happy shopping!"
    }

    @PostMapping
    fun placeOrder(@RequestBody order: OrderEntity): String {
        orderRepository.save(order)
        return "Order received!"
    }

    @GetMapping
    fun listOrders(): List<OrderEntity> {
        return orderRepository.findAll()

    }
}

