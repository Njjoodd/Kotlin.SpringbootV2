package com.coded.Profiles
import com.coded.authentication.UsersRepository
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val usersRepository: UsersRepository,
    private val profileRepository: ProfileRepository
) {
    fun saveProfile(request: ProfileController.ProfileRequest, userDetails: User): String {
        val userEntity = usersRepository.findByUsername(userDetails.username)
            ?: return "User not found"

        val profile = ProfileEntity(
            user = userEntity,
            firstName = request.firstName,
            lastName = request.lastName,
            phoneNumber = request.phoneNumber
        )

        profileRepository.save(profile)
        return "Profile saved for ${userEntity.username}"
    }
}
