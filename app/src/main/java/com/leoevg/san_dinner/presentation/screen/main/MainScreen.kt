package com.leoevg.san_dinner.presentation.screen.main

import android.content.Context
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.presentation.navigation.Screen
import com.leoevg.san_dinner.presentation.screen.main.components.CourseSection
import com.leoevg.san_dinner.presentation.screen.main.components.DishCard
import com.leoevg.san_dinner.presentation.screen.main.components.OrderBtn
import com.leoevg.san_dinner.presentation.screen.main.components.TopSection
import com.leoevg.san_dinner.presentation.screen.main.components.UserInfoSection
import com.leoevg.san_dinner.ui.theme.Purple40
import java.util.Locale
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit = {},
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    val context = LocalContext.current
    
    // Apply language to configuration for string resources to work
    val configuration = LocalConfiguration.current
    val localizedConfiguration = remember(state.language, configuration) {
        val locale = when (state.language) {
            "RU" -> Locale("ru", "RU")
            "HE" -> Locale("he", "IL")
            "EN" -> Locale("en", "US")
            else -> Locale.getDefault()
        }
        val config = Configuration(configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        config
    }
    
    // Update resources configuration 
    val resources = context.resources
    val locale = when (state.language) {
        "RU" -> Locale("ru", "RU")
        "HE" -> Locale("he", "IL")
        "EN" -> Locale("en", "US")
        else -> Locale.getDefault()
    }
    Configuration(resources.configuration).apply {
        setLocale(locale)
        setLayoutDirection(locale)
        resources.updateConfiguration(this, resources.displayMetrics)
    }

    CompositionLocalProvider(
        LocalConfiguration provides localizedConfiguration,
        // Force LTR direction for UI consistency across languages
        LocalLayoutDirection provides LayoutDirection.Ltr
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
                TopSection(
                    language = state.language,
                    onLanguageChange = { viewModel.onEvent(MainScreenEvent.LanguageUpdated(it)) },
                    notificationsEnabled = state.notificationsEnabled,
                    onNotificationsToggle = { viewModel.onEvent(MainScreenEvent.NotificationsUpdated(it)) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                // User information card
                UserInfoSection(
                    firstName = state.firstName,
                    workerID = state.workerID,
                    onNameChange = { viewModel.onEvent(MainScreenEvent.NameUpdated(it)) },
                    onWorkerIDChange = { viewModel.onEvent(MainScreenEvent.WorkerIDUpdated(it)) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                // First Course Section
                CourseSection(
                    title = stringResource(R.string.first_course),
                    isChosen = true
                ) {
                    // Borscht card
                    DishCard(
                        title = stringResource(R.string.borscht),
                        imageUrl = null,
                        isSelected = false,
                        onSelect = { },
                        modifier = Modifier.weight(1f)
                    )

                    // Solyanka card (selected)
                    DishCard(
                        title = stringResource(R.string.solyanka),
                        imageUrl = null,
                        isSelected = true,
                        onSelect = { },
                        modifier = Modifier.weight(1f),
                        borderColor = Purple40
                    )
                    // Mushroom Soup card
                    DishCard(
                        title = stringResource(R.string.mushroom_soup),
                        imageUrl = null,
                        isSelected = false,
                        onSelect = { },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Second Course Section
                CourseSection(
                    title = stringResource(R.string.second_course),
                    isChosen = true,
                    chosenColorBg = Color(0xFFADD8E6), // Light blue
                    chosenColorText = Color(0xFF0066CC) // Blue
                ) {
                    // Steak card
                    DishCard(
                        title = stringResource(R.string.steak),
                        imageUrl = null,
                        isSelected = false,
                        onSelect = { },
                        modifier = Modifier.weight(1f)
                    )

                    // Chicken Fillet card (selected)
                    DishCard(
                        title = stringResource(R.string.chicken_fillet),
                        imageUrl = null,
                        isSelected = true,
                        onSelect = { },
                        modifier = Modifier.weight(1f),
                        borderColor = Color(0xFF0066CC) // Blue
                    )
                    // Grilled Salmon card
                    DishCard(
                        title = stringResource(R.string.grilled_salmon),
                        imageUrl = null,
                        isSelected = false,
                        onSelect = { },
                        modifier = Modifier.weight(1f)
                    )
                }
                
                // For now hardcode validation to false or use placeholder state until ViewModels are hooked up
                val isFormValid = true 

                OrderBtn(
                    onClick = { /* Submit */ },
                    isFormValid = isFormValid
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