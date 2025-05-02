package com.coded

import com.coded.Profiles.ProfileController
import com.coded.authentication.JwtService
import com.coded.authentication.Role
import com.coded.authentication.UserEntity
import com.coded.authentication.UsersRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.http.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApplicationTesting {
    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var restTemplate: TestRestTemplate
    lateinit var jwtToken: String

    @BeforeAll
    fun setup () {
        val username = "testuser"
        val password = "Password123"
        val existingUser = usersRepository.findByUsername(username)
        if (existingUser == null){
            val user = UserEntity(
                name = "Test User",
                age = 25,
                username = username,
                password = passwordEncoder.encode(password),
                role = Role.USER

            )
            usersRepository.save(user)
        }
        jwtToken = jwtService.generateToken(username)
        }

    @Test
    fun `authenticated user can create profile` () {
        val profileRequest = ProfileController.ProfileRequest(
            firstName = "Njood",
            lastName = "Aldousari",
            phoneNumber = "96055159"

        )
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            setBearerAuth(jwtToken)
        }
        val entity = HttpEntity(profileRequest, headers)

        val response = restTemplate.postForEntity(
            "http://localhost:$port/api/v1/profile",
            entity,
            String::class.java

        )

        Assertions.assertEquals(HttpStatus.OK, response.statusCode)
        Assertions.assertTrue(response.body?.contains("Profile saved for testuser") == true)
    }
}