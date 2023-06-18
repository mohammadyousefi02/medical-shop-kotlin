package com.medical.medicalshop.cartItem

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.medical.medicalshop.product.Product
import com.medical.medicalshop.user.User
import jakarta.persistence.*

@Entity
@Table(name = "cart_item")
class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    var id: Long? = null

    var quantity = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties("cart", "orders")
    var user: User? = null

    fun increaseQuantity() {
        quantity++
    }

    fun decreaseQuantity() {
        quantity--
    }
}