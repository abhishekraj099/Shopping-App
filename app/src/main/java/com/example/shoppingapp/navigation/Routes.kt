package com.example.shoppingapp.navigation

import kotlinx.serialization.Serializable

sealed class SubNavigation{
    @Serializable
    object LoginSignUpScreen:SubNavigation()

    @Serializable
    object MainHomeScreen:SubNavigation()
}

sealed class Routes {
    @Serializable
    object LoginScreen

    @Serializable
    object SingUpScreen

    @Serializable
    object ProfileScreen

    @Serializable
    object HomeScreen

    @Serializable
    object WishListScreen

    @Serializable
    object CartScreen

    @Serializable
    object ProductDetailsScreen

    @Serializable
    object CheckoutScreen

}