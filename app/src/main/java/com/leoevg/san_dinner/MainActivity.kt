package com.leoevg.san_dinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.leoevg.san_dinner.presentation.navigation.MainNav
import com.leoevg.san_dinner.presentation.screen.main.MainScreen
import com.leoevg.san_dinner.ui.theme.SAN_dinnerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SAN_dinnerTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Apply innerPadding to MainScreen to handle edge-to-edge insets properly
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNav(navHostController = rememberNavController())
                    }
        }
    }
}}}