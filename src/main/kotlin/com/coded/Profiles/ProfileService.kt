package com.coded.Profiles

import com.coded.ordering.UsersRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class ProfileService(
    private val profileRepository: ProfileRepository,
    private val userRepository: UsersRepository
) {
    fun createProfile(username: String, request: ProfileRequest): ProfileEntity{
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        if (profileRepository.findByUserId(user.id!!) != null) {
            throw IllegalStateException("Profile already exists for this user")

        }
        val profile = ProfileEntity(
            firstName = request.firstName,
            lastName = request.lastName,
            phoneNumber = request.phoneNumber,
            user = user

        )

        return profileRepository.save(profile)
    }
}