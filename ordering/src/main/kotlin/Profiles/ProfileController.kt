package com.coded.Profiles

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/profile")
class ProfileController(
    private val profileService: ProfileService
) {

    @PostMapping
    fun createOrUpdateProfile(
        @RequestBody request: ProfileRequest,
        @AuthenticationPrincipal userDetails: User
    ): String {
        return profileService.saveProfile(request, userDetails)
    }

    data class ProfileRequest(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String
    )
}
