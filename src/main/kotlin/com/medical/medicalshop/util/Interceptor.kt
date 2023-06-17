package com.medical.medicalshop.util

import com.medical.medicalshop.auth.Authorization
import com.medical.medicalshop.exception.UnAuthorizedException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class Interceptor(private val jwtUtil: JwtUtil) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = (handler as HandlerMethod).method
        if (method.isAnnotationPresent(Authorization::class.java)) {
            val token: String = request.getHeader("Authorization")
            if (token == null || !token.startsWith("Bearer ") || !jwtUtil.isTokenValid(token.substring(7)))
                throw UnAuthorizedException("you can't access this endpoint without token")
            val userId = jwtUtil.extractId(token.substring(7));
            request.setAttribute("userId", userId);
        }
        return super.preHandle(request, response, handler)
    }
}