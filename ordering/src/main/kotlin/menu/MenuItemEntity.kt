package com.coded.menu

import jakarta.persistence.*

@Entity
@Table(name = "menu")
data class MenuItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column (nullable = false)
    val name: String,
    @Column(nullable = false)
    val price: Double
)