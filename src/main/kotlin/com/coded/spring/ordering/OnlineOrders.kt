package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping

class OnlineOrders {
    @GetMapping("/")
    fun welcome(): String {
        return "Welcome to (this) online ordering service! " +
                "The place where you can find everything you need with a simple tap" +
                "happy shopping "
    }
}