package com.coded.authentication

import jakarta.persistence.*

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username"])])
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var age: Int,

    @Column(nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER

) {
    constructor() : this(null, "", 0, "", "", Role.USER)
}

enum class Role {
    USER,
    ADMIN
}
