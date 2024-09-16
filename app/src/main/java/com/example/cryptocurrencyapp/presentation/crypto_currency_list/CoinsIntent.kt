package com.example.cryptocurrencyapp.presentation.crypto_currency_list

sealed class CoinsIntent {
    data object LoadCoins : CoinsIntent()
}
