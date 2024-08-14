package com.example.shoppingapp.domain.useCase

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.CategoryModel
import com.example.shoppingapp.domain.repo.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: Repository) {
    fun getCategories(): Flow<ResultState<List<CategoryModel>>> {
        return repository.getCategories()
    }
}