package com.medical.medicalshop.category

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.medical.medicalshop.product.Product
import jakarta.persistence.*

@Entity
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    var id: Long? = null

    var title: String? = null

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties("parentCategory")
    var subcategories: List<Category>? = null

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties("subcategories")
    var parentCategory: Category? = null

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    var products: List<Product>? = null
}