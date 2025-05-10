package com.coded.Profiles

import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<ProfileEntity, Long> {
    fun findByUserId(userId: Long): ProfileEntity?
}
