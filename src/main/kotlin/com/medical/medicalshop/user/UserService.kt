package com.medical.medicalshop.user

import com.medical.medicalshop.auth.SignupDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun signup(signupDto: SignupDto): User? {
        val user = findByEmail(signupDto.email)
        if (user.isPresent) return null
        val newUser = User()
        newUser.username = signupDto.username
        newUser.email = signupDto.email
        newUser.password = passwordEncoder.encode(signupDto.password)
        return userRepository.save(newUser)
    }
}