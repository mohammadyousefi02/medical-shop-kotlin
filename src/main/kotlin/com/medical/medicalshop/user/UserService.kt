package com.medical.medicalshop.user

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers():List<User> {
        return userRepository.findAll();
    }
}