package com.leoevg.san_dinner.presentation.screen.main

data class MainScreenState(
    val firstName: String = "",
    val workerID: String = "",
    val language: String = "RU",
    val notificationsEnabled: Boolean = true
)