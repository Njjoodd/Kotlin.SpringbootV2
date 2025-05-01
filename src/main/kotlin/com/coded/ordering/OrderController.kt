package com.coded.ordering

import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/orders/v1")
class OrderController (
    private val orderRepository: OrderRepository )
{
    data class OrderRequest(
        val user: String,
        val restaurant: String,
        val items: List<String>
    )
    @PostMapping("/orders")
    fun submitOrder(@RequestBody request: OrderRequest): String {
        val order = OrderEntity(
            customerName = request.user,
            restaurant = request.restaurant,
            items = request.items.joinToString(", ")
        )
        orderRepository.save(order)
        return "Order from ${request.user} for ${request.items.size} item(s) at ${request.restaurant} received!"
    }

}




