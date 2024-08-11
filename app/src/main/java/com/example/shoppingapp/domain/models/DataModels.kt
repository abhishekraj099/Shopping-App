package com.example.shoppingapp.domain.models


data class UserData(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",

)

data class UserDataParent(
    val nodeId: String = "",
    val userData: UserData = UserData(),
)