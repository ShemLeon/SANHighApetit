package com.leoevg.san_dinner.presentation.screen.preload

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.presentation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun PreloadScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        delay(2000) // Show logo for 2 seconds
        onNavigateTo(Screen.Main)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp) // Adjust size as needed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreloadScreenPreview() {
    PreloadScreen()
}
