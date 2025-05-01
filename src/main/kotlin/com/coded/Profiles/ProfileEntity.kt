package com.coded.Profiles
import com.coded.authentication.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "profiles")
data class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    val user: UserEntity,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @Column(name = "phone_number", nullable = false)
    val phoneNumber: String
)
