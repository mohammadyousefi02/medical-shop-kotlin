package com.medical.medicalshop.category

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByTitle(title: String): Optional<Category>
}