package com.medical.medicalshop.save

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface SaveRepository: JpaRepository<Save, Long> {
    fun findByUser_IdAndProduct_Id(userId: Long?, productId: Long?): Optional<Save?>?
}