package com.medical.medicalshop.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CustomExceptionImpl::class)
    fun handleException(exception: CustomException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(exception.getErrMessage()!!, exception.getHttpStatus().value(), System.currentTimeMillis())
        return ResponseEntity(errorResponse, exception.getHttpStatus())
    }
}