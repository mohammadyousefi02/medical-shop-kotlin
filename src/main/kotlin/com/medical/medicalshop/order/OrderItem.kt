package com.medical.medicalshop.order

import com.medical.medicalshop.product.Product
import jakarta.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    var id: Long? = null

    var title: String? = null
    var price: Long? = null
    var quantity = 0

    @Column(name = "made_in")
    var madein: String? = null
    var company: String? = null

    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order? = null

    @OneToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null
}