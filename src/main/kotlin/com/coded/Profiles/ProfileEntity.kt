package com.coded.Profiles
import com.coded.ordering.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "profiles")
data class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "last_name", nullable = false)
    val lastName: String,
    @Column(name = "phone_number", nullable = false)
    val phoneNumber: String,

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    val user: UserEntity
)