package com.example.shoppingapp.domain.repo

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.CategoryModel
import com.example.shoppingapp.domain.models.ProductDataModels
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.domain.models.UserDataParent
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>

    fun loginUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>

    fun getUserByUID(uid: String): Flow<ResultState<UserDataParent>>

    fun updateUserData(userDataParent: UserDataParent): Flow<ResultState<String>>

    fun getAllProducts(): Flow<ResultState<List<ProductDataModels>>>

    fun getCategories(): Flow<ResultState<List<CategoryModel>>>

}
