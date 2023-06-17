package com.medical.medicalshop.exception

import org.springframework.http.HttpStatus

open class CustomExceptionImpl(message: String, private val httpStatus: HttpStatus) : RuntimeException(message),
    CustomException {
    override fun getHttpStatus(): HttpStatus {
        return httpStatus;
    }

    override fun getErrMessage(): String? {
        return message;
    }
}