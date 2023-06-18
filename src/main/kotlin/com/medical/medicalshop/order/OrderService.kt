package com.medical.medicalshop.order

import com.medical.medicalshop.cartItem.CartItem
import com.medical.medicalshop.cartItem.CartItemService
import com.medical.medicalshop.exception.BadRequestException
import com.medical.medicalshop.exception.NotFoundException
import com.medical.medicalshop.product.Product
import com.medical.medicalshop.user.User
import com.medical.medicalshop.user.UserService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userService: UserService,
    private val cartItemService: CartItemService
) {
    @Transactional
    fun addOrder(orderDTO: OrderDto, userId: Long?): Order? {
        val user: Optional<User> = userService.findById(userId!!)
        if (user.isEmpty) throw NotFoundException("user does not exist")
        var order = Order()
        order.address = orderDTO.address
        order.name = orderDTO.name
        order.city = orderDTO.city
        order.province = orderDTO.province
        order.zipcode = orderDTO.zipcode
        order.number = orderDTO.number
        order.user = user.get()
        val cartItems: List<CartItem> = user.get().cart as List<CartItem>
        val orderItems: MutableList<OrderItem> = ArrayList()
        if (cartItems.isEmpty()) throw BadRequestException("you should have at least an item in cart")
        for (cartItem in cartItems) {
            val orderItem = OrderItem()
            val product: Product? = cartItem.product
            orderItem.product = product;
            orderItem.company = product?.company
            orderItem.price = product?.price
            orderItem.title = product?.title
            orderItem.madein = product?.madein
            orderItem.quantity = cartItem.quantity
            orderItem.order = order
            orderItems.add(orderItem)
        }
        order.orderItems = orderItems
        order = orderRepository.save(order)
        cartItemService.clear(userId)
        return order
    }
}