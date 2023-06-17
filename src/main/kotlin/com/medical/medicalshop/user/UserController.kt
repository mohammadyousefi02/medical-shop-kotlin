package com.medical.medicalshop.user

import com.medical.medicalshop.auth.Authorization
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {
    @GetMapping
    @Authorization
    fun getAllUsers(): List<User> {
        return userService.getAllUsers();
    }
}