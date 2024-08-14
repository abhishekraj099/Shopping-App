package com.example.shoppingapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.shoppingapp.R
import com.example.shoppingapp.navigation.Routes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

data class OnboardingPage(
    val imageResId1: Int,
    val imageResId2: Int,
    val imageResId3: Int,
    val quote: String
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreenUI(navController: NavHostController) {
    val onboardingPages = listOf(
        OnboardingPage(
            R.drawable.girl_1,
            R.drawable.girl_2,
            R.drawable.girl_3,
            "\"Dress like you've made something of yourself in the world, even if you haven't.\""
        ),
        OnboardingPage(
            R.drawable.girl_4,
            R.drawable.girl_5,
            R.drawable.girl_6,
            "\"Style is knowing who you are, what you want to say, and not giving a damn.\""
        ),
        OnboardingPage(
            R.drawable.girl_7,
            R.drawable.girl_8,
            R.drawable.girl_9,
            "\"Fashion is about dreaming and making other people dream.\""
        )
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                TextButton(
                    onClick = {
                        // Handle "Skip" action - navigate to main screen
                        navController.navigate(Routes.LoginScreen)
                    }
                ) {
                    Text(
                        "Skip",
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }

            HorizontalPager(
                count = onboardingPages.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) { page ->
                OnboardingPageContent(onboardingPages[page])
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < onboardingPages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            navController.navigate(Routes.LoginScreen)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .height(56.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage < onboardingPages.size - 1) "Next" else "Get Started",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(onboardingPages.size) { iteration ->
                    val indicatorColor =
                        if (pagerState.currentPage == iteration) Color(0xFF2196F3) else Color.LightGray
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(indicatorColor)
                    )
                    if (iteration != onboardingPages.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageLayout(page = page)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = page.quote,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
        )
    }
}


@Composable
fun ImageLayout(page: OnboardingPage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp), // Adjust this height as needed
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OnboardingImage(
            imageResId = page.imageResId1,
            aspectRatio = 0.5f, // 1:2 ratio (width:height)
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OnboardingImage(
                imageResId = page.imageResId2,
                aspectRatio = 1f, // 1:1 ratio (square)
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            OnboardingImage(
                imageResId = page.imageResId3,
                aspectRatio = 1f, // 1:1 ratio (square)
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun OnboardingImage(
    imageResId: Int,
    aspectRatio: Float,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberAsyncImagePainter(model = imageResId),
        contentDescription = "Onboarding Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(aspectRatio)
            .clip(RoundedCornerShape(16.dp))
    )
}

