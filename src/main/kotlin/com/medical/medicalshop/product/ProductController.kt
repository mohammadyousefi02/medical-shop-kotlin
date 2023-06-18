package com.medical.medicalshop.product

import com.medical.medicalshop.auth.Authorization
import com.medical.medicalshop.exception.NotFoundException
import com.medical.medicalshop.response.Response
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(private val productService: ProductService) {
    @GetMapping
    fun getAll(): Response<List<Product>> {
        return Response(productService.findAll(), HttpStatus.OK.value())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Response<Product> {
        val product = productService.findById(id)
        if (product.isEmpty) throw NotFoundException("product does not exist")
        return Response(product.get(), HttpStatus.OK.value())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Authorization
    fun create(@RequestBody productDto: ProductDto): Response<Product> {
        productDto.id = 0
        val product = productService.save(productDto)
        return Response(product, HttpStatus.CREATED.value())
    }

    @PutMapping("/{id}")
    @Authorization
    fun update(@RequestBody productDto: ProductDto, @PathVariable id: Long): Response<Product> {
        productDto.id = id;
        val product = productService.save(productDto)
        return Response(product, HttpStatus.OK.value())
    }

    @DeleteMapping("/{id}")
    @Authorization
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long): Response<String> {
        val result: String = productService.deleteById(id)
        return Response(result, HttpStatus.NO_CONTENT.value())
    }
}