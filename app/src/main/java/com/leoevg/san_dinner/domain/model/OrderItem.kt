package com.leoevg.san_dinner.domain.model

data class OrderItem(
    val id: String = "",
    val days: List<String> = emptyList(),
    val lang: Map<String, String> = emptyMap(),
    val picture: String = "",
    val type: String = "",
    val vegan: Boolean = false
)
