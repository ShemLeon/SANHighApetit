package com.leoevg.san_dinner.presentation.navigation

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object PreLoad: Screen
    @Serializable
    data object Main: Screen
    @Serializable
    data object After: Screen
}

