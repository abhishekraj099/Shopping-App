package com.example.shoppingapp.domain.models

data class UserData(
    val name: String,
    val email: String,
    val password: String,
    var phone: String
)