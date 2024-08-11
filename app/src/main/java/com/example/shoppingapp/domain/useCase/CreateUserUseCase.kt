package com.example.shoppingapp.domain.useCase

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.domain.repo.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repository: Repository ) {
    fun createUser(userData: UserData): Flow<ResultState<String>> {
        return repository.registerUserWithEmailAndPassword(userData)
    }

    fun loginUser(userData: UserData): Flow<ResultState<String>> {
        return repository.loginUserWithEmailAndPassword(userData)
    }
}


