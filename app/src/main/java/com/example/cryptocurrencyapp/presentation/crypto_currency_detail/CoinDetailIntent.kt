package com.example.cryptocurrencyapp.presentation.crypto_currency_detail

sealed class CoinDetailIntent {
    data class LoadCoinDetail(val coinId: String) : CoinDetailIntent()
}
