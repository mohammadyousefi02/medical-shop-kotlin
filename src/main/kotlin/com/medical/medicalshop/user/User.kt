package com.medical.medicalshop.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0

    var username: String = ""

    @JsonIgnore
    var password: String = ""
    var email: String = ""
}