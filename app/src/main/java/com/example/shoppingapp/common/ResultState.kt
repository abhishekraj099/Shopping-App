package com.example.shoppingapp.common

sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val exception: String) : ResultState<String>()
    data object Loading : ResultState<Nothing>()

}