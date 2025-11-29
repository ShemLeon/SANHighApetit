package com.leoevg.san_dinner.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoevg.san_dinner.presentation.navigation.Screen
import com.leoevg.san_dinner.presentation.screen.main.components.CourseSection
import com.leoevg.san_dinner.presentation.screen.main.components.DishCard
import com.leoevg.san_dinner.presentation.screen.main.components.TopSection
import com.leoevg.san_dinner.presentation.screen.main.components.UserInfoSection
import com.leoevg.san_dinner.ui.theme.Purple40

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF3E8FF),
                        Color.White,
                        Color(0xFFEFF6FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 430.dp)
                .align(Alignment.TopCenter)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Top section with date and notification
            TopSection()
            Spacer(modifier = Modifier.height(24.dp))
            // User information card
            UserInfoSection()
            Spacer(modifier = Modifier.height(32.dp))
            // First Course Section
            CourseSection(
                title = "Первое блюдо",
                isChosen = true
            ) {
                // Borscht card
                DishCard(
                    title = "Борщ",
                    imageUrl = null,
                    isSelected = false,
                    onSelect = { },
                    modifier = Modifier.weight(1f)
                )

                // Solyanka card (selected)
                DishCard(
                    title = "Солянка",
                    imageUrl = null,
                    isSelected = true,
                    onSelect = { },
                    modifier = Modifier.weight(1f),
                    borderColor = Purple40
                )
                // Mushroom Soup card
                DishCard(
                    title = "Грибной суп",
                    imageUrl = null,
                    isSelected = false,
                    onSelect = { },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Second Course Section
            CourseSection(
                title = "Второе блюдо",
                isChosen = true,
                chosenColorBg = Color(0xFFADD8E6), // Light blue
                chosenColorText = Color(0xFF0066CC) // Blue
            ) {
                // Steak card
                DishCard(
                    title = "Стейк из...",
                    imageUrl = null,
                    isSelected = false,
                    onSelect = { },
                    modifier = Modifier.weight(1f)
                )

                // Chicken Fillet card (selected)
                DishCard(
                    title = "Куриное филе",
                    imageUrl = null,
                    isSelected = true,
                    onSelect = { },
                    modifier = Modifier.weight(1f),
                    borderColor = Color(0xFF0066CC) // Blue
                )
                // Grilled Salmon card
                DishCard(
                    title = "Лосось на гри.",
                    imageUrl = null,
                    isSelected = false,
                    onSelect = { },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}