package com.coded.Profiles

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profiles")
class ProfileController(
    private val profileService: ProfileService
) {
    @PostMapping
    fun createProfile(
        @RequestBody request: ProfileRequest,
        authentication: Authentication
    ): ResponseEntity<Any> {
        val username = authentication.name
        val profile = profileService.createProfile(username, request)
        return ResponseEntity.ok(profile)
    }
}

        data class ProfileRequest(
            val firstName: String,
            val lastName: String,
            val phoneNumber: String
        )

