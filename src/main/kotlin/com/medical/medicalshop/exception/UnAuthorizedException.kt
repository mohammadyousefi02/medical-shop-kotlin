package com.medical.medicalshop.exception

import org.springframework.http.HttpStatus

class UnAuthorizedException(message: String) : CustomExceptionImpl(message, HttpStatus.UNAUTHORIZED)