package com.example.shoppingapp.screens


// Define the Dress data class



import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Voicemail
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.models.ProductDataModels
import com.example.shoppingapp.viewModels.ShoppingAppViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.pager.PagerState



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenUI(
    viewModel: ShoppingAppViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val productUiState by viewModel.productUiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState()
    val uniqueCategories by viewModel.uniqueCategories.collectAsStateWithLifecycle()
    val allCategories by viewModel.getAllCategories.collectAsStateWithLifecycle()
    val usingCategories = allCategories.categoryList
    val bannerImages = listOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3
    )
    val products = productUiState.productList
    val selectedCategory = remember { mutableStateOf("All") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle search input here */ },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    placeholder = { Text("Search") },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = { /* Handle voice search action here */ }) {
                    Icon(
                        imageVector = Icons.Filled.Voicemail,
                        contentDescription = "Voice Search",
                        tint = Color(0xFF808080)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }


        // Image Slider (New Collection)
        item {
            HorizontalPager(
                count = bannerImages.size,
                state = pagerState, // Use the pagerState
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) { page ->
                // Our page content, displaying a random image
                AsyncImage(
                    model = bannerImages[page],
                    contentDescription = "Banner Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Pager Indicators
            DotsIndicator(
                totalDots = bannerImages.size,
                currentPage = pagerState.currentPage,
                selectedColor = Color.DarkGray,
                unSelectedColor = Color.LightGray
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }


        item {
            // Category Section
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Category",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(uniqueCategories) { category ->
                        CategoryItem(
                            category = category,
                            isSelected = category == selectedCategory.value,
                            onSelect = { selectedCategory.value = it }
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            // Flash Sale Section
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Flash Sale Closing In",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "2 : 12 : 56", // Replace with your actual countdown timer
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Red
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(uniqueCategories) { category ->
                        FilterChip(
                            category = category,
                            isSelected = category == selectedCategory.value,
                            onSelect = { selectedCategory.value = it })
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }


        item {
            val filteredProducts = if (selectedCategory.value == "All") {
                products
            } else {
                products?.filter { it.category == selectedCategory.value }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.height(500.dp)
            ) {
                filteredProducts?.let { list ->
                    items(list) { product ->
                        ProductCard(product = product)
                    }
                }
            }
        }

    }
}


// Category Item Composable
@Composable
fun CategoryItem(category: String, isSelected: Boolean, onSelect: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedButton(
            onClick = { onSelect(category) },
            modifier = Modifier
                .size(80.dp)
                .padding(4.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, if (isSelected) Color.Blue else Color.LightGray),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isSelected) Color.Blue else Color.White
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            AsyncImage(
                model = R.drawable.ic_launcher_foreground, // Replace with actual category Image
                contentDescription = category,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = category, fontSize = 12.sp, modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
    }
}

// Filter Chip Composable
@Composable
fun FilterChip(category: String, isSelected: Boolean, onSelect: (String) -> Unit) {
    Button(
        onClick = { onSelect(category) },
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFFADD8E6) else Color.White
        )
    ) {
        Text(
            text = category,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

// Product Card Composable
@Composable
fun ProductCard(product: ProductDataModels) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Product Image
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "₹${product.price}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "4.4", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    currentPage: Int,
    selectedColor: Color,
    unSelectedColor: Color,
    indicatorSize: Dp = 8.dp,
    spacing: Dp = 4.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Add horizontal padding if needed
        horizontalArrangement = Arrangement.Center
    ) {
        for (index in 0 until totalDots) {
            Box(
                modifier = Modifier
                    .size(indicatorSize)
                    .clip(CircleShape)
                    .background(color = if (index == currentPage) selectedColor else unSelectedColor)
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.width(spacing))
            }
        }
    }
}

