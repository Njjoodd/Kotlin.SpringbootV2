package com.coded.spring.ordering

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Int>


@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "user_id")
    var userId: Int,
    var restaurant: String,
    var items: String,
    ) {
        constructor() : this(0, 0, "", "")
    }




