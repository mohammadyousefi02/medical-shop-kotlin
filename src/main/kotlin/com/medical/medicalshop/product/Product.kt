package com.medical.medicalshop.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.medical.medicalshop.category.Category
import jakarta.persistence.*

@Entity
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    var id: Long = 0

    var title: String? = null
    var description: String? = null
    var price: Long? = null
    var stock = 0

    @Column(name = "made_in")
    var madein: String? = null
    var company: String? = null

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    private var images: List<Image>? = null

    fun setImages(images: Array<Image>) {
        this.images = images.toList();
    }

    fun getImages(): MutableList<String?>? {
        return images?.stream()?.map { i -> i.url }?.toList()
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "product_category",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    @JsonIgnoreProperties("products")
    var categories: List<Category>? = null
}