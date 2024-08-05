package com.example.shoppingapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.navigation

import com.example.shoppingapp.screens.LoginScreenUi
import com.example.shoppingapp.screens.ProfileScreenUI

import com.example.shoppingapp.screens.SingUpScreenUi
import com.google.firebase.auth.FirebaseAuth


@Composable
fun App(firebaseAuth: FirebaseAuth) {
    val navController = rememberNavController()


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        var startScreen = if (firebaseAuth.currentUser == null) {
            SubNavigation.LoginSignUpScreen
        } else {
            SubNavigation.MainHomeScreen
        }
        NavHost(navController = navController, startDestination = startScreen) {


            navigation<SubNavigation.LoginSignUpScreen>(startDestination = Routes.LoginScreen) {
                composable<Routes.LoginScreen> {
                    LoginScreenUi(navController = navController)
                }

                composable<Routes.SingUpScreen> {
                    SingUpScreenUi()
                }
            }
            navigation<SubNavigation.MainHomeScreen>(startDestination = Routes.ProfileScreen) {
                composable<Routes.HomeScreen> {


                }

                composable<Routes.ProfileScreen> {
                    ProfileScreenUI(firebaseAuth = firebaseAuth)
                }

                composable<Routes.WishListScreen> { }

                composable<Routes.CartScreen> { }
            }





            composable<Routes.ProductDetailsScreen> { }

            composable<Routes.CheckoutScreen> { }


        }

    }
}