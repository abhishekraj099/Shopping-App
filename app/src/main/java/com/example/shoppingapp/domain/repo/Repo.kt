package com.example.shoppingapp.domain.repo

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface Repo  {
    fun registerUserWithEmailAndPassword(userData: UserData) :Flow<ResultState<String>>
}