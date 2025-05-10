package com.coded.ordering

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class OrderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val customerName: String,
    val restaurant: String,
    val items: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)



