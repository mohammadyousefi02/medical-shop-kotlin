package com.medical.medicalshop

import com.medical.medicalshop.user.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MedicalShopApplication

fun main(args: Array<String>) {
	val user = User();
	runApplication<MedicalShopApplication>(*args)
}
