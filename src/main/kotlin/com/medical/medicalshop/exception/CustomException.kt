package com.medical.medicalshop.exception

import org.springframework.http.HttpStatus

interface CustomException {
    fun getHttpStatus(): HttpStatus
    fun getErrMessage(): String?
}