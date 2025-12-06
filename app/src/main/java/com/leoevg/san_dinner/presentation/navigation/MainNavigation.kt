package com.leoevg.san_dinner.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leoevg.san_dinner.presentation.screen.after.AfterScreen
import com.leoevg.san_dinner.presentation.screen.main.MainScreen
import com.leoevg.san_dinner.presentation.screen.preload.PreloadScreen

@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.PreLoad
    ){
        composable<Screen.PreLoad>{
            PreloadScreen(
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo) {
                        popUpTo(Screen.PreLoad) { inclusive = true }
                    }
                }
            )
        }
        composable<Screen.Main>{
            MainScreen(
                onNavigateTo = { navigateTo->
                    navHostController.navigate(navigateTo)
                }
            )
        }
        composable<Screen.After>{
            AfterScreen(
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo)
                }
            )
        }
    }
}
