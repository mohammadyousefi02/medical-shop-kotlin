package com.medical.medicalshop.product

import com.medical.medicalshop.category.Category
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun findAll(): List<Product> {
        return productRepository.findAll();
    }

    fun findById(id: Long): Optional<Product> {
        return productRepository.findById(id);
    }

    fun save(productDto: ProductDto): Product {
        val product = Product()
        product.id = productDto.id!!
        product.title = productDto.title
        product.stock = productDto.stock
        product.madein = productDto.madein
        product.company = productDto.company
        product.description = productDto.description
        product.price = productDto.price
        val image = Image()
        image.url = productDto.image
        image.product = product
        val images = arrayOf(image)

        val category = Category()
        category.id = productDto.category
        val categories = arrayOf(category)

        product.setImages(images)
        product.categories = categories.toList()

        return productRepository.save(product)
    }

    fun deleteById(id: Long): String {
        productRepository.deleteById(id);
        return "successfully deleted"
    }
}