package com.leoevg.san_dinner.presentation.screen.main.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.foundation.layout.Box
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.presentation.screen.main.MainScreen
import android.content.res.Configuration
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TopSection(
    language: String,
    onLanguageChange: (String) -> Unit,
    notificationsEnabled: Boolean,
    onNotificationsToggle: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var isLanguageMenuExpanded by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    // Get localized context based on selected language
    val localizedContext = remember(language) {
        val locale = when (language) {
            "RU" -> Locale("ru", "RU")
            "HE" -> Locale("he", "IL")
            "EN" -> Locale("en", "US")
            else -> Locale.getDefault()
        }
        val configuration = context.resources.configuration
        val newConfig = android.content.res.Configuration(configuration)
        newConfig.setLocale(locale)
        context.createConfigurationContext(newConfig)
    }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Кнопка выбора языка
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
                                contentDescription = localizedContext.getString(R.string.language),
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
                Surface(
                    shape = CircleShape,
                    color = if (notificationsEnabled) Color(0xFF9333EA) else Color.White,
                    shadowElevation = 2.dp,
                    modifier = Modifier.size(40.dp)
                ) {
                    IconButton(onClick = {
                        val newState = !notificationsEnabled
                        onNotificationsToggle(newState)
                        scope.launch {
                            val message = if (newState) {
                                localizedContext.getString(R.string.notification_enabled)
                            } else {
                                localizedContext.getString(R.string.notification_disabled)
                            }
                            snackbarHostState.showSnackbar(message)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = localizedContext.getString(R.string.notifications),
                            tint = if (notificationsEnabled) Color.White else Color(0xFF9CA3AF)
                        )
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
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
        notificationsEnabled = true,
        onNotificationsToggle = {}
    )
}