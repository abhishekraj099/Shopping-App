package com.example.shoppingapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.navigation

@Composable
fun App(modifier: Modifier){
    val navController = rememberNavController()
    
    Box(modifier = Modifier.fillMaxSize()
    ){
        NavHost(navController =navController , startDestination =Routes.LoginScreen ) {

            composable<Routes.LoginScreen> {

            }

            composable<Routes.SignUpScreen> {

            }


        }
    }
}