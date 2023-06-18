package com.medical.medicalshop.order

import com.medical.medicalshop.auth.Authorization
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(private val orderService: OrderService) {
    @PostMapping
    @Authorization
    fun order(@RequestBody orderDTO: OrderDto, request: HttpServletRequest): Order? {
        val order = orderService.addOrder(orderDTO, request.getAttribute("userId") as Long)
        return order
    }
}