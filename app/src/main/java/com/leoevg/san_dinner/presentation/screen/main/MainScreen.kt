package com.leoevg.san_dinner.presentation.screen.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
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
    viewModel: MainScreenViewModel = hiltViewModel(creationCallback = { factory: MainScreenViewModel.Factory ->
        factory.create(onNavigateTo)
    })
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

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
            .clickable( // Clear focus on outside click
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
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
                onLanguageChange = { viewModel.onEvent(MainScreenEvent.LanguageUpdated(it)) }
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
                        modifier = Modifier.padding(top = 10.dp),
                        title = localizedContext.getString(R.string.first_course),
                        isChosen = state.selectedMainDishId != null,
                        language = state.language
                    ) {
                        state.mainDishes.forEach { dish ->
                            val title = dish.lang[state.language.lowercase()] ?: dish.lang["en"] ?: dish.id
                            DishCard(
                                title = title,
                                imageUrl = dish.picture.takeIf { it.isNotEmpty() },
                                isSelected = state.selectedMainDishId == dish.id,
                                onSelect = { viewModel.onEvent(MainScreenEvent.MainDishSelected(dish.id)) },
                                modifier = Modifier.width(140.dp),
                                accentColor = Color(0xFF9333EA)
                            )
                        }
                    }
                }

                // Side Dishes (Bottom)
                if (state.sideDishes.isNotEmpty()) {
                    CourseSection(
                        modifier = Modifier.padding(top = 10.dp),
                        title = localizedContext.getString(R.string.second_course),
                        isChosen = state.selectedSideDishId != null,
                        chosenColorBg = Color(0xFFADD8E6),
                        chosenColorText = Color(0xFF0066CC),
                        language = state.language
                    ) {
                        state.sideDishes.forEach { dish ->
                            val title = dish.lang[state.language.lowercase()] ?: dish.lang["en"] ?: dish.id
                            DishCard(
                                title = title,
                                imageUrl = dish.picture.takeIf { it.isNotEmpty() },
                                isSelected = state.selectedSideDishId == dish.id,
                                onSelect = { viewModel.onEvent(MainScreenEvent.SideDishSelected(dish.id)) },
                                modifier = Modifier.width(140.dp),
                                accentColor = Color(0xFF0066CC)
                            )
                        }
                    }
                }
            }

            val isFormValid = state.selectedMainDishId != null && state.selectedSideDishId != null

            if (state.responseSentSuccessfully != null && state.responseSentSuccessfully == false) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(Color.Red, RoundedCornerShape(15.dp))
                        .padding(12.dp),
                    text = stringResource(R.string.error_sending_form_response)
                )
            }
            OrderBtn(
                modifier = Modifier
                    .padding(top = 30.dp),
                text = localizedContext.getString(R.string.order),
                onClick = {
                    // Mark order as confirmed to prevent alarm
                    viewModel.onEvent(MainScreenEvent.OrderConfirmed)
                },
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
