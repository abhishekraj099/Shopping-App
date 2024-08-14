package com.example.shoppingapp.screens

//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.FavoriteBorder
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.shoppingapp.R
//import com.example.shoppingapp.navigation.Routes
//
//data class Dress(
//    val imageResId: Int,
//    val name: String,
//    val price: String
//)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreenUI(navController: NavHostController) {
//    val dresses = listOf(
//        Dress(R.drawable.dress1, "Polkadot Red Dress", "Rs. 1,499.00"),
//        Dress(R.drawable.dress2, "Striped Pink Dress", "Rs. 1,299.00"),
//        Dress(R.drawable.dress3, "Floral Blue Dress", "Rs. 1,699.00"),
//        Dress(R.drawable.dress4, "Solid Green Dress", "Rs. 1,399.00")
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF5F5F5))
//            .padding(16.dp)
//    ) {
//        TopBar(navController)
//        SearchBar()
//        CategoryButtons()
//        DressGrid(dresses, navController)
//    }
//}
//
//@Composable
//fun TopBar(navController: NavHostController) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            imageVector = Icons.Default.Menu,
//            contentDescription = "Menu",
//            tint = Color.Black,
//            modifier = Modifier.clickable { /* Open drawer or menu */ }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.user),
//            contentDescription = "User",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .clickable { navController.navigate(Routes.ProfileScreen) }
//        )
//    }
//    Text(
//        text = "Match your style",
//        fontWeight = FontWeight.Bold,
//        fontSize = 24.sp,
//        modifier = Modifier.padding(bottom = 16.dp)
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SearchBar() {
//    TextField(
//        value = "",
//        onValueChange = {},
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Default.Search,
//                contentDescription = null,
//                tint = Color.Gray
//            )
//        },
//        placeholder = { Text("Search") },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp),
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color.White,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent
//        ),
//        shape = RoundedCornerShape(8.dp)
//    )
//}
//
//@Composable
//fun CategoryButtons() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        CategoryButton("Trending Now", Color(0xFFFF69B4), true)
//        CategoryButton("All", Color.LightGray)
//        CategoryButton("New", Color.LightGray)
//    }
//}
//
//@Composable
//fun CategoryButton(text: String, color: Color, isSelected: Boolean = false) {
//    Button(
//        onClick = { /* TODO: Implement category filtering */ },
//        colors = ButtonDefaults.buttonColors(containerColor = color),
//        shape = RoundedCornerShape(20.dp),
//        modifier = Modifier.padding(end = 8.dp)
//    ) {
//        Text(text, color = if (isSelected) Color.White else Color.Black)
//    }
//}
//
//@Composable
//fun DressGrid(dresses: List<Dress>, navController: NavHostController) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        horizontalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        items(dresses) { dress ->
//            DressItemCard(dress, navController)
//        }
//    }
//}
//
//@Composable
//fun DressItemCard(dress: Dress, navController: NavHostController) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                navController.navigate(Routes.ProductDetailsScreen)
//                // You might want to pass the dress details to the product screen
//                // navController.navigate("${Routes.ProductDetailsScreen}/${dress.id}")
//            },
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Column {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = dress.imageResId),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )
//                Icon(
//                    imageVector = Icons.Default.FavoriteBorder,
//                    contentDescription = "Favorite",
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .align(Alignment.TopEnd)
//                        .clickable { /* TODO: Add to favorites */ },
//                    tint = Color.Red
//                )
//            }
//            Column(modifier = Modifier.padding(12.dp)) {
//                Text(text = dress.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
//                Text(text = dress.price, color = Color.Gray, fontSize = 14.sp)
//            }
//        }
//    }
//}