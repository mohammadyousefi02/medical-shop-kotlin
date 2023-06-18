package com.medical.medicalshop.cartItem

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartItemRepository: JpaRepository<CartItem, Long> {
    fun findByUser_IdAndProduct_Id(userId: Long?, productId: Long?): Optional<CartItem?>?

    fun deleteByUser_Id(userId: Long?): Int
}