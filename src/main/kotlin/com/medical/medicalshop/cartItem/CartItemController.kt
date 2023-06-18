package com.medical.medicalshop.cartItem

import com.medical.medicalshop.auth.Authorization
import com.medical.medicalshop.response.Response
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cart")
class CartItemController(private val cartItemService: CartItemService) {
    @PostMapping("/add")
    @Authorization
    fun addToCart(@RequestBody cartBody: CartDto, request: HttpServletRequest): ResponseEntity<Response<CartItem>>? {
        val response: Response<CartItem?>? =
            cartItemService.addToCart(request.getAttribute("userId") as Long, cartBody.productId)
        return response?.let {
            ResponseEntity.status(response.status).body(response as Response<CartItem>)
        }
    }

    @PostMapping("/decrease")
    @Authorization
    fun decrease(@RequestBody cartBody: CartDto, request: HttpServletRequest): Response<String?>? {
        cartItemService.decrease(request.getAttribute("userId") as Long, cartBody.productId)
        return Response("ok", HttpStatus.OK.value())
    }

    @DeleteMapping("/delete-one-product")
    @Authorization
    fun deleteOneProduct(@RequestBody cartBody: CartDto, request: HttpServletRequest): Response<String?>? {
        val cartItem: String? =
            cartItemService.deleteOneProduct(request.getAttribute("userId") as Long, cartBody.productId)
        return Response(cartItem, HttpStatus.OK.value())
    }

    @DeleteMapping("/clear")
    @Authorization
    fun clear(request: HttpServletRequest): Response<String?>? {
        cartItemService.clear(request.getAttribute("userId") as Long)
        return Response("ok", HttpStatus.OK.value())
    }
}