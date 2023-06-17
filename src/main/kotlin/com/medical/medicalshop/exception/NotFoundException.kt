package com.medical.medicalshop.exception

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : CustomExceptionImpl(message, HttpStatus.NOT_FOUND)