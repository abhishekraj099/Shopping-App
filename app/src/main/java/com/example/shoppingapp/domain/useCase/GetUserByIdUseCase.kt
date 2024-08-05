package com.example.shoppingapp.domain.useCase

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.UserDataParent
import com.example.shoppingapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(val repo: Repo) {
    fun getUserById(uId: String) : Flow<ResultState<UserDataParent>> {
        return repo.getUserByUId(uId)

    }
}