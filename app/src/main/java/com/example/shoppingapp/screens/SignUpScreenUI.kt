package com.example.shoppingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.R

@Preview(showBackground = true)
@Composable
fun SignUpScreenUI() {
    var name = remember {
        mutableStateOf("")
    }

    var password = remember {
        mutableStateOf("")
    }
    var email = remember {
        mutableStateOf("")
    }
    var phone = remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ellipse1), contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .size(400.dp)  // Adjust size as needed
                .offset(x = 100.dp, y = (-120).dp)  // Adjust offset as needed
                .clip(CircleShape)
                .align(Alignment.TopEnd),
            alignment = Alignment.TopEnd
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Person, contentDescription = null,
                modifier = Modifier.size(160.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = name.value, onValueChange = { name.value = it })
            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = email.value, onValueChange = { email.value = it })
            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = password.value, onValueChange = { password.value = it })
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { "SignUp" }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Sign Up", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp))
            }
          Box(modifier = Modifier.fillMaxSize()){

          }

        }
    }
}