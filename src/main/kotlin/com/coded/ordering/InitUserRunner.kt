package com.coded.ordering

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import com.coded.ordering.UserEntity
import com.coded.ordering.Roles


@SpringBootApplication
class InitUserRunner {
    @Bean
    fun initUsers(userRepository: UsersRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val user = UserEntity(
            name = "HelloUser",
            username = "testuser",
            password = passwordEncoder.encode("password123"),
            age = 18,
            role = Roles.USER
        )
        if (userRepository.findByUsername(user.username) == null) {
            println("Creating user ${user.username}")
            userRepository.save(user)
        } else  {
            println("User ${user.username} already exists")
        }
    }
}
