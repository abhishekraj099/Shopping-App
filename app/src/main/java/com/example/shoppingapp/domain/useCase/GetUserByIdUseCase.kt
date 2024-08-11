package com.example.shoppingapp.domain.useCase

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.UserDataParent
import com.example.shoppingapp.domain.repo.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByIdUserCase @Inject constructor(private val repository: Repository) {
    fun getUserById(uid: String) : Flow<ResultState<UserDataParent>> {
        return repository.getUserByUID(uid)
    }
}