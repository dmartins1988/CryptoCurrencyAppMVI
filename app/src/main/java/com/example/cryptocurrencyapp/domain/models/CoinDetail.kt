package com.example.cyrptocurrencymvi.domain.models

data class CoinDetail(
    val coinId: String = "",
    val name: String = "",
    val description: String = "",
    val symbol: String = "",
    val rank: Int = 0,
    val isActive: Boolean = false,
    val tags: List<String> = emptyList(),
)
