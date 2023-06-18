package com.medical.medicalshop.product

import jakarta.persistence.*

@Entity
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    val id: Long? = null
    var url: String? = null

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null
}