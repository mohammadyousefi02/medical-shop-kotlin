package com.medical.medicalshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(MedicalShopProperties::class)
class MedicalShopApplication

fun main(args: Array<String>) {
    runApplication<MedicalShopApplication>(*args)
}
