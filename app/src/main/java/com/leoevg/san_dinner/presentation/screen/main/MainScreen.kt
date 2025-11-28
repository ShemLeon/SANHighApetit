package com.leoevg.san_dinner.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoevg.san_dinner.presentation.navigation.Screen
import com.leoevg.san_dinner.presentation.screen.preload.Greeting


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

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
