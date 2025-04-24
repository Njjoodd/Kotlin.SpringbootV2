package com.coded.ordering

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var age: Int,
    var username: String,
    var password: String,

    @Enumerated(EnumType.STRING)
    val role: Roles = Roles.USER
) {
    constructor() : this(null, "", 0, "", "", Roles.USER)

}

enum class Roles {
    USER, ADMIN
}
