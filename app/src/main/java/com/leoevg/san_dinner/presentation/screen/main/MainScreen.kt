package com.leoevg.san_dinner.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.san_dinner.presentation.navigation.Screen
import com.leoevg.san_dinner.presentation.screen.main.components.UserInfoBlock
import com.leoevg.san_dinner.presentation.screen.main.components.FoodCard
import com.leoevg.san_dinner.presentation.screen.main.components.TopSection
import com.leoevg.san_dinner.ui.theme.Purple40
import com.leoevg.san_dinner.ui.theme.Purple80

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top section with date and notification
        TopSection()
        Spacer(modifier = Modifier.height(24.dp))
        // User information card
        UserInfoBlock()
        Spacer(modifier = Modifier.height(32.dp))
        // First Course Section
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Первое блюдо",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple40
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Purple80
                ) {
                    Text(
                        text = "Выбрано",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        color = Purple40
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Borscht card
                FoodCard(
                    title = "Борщ",
                    imageUrl = null,
                    isSelected = false,
                    onSelect = { },
                    modifier = Modifier.weight(1f)
                )

                // Solyanka card (selected)
                FoodCard(
                    title = "Солянка",
                    imageUrl = null,
                    isSelected = true,
                    onSelect = { },
                    modifier = Modifier.weight(1f),
                    borderColor = Purple40
                )

                // Mushroom Soup card
                FoodCard(
                    title = "Грибной суп",
                    imageUrl = null,
                    isSelected = false,
                    onSelect = { },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Second Course Section
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Второе блюдо",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple40
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFADD8E6) // Light blue
                ) {
                    Text(
                        text = "Выбрано",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        color = Color(0xFF0066CC) // Blue
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Steak card
                FoodCard(
                    title = "Стейк из...",
                    imageUrl = null,
                    isSelected = false,
                    onSelect = { },
                    modifier = Modifier.weight(1f)
                )

                // Chicken Fillet card (selected)
                FoodCard(
                    title = "Куриное филе",
                    imageUrl = null,
                    isSelected = true,
                    onSelect = { },
                    modifier = Modifier.weight(1f),
                    borderColor = Color(0xFF0066CC) // Blue
                )

                // Grilled Salmon card
                FoodCard(
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