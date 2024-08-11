package com.example.shoppingapp.domain.models

import kotlinx.serialization.Serializable


data class ProductDataModels(
    var name: String = "",
    var price: String = "",
    var image: String = "",
    var category: String = "",
    var description: String = "",
    val date: Long = System.currentTimeMillis(),
    val createdBy: String = "",
    val imageUrl: String = ""

)