package com.example.shoppingapp.domain.useCase

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase  @Inject constructor(private val repo: Repo) {

    fun loginUser(userData: UserData) : Flow<ResultState<String>> {
        return repo.loginUserWithEmailAndPassword(userData)
    }
}