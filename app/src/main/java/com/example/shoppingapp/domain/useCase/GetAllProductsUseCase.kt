package com.example.shoppingapp.domain.useCase

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.ProductDataModels
import com.example.shoppingapp.domain.repo.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(private val repository: Repository) {
    fun getAllProducts(): Flow<ResultState<List<ProductDataModels>>>{
        return repository.getAllProducts()
    }
}