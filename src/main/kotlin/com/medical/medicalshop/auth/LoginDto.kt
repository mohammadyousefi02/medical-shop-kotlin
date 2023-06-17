package com.medical.medicalshop.auth

import org.jetbrains.annotations.NotNull

class LoginDto {
    @NotNull
    lateinit var email: String

    @NotNull
    lateinit var password: String
}