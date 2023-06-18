package com.medical.medicalshop.category

import com.medical.medicalshop.exception.BadRequestException
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

    fun findById(id: Long): Category? {
        val category = categoryRepository.findById(id)
        if (category.isEmpty) return null
        return category.get()
    }

    fun create(categoryDto: CategoryDto): Category {
        val existedCategory = categoryDto.title?.let { categoryRepository.findByTitle(it) }
        if (existedCategory?.isPresent == true)
            throw BadRequestException("already we have a category with this title")
        val category = Category()
        category.title = categoryDto.title
        if (categoryDto.parentCategory != null) {
            val parentCategory = Category()
            parentCategory.id = categoryDto.parentCategory
            category.parentCategory = parentCategory
        }
        return categoryRepository.save(category)
    }

    fun delete(id: Long): String {
        categoryRepository.deleteById(id)
        return "successfully deleted"
    }
}