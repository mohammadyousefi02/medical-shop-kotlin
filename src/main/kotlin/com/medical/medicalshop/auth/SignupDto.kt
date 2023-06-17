package com.medical.medicalshop.auth

import org.jetbrains.annotations.NotNull

class SignupDto {
    @NotNull
    lateinit var username: String;

    @NotNull
    lateinit var email: String;

    @NotNull
    lateinit var password: String;
}