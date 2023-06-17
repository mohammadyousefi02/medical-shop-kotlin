package com.medical.medicalshop.exception

import org.springframework.http.HttpStatus

class BadRequestException(message: String) : CustomExceptionImpl(message, HttpStatus.BAD_REQUEST)