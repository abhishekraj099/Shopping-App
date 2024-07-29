package com.example.shoppingapp.navigation

import kotlinx.serialization.Serializable


sealed class Routes {

    @Serializable
    object LoginScreen : Routes()

    @Serializable
    object SignUpScreen : Routes()


    @Serializable
    object ProfileScreen : Routes()


    @Serializable
    object HomeScreen : Routes()

    @Serializable
    object WishlistScreen : Routes()

    @Serializable
    object CartScreen : Routes()

    @Serializable
    object ProductDetailScreen : Routes()

    @Serializable
    object CheckOutScreen : Routes()

}