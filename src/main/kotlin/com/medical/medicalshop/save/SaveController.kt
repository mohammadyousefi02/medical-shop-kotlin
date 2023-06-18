package com.medical.medicalshop.save

import com.medical.medicalshop.auth.Authorization
import com.medical.medicalshop.response.Response
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/save")
class SaveController(private val saveService: SaveService) {
    @PostMapping("/{productId}")
    @Authorization
    fun save(@PathVariable productId: Long?, request: HttpServletRequest): Response<String?>? {
        val result = saveService.save(request.getAttribute("userId") as Long, productId)
        return Response(result, 200)
    }
}