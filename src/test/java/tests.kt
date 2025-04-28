package com.coded

import com.coded.ordering.UserEntity
import com.coded.authentication.JwtService
import com.coded.ordering.UsersRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.MultiValueMap
import kotlin.test.assertEquals



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldApplicationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var jwtService: JwtService

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(
            @Autowired usersRepository: UsersRepository,
            @Autowired passwordEncoder: PasswordEncoder,
        ){
            usersRepository.deleteAll()
            val testUser = UserEntity(
                username = "coded",
                password = passwordEncoder.encode("joincoded")
            )
            usersRepository.save(testUser)
        }
    }

    @Test
    fun helloWorld() {
        val token = jwtService.generateToken("coded")
        val headers = HttpHeaders(
            MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
        )
        val requestEntity = HttpEntity<String>(headers)

        val result = restTemplate.exchange(
            "/hello",
            HttpMethod.GET,
            requestEntity,
            String::class.java
        )

        assertEquals(expected = HttpStatus.OK, actual = result?.statusCode)
        assertEquals(expected = "Hello World", actual = result.body)
    }

}