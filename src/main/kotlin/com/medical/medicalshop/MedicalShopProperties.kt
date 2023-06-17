package com.medical.medicalshop

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("medical-shop")
data class MedicalShopProperties(
    var secret: String = "",
    var expirationMs: Long = 0
)