package com.example.cryptocurrencyapp.presentation.navigation

import kotlinx.serialization.Serializable

object Screen {
    @Serializable
    object CoinList

    @Serializable
    data class CoinDetail(val coinId: String)
}
