package com.coded.ordering

import jakarta.persistence.*


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UsersRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}


@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username"])])
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var username: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Enumerated(EnumType.STRING)
    var role: Roles = Roles.USER
) {
    constructor() : this(null, "", "", Roles.USER)
}

enum class Roles {
    USER, ADMIN
}
