package com.example.shoppingapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.viewModels.ShoppingAppViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ProfileScreenUI(viewModel: ShoppingAppViewModel = hiltViewModel(),firebaseAuth: FirebaseAuth)  {
    LaunchedEffect(key1 = true) {
        viewModel.getUserById(firebaseAuth.currentUser?.uid.toString())

    }

}