package com.leoevg.san_dinner.presentation.screen.main

import android.content.res.Configuration
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.presentation.navigation.Screen
import com.leoevg.san_dinner.presentation.screen.main.components.CourseSection
import com.leoevg.san_dinner.presentation.screen.main.components.DishCard
import com.leoevg.san_dinner.presentation.screen.main.components.OrderBtn
import com.leoevg.san_dinner.presentation.screen.main.components.TopSection
import com.leoevg.san_dinner.presentation.screen.main.components.UserInfoSection
import java.util.Locale

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit = {},
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    // Create localized context - use state.language as key (String has proper equals)
    val localizedContext = remember(state.language) {
        val locale = when (state.language) {
            "RU" -> Locale("ru", "RU")
            "HE" -> Locale("he", "IL")
            "EN" -> Locale("en", "US")
            else -> Locale.getDefault()
        }
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
    }

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
            TopSection(
                language = state.language,
                onLanguageChange = { viewModel.onEvent(MainScreenEvent.LanguageUpdated(it)) },
                notificationsEnabled = state.notificationsEnabled,
                onNotificationsToggle = { viewModel.onEvent(MainScreenEvent.NotificationsUpdated(it)) }
            )
            Spacer(modifier = Modifier.height(5.dp))

            UserInfoSection(
                firstName = state.firstName,
                workerID = state.workerID,
                onNameChange = { viewModel.onEvent(MainScreenEvent.NameUpdated(it)) },
                onWorkerIDChange = { viewModel.onEvent(MainScreenEvent.WorkerIDUpdated(it)) },
                language = state.language
            )
            Spacer(modifier = Modifier.height(5.dp))

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                if (state.mainDishes.isEmpty() && state.sideDishes.isEmpty()) {
                    Text(
                        text = "ERROR: List is empty",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                // Main Dishes (Top)
                if (state.mainDishes.isNotEmpty()) {
                    CourseSection(
                        title = localizedContext.getString(R.string.first_course),
                        isChosen = true,
                        language = state.language
                    ) {
                        state.mainDishes.forEach { dish ->
                            val title = dish.lang[state.language.lowercase()] ?: dish.lang["en"] ?: dish.id
                            DishCard(
                                title = title,
                                imageUrl = dish.picture.takeIf { it.isNotEmpty() },
                                isSelected = false, // Logic for selection to be added
                                onSelect = { },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                // Side Dishes (Bottom)
                if (state.sideDishes.isNotEmpty()) {
                    CourseSection(
                        title = localizedContext.getString(R.string.second_course),
                        isChosen = true,
                        chosenColorBg = Color(0xFFADD8E6),
                        chosenColorText = Color(0xFF0066CC),
                        language = state.language
                    ) {
                        state.sideDishes.forEach { dish ->
                            val title = dish.lang[state.language.lowercase()] ?: dish.lang["en"] ?: dish.id
                            DishCard(
                                title = title,
                                imageUrl = dish.picture.takeIf { it.isNotEmpty() },
                                isSelected = false, // Logic for selection to be added
                                onSelect = { },
                                modifier = Modifier.weight(1f),
                                borderColor = Color(0xFF0066CC)
                            )
                        }
                    }
                }
            }

            val isFormValid = true

            OrderBtn(
                onClick = { /* Submit */ },
                isFormValid = isFormValid
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
