package com.leoevg.san_dinner.presentation.screen.after

data class AfterScreenState(
    val orderData: OrderData = OrderData(),
    val language: String = "RU"
)

data class OrderData(
    val firstName: String = "",
    val lastName: String = "",
    val employeeId: String = "",
    val mainDish: String = "",
    val sideDish: String = ""
)
