package com.medical.medicalshop.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.medical.medicalshop.cartItem.CartItem
import com.medical.medicalshop.order.Order
import com.medical.medicalshop.save.Save
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties("user")
    var cart: List<CartItem>? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties("user")
    var orders: List<Order>? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties("user")
    var saves: List<Save>? = null
}