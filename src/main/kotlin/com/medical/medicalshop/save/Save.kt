package com.medical.medicalshop.save

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.medical.medicalshop.product.Product
import com.medical.medicalshop.user.User
import jakarta.persistence.*

@Entity
class Save {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_id")
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("saves")
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null
}