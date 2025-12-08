package com.leoevg.san_dinner.presentation.screen.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TopSection(
    language: String,
    onLanguageChange: (String) -> Unit,
) {
    val context = LocalContext.current
    var isLanguageMenuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Date
        Surface(
            shape = RoundedCornerShape(50),
            color = Color.White,
            shadowElevation = 2.dp,
        ) {
            Text(
                text = getFormattedDate(language),
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                fontSize = 14.sp,
                color = Color(0xFF9333EA),
            )
        }

        // Language Button
        Box {
            Surface(
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.size(40.dp)
            ) {
                IconButton(onClick = { isLanguageMenuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = "Language", // Fallback
                        tint = Color(0xFF374151)
                    )
                }
            }

            DropdownMenu(
                expanded = isLanguageMenuExpanded,
                onDismissRequest = { isLanguageMenuExpanded = false }
            ) {
                listOf("RU", "HE", "EN").forEach { lang ->
                    DropdownMenuItem(
                        text = { Text(lang) },
                        onClick = {
                            onLanguageChange(lang)
                            isLanguageMenuExpanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = if (language == lang) Color(0xFF9333EA) else Color.Black
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun getFormattedDate(language: String): String {
    val calendar = Calendar.getInstance()
    val locale = when (language) {
        "RU" -> Locale("ru", "RU")
        "HE" -> Locale("he", "IL")
        "EN" -> Locale("en", "US")
        else -> Locale.getDefault()
    }
    val dateFormat = SimpleDateFormat("EEEE, d MMMM", locale)
    return dateFormat.format(calendar.time)
}

@Preview(showBackground = true)
@Composable
fun TopPreview() {
    TopSection(
        language = "RU",
        onLanguageChange = {},
    )
}
