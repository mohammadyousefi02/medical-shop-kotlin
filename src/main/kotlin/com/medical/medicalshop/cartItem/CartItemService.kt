package com.medical.medicalshop.cartItem

import com.medical.medicalshop.exception.NotFoundException
import com.medical.medicalshop.product.Product
import com.medical.medicalshop.product.ProductService
import com.medical.medicalshop.response.Response
import com.medical.medicalshop.user.User
import com.medical.medicalshop.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CartItemService(
    private val cartItemRepository: CartItemRepository,
    private val userService: UserService,
    private val productService: ProductService
) {
    @Transactional
    fun addToCart(userId: Long?, productId: Long?): Response<CartItem?>? {
        val cartItem: Optional<CartItem?>? = cartItemRepository.findByUser_IdAndProduct_Id(userId, productId)
        return if (cartItem?.isPresent == true) {
            cartItem.get().increaseQuantity()
            return Response(cartItemRepository.save(cartItem.get()), HttpStatus.OK.value())
        } else {
            val user: Optional<User> = Optional.ofNullable(userService.findById(userId!!).orElseThrow {
                NotFoundException(
                    "User not found"
                )
            })
            val product: Optional<Product> = Optional.ofNullable(productService.findById(productId!!).orElseThrow {
                NotFoundException("Product not found")
            })
            var newCartItem = CartItem()
            newCartItem.quantity = 1
            newCartItem.user = user.get()
            newCartItem.product = product.get()
            newCartItem = cartItemRepository.save(newCartItem)
            return Response(newCartItem, HttpStatus.CREATED.value())
        }
    }

    @Transactional
    fun decrease(userId: Long?, productId: Long?): CartItem? {
        val cartItem: Optional<CartItem> =
            Optional.ofNullable(cartItemRepository.findByUser_IdAndProduct_Id(userId, productId)?.orElseThrow {
                NotFoundException("not found")
            })
        cartItem.get().decreaseQuantity()
        if (cartItem.get().quantity === 0) {
            cartItemRepository.delete(cartItem.get())
            return cartItem.get()
        }
        return cartItemRepository.save(cartItem.get())
    }

    @Transactional
    fun deleteOneProduct(userId: Long?, productId: Long?): String? {
        val cartItem: Optional<CartItem> =
            Optional.ofNullable(cartItemRepository.findByUser_IdAndProduct_Id(userId, productId)?.orElseThrow {
                NotFoundException("not found")
            })
        cartItemRepository.delete(cartItem.get())
        return "ok"
    }

    @Transactional
    fun clear(userId: Long?): String? {
        cartItemRepository.deleteByUser_Id(userId)
        return "ok"
    }
}