package com.coded.authentication

import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}
