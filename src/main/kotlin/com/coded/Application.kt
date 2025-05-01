package com.coded

import com.coded.authentication.Role
import com.coded.authentication.UserEntity
import com.coded.authentication.UsersRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class Application{
    @Bean
    fun initUsers(usersRepository: UsersRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val username = "testuser"
        val password = "Password123"

        if (usersRepository.findByUsername(username) == null) {
            val user = UserEntity(
                name = "Test User",
                age = 25,
                username = username,
                password = passwordEncoder.encode(password),
                role = Role.USER
            )
            usersRepository.save(user)
            println(" User $username created.")
        } else {
            println("User $username already exists.")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
