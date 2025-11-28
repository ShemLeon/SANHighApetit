package com.leoevg.san_dinner.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.san_dinner.presentation.navigation.Screen
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Date button
            Surface(
                modifier = Modifier,
                shape = RoundedCornerShape(20.dp),
                color = Purple80
            ) {
                Text(
                    text = "Суббота, 29 ноября",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 14.sp,
                    color = Purple40
                )
            }

            // Notification bell
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(48.dp)
                    .background(Purple40, RoundedCornerShape(24.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // User information card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    placeholder = { Text("Имя") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Purple40
                    )
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    placeholder = { Text("Фамилия") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Purple40
                    )
                )
            }
        }

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

@Composable
fun FoodCard(
    title: String,
    imageUrl: String?,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Purple40
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .then(
                if (isSelected) {
                    Modifier.border(2.dp, borderColor, RoundedCornerShape(16.dp))
                } else {
                    Modifier
                }
            )
            .background(if (isSelected) Purple80 else Color.White)
            .clickable { onSelect() }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for image - replace with AsyncImage when you have image URLs
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2
            )

            if (isSelected) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.checkbox_on_background),
                        contentDescription = "Selected",
                        modifier = Modifier.size(20.dp),
                        tint = borderColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}