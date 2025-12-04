package com.leoevg.san_dinner.presentation.screen.main

import com.leoevg.san_dinner.domain.model.OrderItem

data class MainScreenState(
    val firstName: String = "",
    val workerID: String = "",
    val language: String = "RU",
    val notificationsEnabled: Boolean = true,
    val mainDishes: List<OrderItem> = emptyList(),
    val sideDishes: List<OrderItem> = emptyList(),
    val isLoading: Boolean = false
)
