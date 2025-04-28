package com.coded.ordering

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
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



