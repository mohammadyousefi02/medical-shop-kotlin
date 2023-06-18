package com.medical.medicalshop.category

import com.medical.medicalshop.auth.Authorization
import com.medical.medicalshop.exception.NotFoundException
import com.medical.medicalshop.response.Response
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(private val categoryService: CategoryService) {
    @GetMapping
    fun findAll(): Response<List<Category>> {
        val categories = categoryService.findAll()
        return Response(categories, HttpStatus.OK.value())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Response<Category> {
        val category = categoryService.findById(id) ?: throw NotFoundException("category does not exist")
        return Response(category, HttpStatus.OK.value())
    }

    @PostMapping
    @Authorization
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody categoryDto: CategoryDto): Response<Category> {
        val category = categoryService.create(categoryDto)
        return Response(category, HttpStatus.CREATED.value())
    }

    @DeleteMapping("/{id}")
    @Authorization
    fun delete(@PathVariable id: Long): Response<String> {
        val result = categoryService.delete(id)
        return Response(result, HttpStatus.NO_CONTENT.value())
    }
}