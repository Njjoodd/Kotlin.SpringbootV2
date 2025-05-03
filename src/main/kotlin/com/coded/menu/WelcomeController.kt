package com.coded.menu

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {
    @Value("\${COMPANY_NAME}")
    lateinit var companyName: String

    @GetMapping("/welcome")
    fun welcome(): String{
        return "Welcome to Online Ordering by $companyName"
    }
}