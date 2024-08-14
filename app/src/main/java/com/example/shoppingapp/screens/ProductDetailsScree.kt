package com.example.shoppingapp.screens

import com.example.shoppingapp.R




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductDetailsScreenPreview() {
    ProductDetailsScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Empty title space */ },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    // Add more icons here if needed
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Apply Scaffold padding
        ) {
            ProductImageSection()
            ProductInfoSection()
        }
    }
}

@Composable
fun ProductImageSection() {
    Image(
        painter = painterResource(id = R.drawable.dress_image), // Replace with actual image
        contentDescription = "Product Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProductInfoSection() {
    val availableSizes = listOf("UK 8", "UK 10", "UK 12")
    val availableColors = listOf(
        Color(0xFFFFC0CB), // Light Pink
        Color(0xFF40E0D0), // Turquoise
        Color(0xFF98EE99), // Light Green
        Color(0xFFFFFFE0) // Light Yellow
    )
    var selectedSize by remember { mutableStateOf("UK 8") } // Default size
    var selectedColor by remember { mutableStateOf(availableColors[0]) } // Default color
    var quantity by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "One Shoulder Linen Dress",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        // Replace with actual rating
        Row {
            repeat(5) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_filled), // Replace with filled star icon
                    contentDescription = "Star",
                    tint = Color(0xFFFFC800), // Yellow color for the stars
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Rs 5740", // Replace with actual price
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Size Selection
        Text(text = "Size", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(availableSizes.size) { index ->
                val size = availableSizes[index]
                OutlinedButton(
                    onClick = { selectedSize = size },
                    modifier = Modifier
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(16.dp), // Rounded corners for buttons
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (selectedSize == size) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                ) {
                    Text(text = size)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Color Selection
        Text(text = "Color", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(availableColors.size) { index ->
                val color = availableColors[index]
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color)
                        .padding(4.dp), // Optional padding for visual separation
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedColor == color) {
                        // Show a checkmark or a different border when selected
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = "Selected Color",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quantity Selection
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (quantity > 1) quantity-- }) {
                Icon(Icons.Filled.Remove, "Decrease quantity")
            }
            Text(text = quantity.toString())
            IconButton(onClick = { quantity++ }) {
                Icon(Icons.Filled.Add, "Increase quantity")
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "See more",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Specification", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        ProductSpecificationItem("Dress")
        ProductSpecificationItem("Material: Linen")
        ProductSpecificationItem("Material Composition: 100% Linen")

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Please bear in mind that the photo may be slightly different from the actual item in terms of color due to lighting conditions or the display used to view.",
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle 'Buy Now' action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(0.dp) // Remove default padding
        ) {
            Text(
                text = "Buy now",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Handle 'Add to Cart' action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray // Use LightGray for 'Add to Cart'
            ),
            contentPadding = PaddingValues(0.dp) // Remove default padding
        ) {
            Text(
                text = "Add to Cart",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder, // Or Icons.Filled.Favorite for filled heart
                contentDescription = "Add to Wishlist",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add to Wishlist",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ProductSpecificationItem(text: String) {
    Text(
        text = text,
        fontSize = 14.sp
    )
}