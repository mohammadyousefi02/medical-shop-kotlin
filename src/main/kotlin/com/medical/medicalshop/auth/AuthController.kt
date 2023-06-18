package com.medical.medicalshop.auth

import com.medical.medicalshop.exception.BadRequestException
import com.medical.medicalshop.response.Response
import com.medical.medicalshop.user.UserService
import com.medical.medicalshop.util.JwtUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): Response<TokenDto> {
        val user = userService.findByEmail(loginDto.email)
        if (user.isEmpty) throw BadRequestException("username or password is incorrect")
        val isPasswordValid = passwordEncoder.matches(loginDto.password, user.get().password)
        if (!isPasswordValid) throw BadRequestException("username or password is incorrect")
        val token: String = jwtUtil.generateToken(user.get())
        val tokenDto = TokenDto(token)
        return Response(tokenDto, HttpStatus.OK.value());
    }

    @PostMapping("/signup")
    fun signup(@RequestBody signupDto: SignupDto): Response<TokenDto> {
        val user = userService.signup(signupDto) ?: throw BadRequestException("this user is already exist");
        val token = jwtUtil.generateToken(user);
        val tokenDto = TokenDto(token)
        return Response(tokenDto, HttpStatus.OK.value())
    }

    @PostMapping("/refreshToken")
    @Authorization
    fun refreshToken(request: HttpServletRequest): Long {
        TODO()
        return request.getAttribute("userId") as Long
    }
}