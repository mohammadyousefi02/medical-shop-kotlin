package com.medical.medicalshop.save

import com.medical.medicalshop.exception.NotFoundException
import com.medical.medicalshop.product.Product
import com.medical.medicalshop.product.ProductService
import com.medical.medicalshop.user.User
import com.medical.medicalshop.user.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SaveService(
    private val saveRepository: SaveRepository,
    private val userService: UserService,
    private val productService: ProductService
) {
    fun save(userId: Long?, productId: Long?): String? {
        val existedSave = saveRepository.findByUser_IdAndProduct_Id(userId, productId)
        return if (existedSave!!.isPresent) {
            saveRepository.delete(existedSave.get())
            return "successfully deleted"
        } else {
            val user: Optional<User> = userService.findById(userId!!)
            if (user.isEmpty) throw NotFoundException("user does not found")
            val product: Optional<Product> = productService.findById(productId!!)
            if (product.isEmpty) throw NotFoundException("product does not found")
            val save = Save()
            save.user = user.get()
            save.product = product.get()
            saveRepository.save(save)
            return "successfully added"
        }
    }
}