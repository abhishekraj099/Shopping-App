package com.example.shoppingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.navigation.Routes
import com.example.shoppingapp.navigation.SubNavigation
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shoppingapp.viewModels.ShoppingAppViewModel


import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SignUpScreenUI(
    viewModel: ShoppingAppViewModel = hiltViewModel(),
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val primaryColor = Color(0xFF42A5F5)
    val backgroundColor = Color(0xFFF5F5F5)

    LaunchedEffect(state.success) {
        if (state.success != null) {
            navController.navigate(SubNavigation.MainHomeScreen)
            viewModel.resetState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        SignUpContent(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onNameChange = { name = it },
            onEmailChange = { email = it },
            onPhoneChange = { phone = it },
            onPasswordChange = { password = it },
            onSignUp = {
                val userData = UserData(name = name, email = email, password = password, phone = phone)
                viewModel.createUser(userData)
            },
            onNavigateToLogin = { navController.navigate(Routes.LoginScreen) },
            primaryColor = primaryColor,
            isLoading = state.isLoading,
            error = state.error
        )
    }
}

@Composable
fun SignUpContent(
    name: String,
    email: String,
    phone: String,
    password: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit,
    onNavigateToLogin: () -> Unit,
    primaryColor: Color,
    isLoading: Boolean,
    error: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpHeader()
        SignUpForm(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onNameChange = onNameChange,
            onEmailChange = onEmailChange,
            onPhoneChange = onPhoneChange,
            onPasswordChange = onPasswordChange,
            onSignUp = onSignUp,
            primaryColor = primaryColor,
            isLoading = isLoading
        )
        if (error != null) {
            Text(text = error, color = Color.Red, style = MaterialTheme.typography.bodyMedium)
        }
        SignUpSocialOptions()
        SignUpLoginOption(onNavigateToLogin = onNavigateToLogin)
    }
}

@Composable
fun SignUpHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = Icons.Default.PersonAdd,
            contentDescription = "Sign Up",
            modifier = Modifier.size(64.dp),
            tint = Color(0xFF42A5F5)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Create an Account",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF333333)
        )
    }
}

@Composable
fun SignUpForm(
    name: String,
    email: String,
    phone: String,
    password: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit,
    primaryColor: Color,
    isLoading: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SignUpTextField(
            value = name,
            onValueChange = onNameChange,
            label = "Full Name",
            icon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(16.dp))
        SignUpTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            icon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(16.dp))
        SignUpTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = "Phone Number",
            icon = Icons.Default.Phone
        )
        Spacer(modifier = Modifier.height(16.dp))
        SignUpTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Password",
            icon = Icons.Default.Lock,
            isPassword = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onSignUp,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(8.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(text = "Sign Up", color = Color.White)
            }
        }
    }
}

@Composable
fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = { Icon(icon, contentDescription = null) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
    )
}

@Composable
fun SignUpSocialOptions() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "— Or Sign Up with —", color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SignUpSocialButton(imageResId = R.drawable.ic_facebook)
            SignUpSocialButton(imageResId = R.drawable.ic_google)
            SignUpSocialButton(imageResId = R.drawable.ic_apple)
        }
    }
}

@Composable
fun SignUpSocialButton(imageResId: Int) {
    IconButton(
        onClick = { /* Handle social sign up */ },
        modifier = Modifier
            .size(48.dp)
            .background(Color.White, CircleShape)
            .border(1.dp, Color.LightGray, CircleShape)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Social Sign Up",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SignUpLoginOption(onNavigateToLogin: () -> Unit) {
    TextButton(onClick = onNavigateToLogin) {
        Text(
            text = "Already have an account? Log In",
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
    }
}// Keep the other functions (SignUpTextField, SignUpSocialOptions, SignUpSocialButton, SignUpLoginOption) as they were