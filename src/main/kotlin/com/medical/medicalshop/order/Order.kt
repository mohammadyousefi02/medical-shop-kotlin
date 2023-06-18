package com.medical.medicalshop.order

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIncludeProperties
import com.medical.medicalshop.user.User
import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIncludeProperties("id", "username", "email")
    var user: User? = null

    var name: String? = null
    var province: String? = null
    var city: String? = null
    var address: String? = null
    var zipcode: Long? = null
    var number: String? = null

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
    @JsonIgnoreProperties("order")
    var orderItems: List<OrderItem>? = null
}