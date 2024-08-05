package com.example.shoppingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.navigation.Routes
import com.example.shoppingapp.navigation.SubNavigation
import com.example.shoppingapp.screens.utils.CustomTextField
import com.example.shoppingapp.viewModels.ShoppingAppViewModel

@Composable
fun LoginScreenUi(
    viewModel: ShoppingAppViewModel = hiltViewModel(), navController: NavHostController
) {

    val state = viewModel.loginScreenState.collectAsStateWithLifecycle()
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (state.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else if (state.value.errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = state.value.errorMessage!!)
        }
    } else if (state.value.userData != null) {
        AlertDialog(onDismissRequest = { }, confirmButton = {
            Button(onClick = {
                navController.navigate(SubNavigation.MainHomeScreen)
            }) {
                Text(text = "Go to Home")
            }
        }, title = { Text(text = "Congrats Login Success") })
    } else {


        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.Start)
            )


            Spacer(modifier = Modifier.padding(8.dp))


            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = Icons.Default.Email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            )
            Spacer(modifier = Modifier.padding(8.dp))

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Create Password",
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                leadingIcon = Icons.Default.Lock,
            )
            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Forgot Password?",
                modifier = Modifier.align(Alignment.End),
            )


            Spacer(modifier = Modifier.padding(16.dp))








            Button(
                onClick = {
                    val userData = UserData(
                        email = email,
                        password = password,
                        name = "",
                        phoneNumber = ""
                    )
                    viewModel.loginUser(userData)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA5A5))
            ) {
                Text("Login", color = Color.White)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account?")
                TextButton(onClick = {
                    navController.navigate(Routes.SingUpScreen)
                }) {
                    Text("Signup", color = Color(0xFFFFA5A5))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text("OR", modifier = Modifier.padding(horizontal = 8.dp))
                HorizontalDivider(modifier = Modifier.weight(1f))
            }


            OutlinedButton(
                onClick = { /* Handle Google login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),

                ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text("Log in with Google")
            }
        }
    }
}